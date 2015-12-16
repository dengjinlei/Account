package com.djl.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.djl.domain.CostType;
import com.djl.service.CostTypeService;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class CostTypeAction {
	
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
	
	public String addPage(){
		return "add";
	}
	
	public String add(){
		System.out.println(costType.getContext());
		costTypeService.save(costType);
		ActionContext.getContext().put("bean", "costtype");
		return "ok";
	}

	public String updatePage (){
		String sid = ServletActionContext.getRequest().getParameter("id").toString();
		int id = Integer.parseInt(sid);
		costType = costTypeService.find(id);
		return "update";
	}
	
	public String update (){
		
		costTypeService.update(costType);
		ActionContext.getContext().put("bean", "costtype");
		return "ok";
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
