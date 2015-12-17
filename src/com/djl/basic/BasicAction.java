package com.djl.basic;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BasicAction extends ActionSupport {

	private static final long serialVersionUID = 6793804162671380657L;
	
	protected static Logger log = Logger.getLogger(BasicAction.class); 
	
	public String addPage(){
		return "add";
	}
	
	/**
	 * 向客户端返回执行结果
	 * @param code 返回代码0：成功；1：失败
	 * @param msg 返回结果消息
	 * @author  dengjinlei
	 */
	public void forword(int code , String msg)  {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			response.getWriter().write("{\"code\":"+code+",\"msg\":\""+msg+"\"}");
		} catch (IOException e) {
			log.error("返回结果方法错误"+e.getMessage(),e);
		}
	}
	
}
