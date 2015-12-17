package com.djl.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.djl.domain.Spender;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//			System.out.println("开始校验");
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			HttpSession session = servletRequest.getSession();
			
			String path = servletRequest.getRequestURI();
			System.out.println(path);
			
			if(path.indexOf("jsp")>-1||path.indexOf("action")>-1){
				if( path.indexOf("login_userLogin")>-1){ 
//					System.out.println("登录页面跳过校验");
					chain.doFilter(servletRequest, servletResponse);
				}
				else{
					Spender user = (Spender) session.getAttribute("userContext"); 
					if(user==null){
						//未登录，进行登录校验
//						System.out.println("校验不通过，跳转到登录页面!!!");
						servletResponse.sendRedirect("login_userLogin.action");
					}else{
//						System.out.println("校验通过，继续操作 , 登录用户："+user.getName());
						chain.doFilter(servletRequest,servletResponse);
					}
				}
			}
			else{
//				System.out.println("不在校验范围，跳过校验");
				chain.doFilter(servletRequest, servletResponse);
			}
			
			
			
			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
