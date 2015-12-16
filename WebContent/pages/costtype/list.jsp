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
	<h2>账目种类-列表</h2>
	----<a href="login_userLogin.action">返回主页</a>
	----<a href="costtype_addPage.action">新增账目</a>
	<br /> <br />
	<table class="datalist" style="width: 80%; border-collapse: collapse;" cellspacing="0"" border="1" align="center">
		<tr >
			<td align="center"  colspan="2">操作</td>
			<!-- <td align="center">删除</td> -->
			<td align="center">序号</td>
			<td align="center">种类名称</td>
			<td align="center">账目方向</td>
			<td align="center">备注</td>
		</tr>
		<s:iterator value="#costtypes" status="ct">
			<tr>
				<td align="center"><a href="costtype_updatePage.action?id=${id }">修改</a></td>
				<td align="center"><a href="costtype_delete.action?id=${id }" onclick="return confirm('确认删除？')">删除</a></td>
				<td align="center"><s:property value="#ct.index+1" /></td>
				<td align="center"><s:property value="name" /></td>
				<td align="center"><s:if test="amtflag==1">收入</s:if><s:else>支出</s:else></td>
				<td align="center" width="200px" ><s:property value="context" /></td>
			</tr>
		</s:iterator>
	</table>
	

</body>
</html>