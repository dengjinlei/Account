<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/pages/js/jquery-1.11.3.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/pages/css/table.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/pages/js/listBase.js"></script>
<title>Account</title>
</head>
<body>
<s:debug></s:debug>
	<h2>功能管理-列表</h2>
	----<a href="login_userLogin.action">返回主页</a>
	----<a href="funlist_addPage.action">新增功能</a>
<br><br>
<table class="datalist" align="center">
	<tr align="center">
	<td colspan="3">操作</td>
	<td>序号</td>
	<td>名称</td>
	<td>url</td>
	<td>状态</td>
	<td>级别</td>
	<td>备注</td>
	</tr>
	<s:iterator value="#funLists" status="fl">
	<tr align="center">
		<td ><a href="funlist_updatePage.action?id=${id }">修改</a></td>
		<td ><a href="funlist_delete.action?id=${id }" onclick="return confirm('确认删除？')">删除</a></td> 
		<td ><a href="funlist_changeStatus.action?id=${id }"  >
						<s:if test ="status==0">停用</s:if>
						<s:else>启用</s:else>
					</a>
		</td>
		<td><s:property value="#fl.index+1"/></td>
		<td>${name}</td>
		<td>${url }</td>
		<td><s:if test ="status==0">启用</s:if>
					<s:else>停用</s:else>
		</td>
		<td>
			<s:if test="level==1">系统管理员</s:if>
			<s:elseif test="level==2">企业管理员</s:elseif>
			<s:else>普通用户</s:else>		
		</td>
		<td>${context }</td>
	</tr>
</s:iterator>
	<tr >
		<td align="left" colspan="9">
		<s:if test="#page.currentPage==0">
			<a  >上一页</a>
		</s:if>
		<s:else>
			<a href="funlist_list.action?currentPage=${page.currentPage -1} ">上一页</a>
		</s:else>
		
		<a >第<s:property value="#page.currentPage+1"/>页</a>
		<s:if test="#page.currentPage==#page.totalPage-1">
			<a  >下一页</a>
		</s:if>
		<s:else>
			<a href="funlist_list.action?currentPage=${page.currentPage+1 }">下一页</a>
		</s:else>
		
		<a  >共<s:property value="#page.totalPage"/>页</a>
		</td>
		</tr>
</table>

</body>
</html>