<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
h1{
	margin-left: 30px;
}
h2 a{
	margin-left: 60px;
}
h3 a{
	margin-left: 90px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account</title>
</head>
<body>
<h1 >记账系统-功能列表</h1>
<h2 ><a href="login_logout.action">注销</a></h2>
<s:iterator value="#funlist" status="fl">
	<h3 ><a href="${url }">${name }</a></h3>
</s:iterator> 

<%-- <h2 ><a href="login_logout.action">注销</a></h2>
<h3 ><a href="costtype_list.action">----1.0 账目类型</a></h3>
<h3 ><a href="spender_list.action">----2.0 用户管理</a></h3>
<h3 ><a href="costinfo_list.action">----3.0 账单管理</a></h3>
<h3><a href="<%=request.getContextPath()%>/jspdemo/index.htm">----4.0 图表测试index</a></h3>
<h3><a href="charts_test.action">----4.1 图表测试01</a></h3>
<h3><a href="charts_test01.action">----4.2 图表测试02</a></h3>
<h3><a href="<%=request.getContextPath()%>/pages/jQuery/helloword.jsp">----5.1 jQuery</a></h3>
<h3><a href="<%=request.getContextPath()%>/pages/jQuery/shhi.jsp">----5.2 showHide</a></h3>
<h3><a href="funlist_list.action">----6.0 功能列表管理</a></h3> --%>
</body>
</html>