<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/pages/js/jquery-1.11.3.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
table{
	border:0px ;	/* 表格边框 */
	font-family:Arial;
	font-size:14px;
	position: absolute;
	top:25%;
	left: 40%;
}
tr{
	padding-top:5px; padding-bottom:5px;
	padding-left:12px; padding-right:12px;
}
table.leftlab{
	text-align:right;
}
</style>

<title>Account</title>
</head>
<body>

<table align="center">
<s:form name="funList"  action="funlist_update.action" method="post" theme="simple" >
	<input type="hidden" name="funList.id" value="${funList.id }"/>
	<tr>
		<td class="leftlab">名称:</td>
		<td><input name="funList.name"  type="text" value="${funList.name} "/>
		<a style="color: red">*</a>
		</td>
	</tr>
		<tr>
		<td class="leftlab">url:</td>
		<td><input name="funList.url"  type="text" value="${funList.url }"/>
		</td>
	</tr>
	<tr>
		<td class="leftlab">状态:</td>
		<td><input name="funList.status"  type="text" value="${funList.status}"/>
		</td>
	</tr>
	<tr>
		<td class="leftlab">位置:</td>
		<td><input name="funList.position" value="${funList.position }" type="text"/>
		</td>
	</tr>
	<tr>
		<td class="leftlab">级别:</td>
		<td><input name="funList.level"  type="text" value="${funList.level}"/>
		</td>
	</tr>
	<tr>
		<td class="leftlab">备注:</td>
		<td><input name="funList.context"  type="text" value="${funList.context}"/>
		</td>
	</tr>
	<tr>
		<td><input type="submit" value="更新"  id="upbtn" />
		</td>
		<td align="right"><input type="button" value="返回" onclick="window.history.back()"/>
		</td>
	</tr>
</s:form>
</table>
</body>
</html>