package com.djl.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.djl.basic.BasicAction;
import com.djl.domain.Spender;
import com.djl.service.inter.SpenderServiceInt;
import com.opensymphony.xwork2.ActionContext;

@Controller 
@Scope("prototype")
public class SpenderAction extends BasicAction{
	
	private static final long serialVersionUID = 7449179312761812904L;

	private final static Logger log = Logger.getLogger(SpenderAction.class);
	
	private Spender spender;
	@Resource 
	private SpenderServiceInt spenderService;
	
	public Spender getSpender() {
		return spender;
	}

	public void setSpender(Spender spender) {
		this.spender = spender;
	}

	public void add(){
		try {
				// 判断用户名是否重复
				String msg = spenderService.checkUnique(spender.getName());
				if (msg.equals("yes")) {
					spenderService.excuteSave(spender);
					forword(0, "保存成功");
			} else {
					forword(1, "用户名重复");
			}
		} catch (Exception e) {
			log.error("保存失败-" + spender.getName() + e.getMessage(), e);
			forword(1,"保存失败"+e.getMessage());
		}
	}
	//显示用户列表
	//企业管理员可以看到除系统管理员以外的用户信息
	//系统管理员和普通用户只能看到自己的用户信息
	public String  list(){
			Spender user = (Spender) ServletActionContext.getRequest().getSession().getAttribute("userContext");
			List<Spender> spenders = spenderService.list(user.getLevel());
			ActionContext.getContext().put("spenders", spenders);
			return "list";
	}
	
	public String updatePage(){
		String spenderid = ServletActionContext.getRequest().getParameter("id").toString();
		spender = (Spender) spenderService.queryById(Spender.class, spenderid);
		return "update";
	}
	
	public void update(){
		spenderService.excuteUpdate(spender);
		forword(0,"修改成功");
	}
	
	public String delete (){
		String spenderid = ServletActionContext.getRequest().getParameter("id").toString();
		spenderService.excuteDelete(Spender.class , spenderid);
		ActionContext.getContext().put("bean", "spender");
		return "ok";
	}
	
	//检查用户名是否重复
	public void checkUnique(){
		String spname = ServletActionContext.getRequest().getParameter("spname").toString();
		try {
					// 改变spname的编码格式
					spname = new String(spname.getBytes("ISO8859-1"), "UTF-8");
					// spname = URLEncoder.encode(spname, "UTF-8");
					String msg = spenderService.checkUnique(spname);
					if(msg.equals("yes"))
						forword(0,"用户名可用");
					else
						forword(1,"用户名重复");
		} catch (UnsupportedEncodingException e) {
					log.error("不支持的编码格式"+e.getMessage(),e);
					forword(1,"用户名不能为特色符号！");
		}
	}
	
}
