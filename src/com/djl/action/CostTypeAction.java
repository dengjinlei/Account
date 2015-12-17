package com.djl.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.djl.basic.BasicAction;
import com.djl.domain.CostType;
import com.djl.service.CostTypeService;
import com.opensymphony.xwork2.ActionContext;

@Controller 
@Scope("prototype")
public class CostTypeAction extends BasicAction{
	
	private static final long serialVersionUID = 3216988189797478318L;

	private final static Logger log = Logger.getLogger(CostTypeAction.class);
	
	private CostType costType;
	@Resource 
	private CostTypeService costTypeService;
	
	public CostType getCostType() {
		return costType;
	}

	public void setCostType(CostType costType) {
		this.costType = costType;
	}
	
	public String list(){
		List<CostType> costList = costTypeService.list();
		ActionContext.getContext().put("costtypes", costList);
		return "list";
	}
	
	//修改为通过jQuery post json格式数据保存信息
	public void add()  {
		try {
			costTypeService.save(costType);
			forword(0,"保存成功");
			log.info("保存账务类型成功"+costType.getName());
		} catch (Exception e) {
			log.error("保存账务类别失败-" + costType.getName() + e.getMessage(), e);
			forword(1,"保存失败"+e.getMessage());
		}
	}

	public String updatePage (){
		String sid = ServletActionContext.getRequest().getParameter("id").toString();
		int id = Integer.parseInt(sid);
		costType = costTypeService.find(id);
		return "update";
	}
	
	public void update (){

		try {
				costTypeService.update(costType);
				log.info("账务类别更新成功" + costType.getName());
				forword(0,"更新成功");
		} catch (Exception e) {
			log.error("更新账务类别失败-" + costType.getName() + e.getMessage(), e);
			forword(1,"更新失败"+e.getMessage());
		}
	}
	
	public String delete(){
		String sid = ServletActionContext.getRequest().getParameter("id").toString();
		int  id =  Integer.parseInt(sid);
		log.info("删除 costType id="+id);
		costTypeService.delete(id);
		ActionContext.getContext().put("bean", "costtype");
		return "ok";
	}
	
}
