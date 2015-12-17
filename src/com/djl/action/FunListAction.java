package com.djl.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.djl.basic.BasicAction;
import com.djl.domain.FunList;
import com.djl.domain.Page;
import com.djl.service.FunListService;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class FunListAction extends BasicAction{

	private static final long serialVersionUID = 3071064639750731755L;

	private static final Logger log = Logger.getLogger(FunListAction.class);
	
	private FunList funList;
	
	@Resource
	private FunListService funListService;

	public FunList getFunList() {
		return funList;
	}

	public void setFunList(FunList funList) {
		this.funList = funList;
	}
	
	public String addPage(){
		return "add";
	}
	
	public String add(){
		
		try {
			funListService.excuteSave(funList);
			ActionContext.getContext().put("bean", "funlist");
			log.info("新增功能' "+funList.getName()+"' 成功");
			return "ok";
		} catch (Exception e) {
			log.error("新增功能出错"+funList.getName()+e.getMessage(), e);
			ActionContext.getContext().put("errorinfo", e.getMessage());
			ActionContext.getContext().put("bean", "funlist");
			ActionContext.getContext().put("beanback", "funlist_add");
			return "error";
		}
		
	}
	
	public String list(){
		int currentPage=0;
		Page page = new Page();
		String pageNo = (String) ActionContext.getContext().get("currentPage");
		if(pageNo!=null){
			 currentPage = Integer.parseInt(pageNo);
		}
		List<FunList> funLists = funListService.listOfPage(currentPage);
		int totalCount = funListService.getTotalCount();
		page.setCurrentPage(currentPage);
		page.setTotalPage(totalCount%page.getSize()==0 ? totalCount/page.getSize() : totalCount/page.getSize()+1 );
		ActionContext.getContext().put("page", page);
		ActionContext.getContext().put("funLists", funLists);
		return "list";
	}
	
	public String updatePage(){
		String id = (String)ServletActionContext.getRequest().getParameter("id");
		if(id!=null)
			funList = (FunList) funListService.queryById(FunList.class, Integer.parseInt(id));
		return "update";
	}
	
	public String update(){
		try {
			if(funList!=null){
				funListService.excuteUpdate(funList);
				ActionContext.getContext().put("bean", "funlist");
				return "ok";
			}else
				throw new Exception("无更新对象");
		} catch (Exception e) {
			log.error("更新' "+funList.getName()+" '失败"+e.getMessage(),e );
			ActionContext.getContext().put("errorinfo", e.getMessage());
			ActionContext.getContext().put("bean", "funlist");
			//此时返回更新页面可能会出现无对象情况
			ServletActionContext.getRequest().setAttribute("id", funList.getId());
			ActionContext.getContext().put("beanback", "funlist_updatePage");
			return "error";
		}
		
	}
	
	public String delete(){
		try {
			String id =ServletActionContext.getRequest().getParameter("id");
			if(id!=null)
				funListService.excuteDelete(FunList.class, Integer.parseInt(id));
			else
				throw new Exception("获取删除信息失败");
		} catch (Exception e) {
			log.error("删除失败"+e.getMessage(),e );
			ActionContext.getContext().put("errorinfo", e.getMessage());
			//此时返回更新页面可能会出现无对象情况
			ActionContext.getContext().put("beanback", "funlist_list");
			return "error";
		}
		ActionContext.getContext().put("bean", "funlist");
		return "ok";
	}
	
	public String changeStatus(){
		String id = ServletActionContext.getRequest().getParameter("id");
		if(id!=null){
			funList=(FunList) funListService.queryById(FunList.class, Integer.parseInt(id));
			if(funList.getStatus()==0){
				funList.setStatus(1);
			}else{
				funList.setStatus(0);
			}
			funListService.excuteUpdate(funList);
		}
		return this.list();
	}
	
}
