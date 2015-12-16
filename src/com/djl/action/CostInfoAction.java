package com.djl.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.djl.domain.CostInfo;
import com.djl.domain.CostType;
import com.djl.domain.Page;
import com.djl.domain.Spender;
import com.djl.service.CostInfoService;
import com.djl.service.CostTypeService;
import com.djl.service.SpenderService;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class CostInfoAction{
	
	private CostInfo costInfo;
	@Resource CostInfoService costInfoService;
	@Resource CostTypeService costTypeService;
	@Resource SpenderService spenderService;
	public CostInfo getCostInfo() {
		return costInfo;
	}

	public void setCostInfo(CostInfo costInfo) {
		this.costInfo = costInfo;
	}
	
	public String list() throws Exception{
//		int pageNo= 0 , count =5 ;
		Page page = new Page();
//		int firstRow=0;
		String pageNo ="";
		//定义map存放查询参数
		Map<String , Object> map = new HashMap<String, Object>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(costInfo!=null ){
			System.out.println("costtime:"+costInfo.getSpendTime()+"-----------------costtype:"+costInfo.getAmtflag()) ;
			if(costInfo.getSpendTime()!=null)
				map.put("spendTime", costInfo.getSpendTime());
			if(costInfo.getAmtflag()!=0)
				map.put("amtFlag", costInfo.getAmtflag());
		}
		
		if(ServletActionContext.getRequest().getParameter("currentPage")!=null)
			pageNo = ServletActionContext.getRequest().getParameter("currentPage").toString();
		 
		if(pageNo!=null&&pageNo.length()!=0){
			page.setCurrentPage(Integer.valueOf( pageNo));
//			firstRow = page.getCurrentPage()*page.getCount();
		}
//		page.setFirstRow(firstRow);
		page = costInfoService.list(page,map);
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) page.getList();
		List<CostInfo> costinfos = this.getCostInfosList(list);
//		List<CostInfo> costinfos =  (List<CostInfo>) page.getList();
		//改为数据库分页，此处恢复原来封装
//		List<CostInfo> costinfos = getCostInfosList(firstRow, page.getCount(), listtmps);
//		List<CostInfo> costinfos = getCostInfosList( listtmps);
		//得到list条数
		int totalcount =page.getTotalCount();
		System.out.println("列表总条数是"+totalcount) ;
		
		ActionContext.getContext().put("costinfos", costinfos);
		ActionContext.getContext().put("page", page);
	if(costInfo!=null){
			ActionContext.getContext().put("costInfo", costInfo);
			if(costInfo.getSpendTime()!=null)
				ActionContext.getContext().put("sptime", df.format(costInfo.getSpendTime())); 
		}
		return "list";
	}
	
 public List<CostInfo> getCostInfosList(List<Object[]> listtmps)
			throws ParseException {
		// 把返回结果封装到List<CostInfo> 中，便于前台显示--改为导出xml使用
		List<CostInfo> costinfos = new ArrayList<CostInfo>();
		CostInfo ci = null;
		DateFormat df = DateFormat.getDateInstance();
		for (Object[] obj : listtmps) {
			if (obj != null) {
					ci = new CostInfo();
				ci.setId(obj[0].toString());
				if (obj[1] != null)
					ci.setSpendTime(df.parse(obj[1].toString()));
				ci.setSpname(obj[2].toString());
				ci.setCtname(obj[3].toString());
				ci.setAcname(obj[4].toString());
				ci.setComment(obj[5].toString());
				ci.setAmt(Double.valueOf(obj[6].toString()));
				ci.setAmtflag(Integer.valueOf(obj[7].toString()));
				costinfos.add(ci);
			}
		}
		return costinfos;
	} 
	
	/**
	 * 
	 * @param firstRow 起始行位置
	 * @param pageCount 每页显示条数
	 * @param listtmps list结果
	 * @return
	 * @throws ParseException
	 */
/*	public List<CostInfo> getCostInfosList(int  firstRow, int pageCount, List<Object[]> listtmps)
			throws ParseException {
		//把返回结果封装到List<CostInfo> 中，便于前台显示
		List <CostInfo> costinfos = new ArrayList<CostInfo>();
		CostInfo ci = null;
		DateFormat df = DateFormat.getDateInstance();
//		for(Object[] obj : listtmps){
		if(listtmps.size()<firstRow+pageCount)
			pageCount=listtmps.size();
		for(int i = firstRow ; i< pageCount ; i++){
			if(listtmps.get(i)[0]!=null){
				ci = new CostInfo();
				ci.setId(listtmps.get(i)[0].toString());
				if(listtmps.get(i)[1]!=null)
					ci.setSpendTime(df.parse(listtmps.get(i)[1].toString()));
				ci.setSpname(listtmps.get(i)[2].toString());
				ci.setCtname(listtmps.get(i)[3].toString());
				ci.setAcname(listtmps.get(i)[4].toString());
				ci.setComment(listtmps.get(i)[5].toString());
				ci.setAmt(Double.valueOf(listtmps.get(i)[6].toString()));
				ci.setAmtflag(Integer.valueOf(listtmps.get(i)[7].toString()));
				costinfos.add(ci);
			}
		}
		return costinfos;
	}*/
	
	//显示新增costInfo页面
	public String addPage(){
		//此处两个list 用作添加页面下拉框，
		//注入两个额外的Service 来获得其查询列表
		List<CostType> costtypes = costTypeService.list();
		ActionContext.getContext().put("costtypes", costtypes);
		List<Spender> spenders = spenderService.list(null);
		ActionContext.getContext().put("spenders", spenders);
		return "add";
	}
	
	//保存更新对象
	public String add(){
		//得到spender、accounter 并放入costInfo中
		try {
			Spender s =(Spender) spenderService.queryById(Spender.class,costInfo.getSpid());
			CostType c = costTypeService.find(costInfo.getCtid());
			Spender a = (Spender) spenderService.queryById(Spender.class,costInfo.getAcid());
			CostInfo costInfo1 = new CostInfo(s, costInfo.getSpendTime(), c , a, costInfo.getComment(),costInfo.getAmt() );
			costInfoService.excuteSave(costInfo1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		ActionContext.getContext().put("bean", "costinfo");
		return "ok";
	}
	
	public String delete( ){
		String id = ServletActionContext.getRequest().getParameter("id").toString();
		costInfoService.excuteDelete(CostInfo.class, id);
		ActionContext.getContext().put("bean", "costinfo");
		return "ok";
	}
	//显示costInfo更新页面
	public String updatePage(){
		String id = ServletActionContext.getRequest().getParameter("id").toString();
		costInfo = (CostInfo) costInfoService.queryById(CostInfo.class, id);
		if(costInfo.getSpender()!=null)
			costInfo.setSpid(costInfo.getSpender().getId());
		if(costInfo.getAccounter()!=null)
			costInfo.setAcid(costInfo.getAccounter().getId());
		if(costInfo.getCostType() !=null )
			costInfo.setCtid(costInfo.getCostType().getId());
//		修正日期格式，此方法改正格式后为String类型，因此把sptime传到前台
		if(costInfo.getSpendTime()!=null){
			DateFormat df = DateFormat.getDateInstance();
			String sptime = df.format(costInfo.getSpendTime());
			ActionContext.getContext().put("sptime", sptime);
		}else{
			ActionContext.getContext().put("sptime", "");
		}
		//此处两个list 用作添加页面下拉框，
		//注入两个额外的Service 来获得其查询列表
		List<CostType> costtypes = costTypeService.list();
		ActionContext.getContext().put("costtypes", costtypes);
		List<Spender> spenders = spenderService.list(null);
		ActionContext.getContext().put("spenders", spenders);
		return "update";
	}
	//保存更新对象
	public String update(){
		Spender s =(Spender) spenderService.queryById(Spender.class,costInfo.getSpid());
		CostType c = costTypeService.find(costInfo.getCtid());
		Spender a = (Spender) spenderService.queryById(Spender.class,costInfo.getAcid());
		CostInfo costInfo1 = new CostInfo(s, costInfo.getSpendTime(), c , a, costInfo.getComment(),costInfo.getAmt());
		costInfo1.setId(costInfo.getId());
		costInfoService.excuteUpdate(costInfo1);
		ActionContext.getContext().put("bean", "costinfo");
		return "ok";
	}
	
	//导出xml格式账单
	public String exportXml(){
		String fileName =  "D:/xml/costinfolist.xml";
		
		List<Object[]> listtmps = costInfoService.list();
		try {
			List<CostInfo> costinfos = getCostInfosList(listtmps);
			Document document = setCostInfosElement(costinfos);
			document.setXMLEncoding("UTF8");
			
			//此方法生成文本的保存格式为ANSI，文本中含有中文，不能正常读取
//			Writer fileWriter = new FileWriter(fileName); 
//			XMLWriter xmlWriter = new XMLWriter(fileWriter);
//			xmlWriter.write(document);
//			xmlWriter.close();
			
			//OutputStream可以设置文本输出格式，所以用此方法导出
			OutputStream out = new FileOutputStream(fileName);
			Writer writer = new OutputStreamWriter(out, "UTF-8");
			
			document.write(writer);
			writer.close();
			
			System.out.println("生成xml文档成功");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		ActionContext.getContext().put("bean", "costinfo");
		return "ok";
	}
	
	//读取xml格式账单
	@SuppressWarnings("rawtypes")
	public String readXml(){
		String fileName = "D:/xml/costinfolist.xml";
		File inputfile = new File(fileName);
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(inputfile);
			Element costinfos = document.getRootElement();
			System.out.println(costinfos.getName());
			for(Iterator i = costinfos.elementIterator(); i.hasNext();){
				Element costinfo = (Element) i.next();
				for(Iterator j = costinfo.elementIterator();j.hasNext();){
					Element node = (Element) j.next();
					System.out.println(node.getName()+":"+node.getText());
				}
			}
			System.out.println("读取costinfosXml文件结束！");
	 
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		ActionContext.getContext().put("bean", "costinfo");
		return "ok";
	}

	public Document setCostInfosElement(List<CostInfo> costinfos) {
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF8");
		Element costinfoes = document.addElement("costinfos");
		for (int i=0;i<costinfos.size();i++){
			Element costinfot = costinfoes.addElement("costinfo");
			Element num = costinfot.addElement("num");
			num.setText(Integer.toString(i));
			Element sptime = costinfot.addElement("sptime");
			sptime.setText(costinfos.get(i).getSpendTime().toString());
			Element spname = costinfot.addElement("spname");
			spname.setText(costinfos.get(i).getSpname());
			Element costtype = costinfot.addElement("costtype");
			costtype.setText(costinfos.get(i).getCtname());
			Element amt = costinfot.addElement("amt");
			amt.setText(Double.toString(costinfos.get(i).getAmt()));
			Element amtflag = costinfot.addElement("amtflag");
			amtflag.setText(Long.toString(costinfos.get(i).getAmtflag()));
			Element accounter = costinfot.addElement("accounter");
			accounter.setText(costinfos.get(i).getAcname());
			Element comment = costinfot.addElement("comment");
			comment.setText(costinfos.get(i).getComment());
		}
		return document;
	}
	
	
}