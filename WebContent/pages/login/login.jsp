<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/pages/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
	<%
	String errinfo=(String)request.getAttribute("errinfo");
	if(errinfo==null)
		errinfo="";
	%>
	var err = "<%=errinfo%>";
		if(err !="" ){
			alert(err);
		}
		$("#username").focus();
});
</script>
<script type="text/javascript">
 function check(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		if(username==""){
			alert("用户名不能为空");
			return false;
		}
		if(password==""){
			alert("密码不能为空");
			return false;
		}
		return true;
	}  

</script>
<title>Account</title>
</head>
<body>
<br><br><br><br>
<h2 align="center">登录系统</h2>
<table align="center" >
	<s:form name="userContext" action="login_userLogin.action" theme="simple" method="post" onsubmit="return check()" namespace="/">
	<tr>
		<td>用户名：</td>
		<td><s:textfield name="userContext.name" id="username" style="width:200px"></s:textfield></td>
	</tr>
	<tr>
		<td>密码：</td>
		<td><s:password name="userContext.password" id="password" style="width:200px"></s:password></td>
	</tr>
	
	<tr>
		<td> <s:submit value="登录"> </s:submit></td>
	</tr>
	
	
	</s:form>
</table>
</body>
</html>