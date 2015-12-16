package com.djl.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.djl.domain.Spender;
import com.djl.service.inter.SpenderServiceInt;
import com.opensymphony.xwork2.ActionContext;

@Controller 
@Scope("prototype")
public class SpenderAction{
	
	private Spender spender;
	@Resource 
	private SpenderServiceInt spenderService;
	
	
	public Spender getSpender() {
		return spender;
	}

	public void setSpender(Spender spender) {
		this.spender = spender;
	}

	public String addPage(){
//		ActionContext.getContext().put("error", "");
		return "add";
	}
	
	public String add(){
		//判断用户名是否重复
		String msg = spenderService.checkUnique(spender.getName());
		if(msg.equalsIgnoreCase("用户名可以使用")){
			spenderService.excuteSave(spender);
			ActionContext.getContext().put("bean", "spender");
			return "ok";
		}
		else {
			ActionContext.getContext().put("errorinfo", "用户名重复");
			ActionContext.getContext().put("beanback", "spender_addPage");
			ActionContext.getContext().put("bean", "spender");
			return "error";
		}
			
	}
	
	public String  list(){
			List<Spender> spenders = spenderService.list(null);
			ActionContext.getContext().put("spenders", spenders);
			return "list";
	}
	
	public String updatePage(){
		String spenderid = ServletActionContext.getRequest().getParameter("id").toString();
		spender = (Spender) spenderService.queryById(Spender.class, spenderid);
		return "update";
		
	}
	
	public String update(){
		
		spenderService.excuteUpdate(spender);
		ActionContext.getContext().put("bean", "spender");
		return "ok";
	}
	
	public String delete (){
		String spenderid = ServletActionContext.getRequest().getParameter("id").toString();
		spenderService.excuteDelete(Spender.class , spenderid);
		ActionContext.getContext().put("bean", "spender");
		return "ok";
	}
	
	//检查用户名是否重复
	public String checkUnique(){
		String spname = ServletActionContext.getRequest().getParameter("spname").toString();
//		System.out.println("spname="+spname);
		try {
			//改变spname的编码格式
			spname = new String(spname.getBytes("ISO8859-1"),"GBK");
//			spname = URLEncoder.encode(spname, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
//		System.out.println(spname);
		try {
			String msg = spenderService.checkUnique(spname);
//			ActionContext.getContext().put("msg", msg);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
