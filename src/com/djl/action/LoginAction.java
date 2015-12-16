package com.djl.action;

import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.djl.domain.FunList;
import com.djl.domain.Spender;
import com.djl.service.LoginService;
import com.opensymphony.xwork2.ActionContext;

@Controller@Scope("prototype")
public class LoginAction {
	
	private static Logger log = Logger.getLogger(LoginAction.class);
	
	private Spender userContext;
	
	public Spender getUserContext() {
		return userContext;
	}

	public void setUserContext(Spender userContext) {
		this.userContext = userContext;
	}

	@Resource 
	private LoginService loginService;
	
	public String userLogin(){
		try {
			Spender user = (Spender) ServletActionContext.getRequest().getSession().getAttribute("userContext");
			//此时用户已经登录且未在重新登录
			if(user!=null&&userContext==null){
				userContext=user;
			}
			//登录认证
			if(userContext!=null){
				//获取用户信息
				Spender testUser = loginService.getUser(userContext);
				if(testUser!=null){
					if(userContext.getPassword() .equals(testUser.getPassword())){
						//获取该用户功能列表
						List<FunList> funlists = loginService.showList(testUser.getLevel());
						ActionContext.getContext().put("funlist", funlists);
						//把用户信息放入session
						ServletActionContext.getRequest().getSession().setAttribute("userContext", userContext);
						return "loginok";
					}else
					{	
						throw new LoginException("密码错误");
					}
				}
				else{
					throw new LoginException("用户名不存在");
				}
			}else
			{
				throw new LoginException("获取登陆信息失败");
			}
		} catch (LoginException e) {
			log.warn("登录错误:"+e.getMessage());
			ServletActionContext.getRequest().setAttribute("errinfo", e.getMessage());
			return "loginerror";
		}catch(Exception e){
			log.error("登录错误"+e.getMessage(),e);
			ServletActionContext.getRequest().setAttribute("errinfo", e.getMessage());
			return "loginerror";
		}

	}
	
	public String logout(){
		System.out.println("用户退出登录");
		ServletActionContext.getRequest().getSession().setAttribute("userContext", null);
		return "loginerror";
	}
	
}
