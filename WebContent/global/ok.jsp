<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/pages/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
	 alert("操作成功！");
	 window.location = "<%=request.getAttribute("bean")+"_list.action"%>";
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account</title>
</head>
<body>
<h3>操作成功</h3>

<%-- <a href="${bean}_list.action">返回列表</a><br/>
<a href="login_userLogin.action">返回主页</a> --%>
</body>
</html>