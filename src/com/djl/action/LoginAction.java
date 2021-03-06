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
			// 从session中获取登录信息
			Spender user = (Spender) ServletActionContext.getRequest()
					.getSession().getAttribute("userContext");
			List<FunList> funlists = null;
			if (user != null) {
				//显示用户功能列表
				funlists = loginService.showList(user.getLevel());
			}
			else {
				// 登录认证
				if (userContext != null) {
					// 根据用户名获取用户信息
					Spender testUser = loginService.getUser(userContext
							.getName());
					if (testUser != null) {
						// 密码正确
						if (userContext.getPassword().equals(
								testUser.getPassword())) {
							// 获取该用户功能列表
							funlists = loginService.showList(testUser
									.getLevel());
							// 把用户信息放入session
							ServletActionContext.getRequest().getSession()
									.setAttribute("userContext", testUser);
							log.warn("用户登录，用户名：" + userContext.getName());
						} else {
							throw new LoginException("密码错误！");
						}
					} else {
						throw new LoginException("用户名不存在！");
					}
				}else {
					throw new LoginException("用户登录超时或已退出，请重新登录！");
				}
			}
			ActionContext.getContext().put("funlist", funlists);
			return "loginok";
		} catch (LoginException e) {
			log.warn("登录错误:"+e.getMessage());
			ServletActionContext.getRequest().setAttribute("errinfo", e.getMessage());
			return "loginerror";
		}catch(Exception e){
			log.error("登录错误"+e.getMessage(),e);
			ServletActionContext.getRequest().setAttribute("errinfo", "未知错误，请联系系统管理员");
			return "loginerror";
		}

	}
	
	public String logout(){
		log.warn("用户退出登录");
		ServletActionContext.getRequest().getSession().removeAttribute("userContext");
		return "loginerror";
	}
	
}
