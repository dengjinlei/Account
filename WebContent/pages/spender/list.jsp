<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/css/table.css">
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/pages/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
	$("table.datalist tr:nth-child(odd)").addClass("altrow");
});
</script>
<title>Account</title>
</head>
<body>
	<h2>用户管理-列表</h2>
	----<a href="login_userLogin.action">返回主页</a>
	----<a href="spender_addPage.action">新增用户</a>
	<br />	<br />
<table class="datalist" style="width: 60%; border-collapse: collapse;" cellspacing="0"" border="1" align="center">
	<tr >
		<td align="center"  colspan="2">操作</td>
			<!-- <td align="center">删除</td> -->
		<td align="center">序号</td>
		<td align="center">用户名</td>
		<td align="center">级别</td>
	</tr>
<s:iterator value="#spenders" status="sd">
	<tr>
		<td align="center"><a href="spender_updatePage.action?id=${id }">修改</a></td>
		<td align="center"><a href="spender_delete.action?id=${id }" onclick="return confirm('确认删除？')">删除</a></td>
		<td align="center"><s:property value="#sd.index+1" /></td>
		<td align="center"><s:property value="name" /></td>
		<td align="center">
			<s:if test="level==1">系统管理员</s:if>
			<s:elseif test="level==2">企业管理员</s:elseif>
			<s:else>普通用户</s:else>
		</td>
	</tr>
</s:iterator>	
</table>
</body>
</html>