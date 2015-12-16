<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account</title>
</head>
<body>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<h2 align="center">用户管理--修改密码</h2>
	<%-- <s:debug/> --%>
	<table border="0" align="center">
		<s:form action="spender_update.action" method="post" name="spender"
			theme="simple">
			<s:hidden name="spender.id" value="%{spender.id}" />
			<tr>
				<td align="left" style="padding-left: 7px; padding-bottom: 10px">用户名:</td>
				<td style="padding-left: 5px; padding-bottom: 10px"><s:textfield
						label="用户名" name="spender.name" value="%{spender.name}"
						readonly="true" style="width:195px" /> <a style="color: red">*</a>
					<a id="spnameresult" style="font-size: 10px"></a></td>
			</tr>
			<tr>
				<td align="left" style="padding-left: 7px; padding-bottom: 10px">密码:</td>
				<td style="padding-left: 5px; padding-bottom: 10px"><s:password
						label="密码" name="spender.password" value="" style="width:195px" />
					<!-- <a style="color: red">*</a> --></td>
			<tr>
				<td align="left" style="padding-left: 7px; padding-bottom: 10px">级别:</td>
				<td style="padding-left: 5px; padding-bottom: 10px"><s:textfield 
						label="级别" name="spender.level"   style="width:195px" value="%{spender.level}"
						id="level" /> <!-- <a style="color: red">*</a> --></td>
			<tr>
			<tr>
				<td style="padding-left: 50px; padding-bottom: 10px"><s:submit
						value="更新" />
				</td>
				<td align="right" style="padding-right: 15px; padding-bottom: 10px">
					<input type="button" value="返回" onclick="window.history.back()">
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>