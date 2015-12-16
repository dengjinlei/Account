<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="${pageContext.request.contextPath }/pages/js/jquery-1.11.3.js"></script>
<script language="javascript" src="${pageContext.request.contextPath }/pages/js/jquery.form.js"></script>
<script language= "javascript" type="text/javascript" src="<%=request.getContextPath() %>/pages/js/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/css/table.css">
<script type="text/javascript">
/* function clear(){
	
	document.getElementById("sptime").value='';
	var x=document.getElementsByName("costInfo.amtflag");  //获取所有name=costInfo.amtflag的元素  
	    for(var i=0;i<x.length;i++){ //对所有结果进行遍历，如果状态是被选中的，则将其选择取消  
	        if (x[i].checked==true)  
	         {  
	           x[i].checked=false;  
	      	 }  
	    } 

	}  */
	//使用jQuery进行form清除工作
$(function (){
	$("table.datalist tr:nth-child(odd)").addClass("altrow");
	$("#sptime").focus();
	$("input[type=button]:eq(0)").click(function(){
		$("#myform").clearForm();
	});
});
</script>

<title>Account</title>
</head>
<body>
<s:debug></s:debug>
	<h2>账单管理-列表</h2>
	----<a href="login_userLogin.action">返回主页</a>
	----<a href="costinfo_addPage.action">新增账单</a>
	----<a href="costinfo_exportXml.action">导出xml格式账单</a>
	----<a href="costinfo_readXml.action">读取xml格式账单</a>
	<br />	<br />
	<table  style="border-collapse: collapse;width: 60%" align="center" border="0">
	<s:form name="costInfo" id="myform" action="costinfo_list.action" theme="simple" >
		<tr>
			<td align="left" style="padding-left: 7px;padding-bottom: 10px">账单日期:</td>
			<td style="padding-left: 5px;padding-bottom: 10px"><s:textfield name="costInfo.spendTime" id="sptime" class="Wdate" value="%{sptime}" style="width:195px" label="账单日期" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			<%-- <td align="left" style="padding-left: 7px;padding-bottom: 10px">账单类型:</td>
			<td style="padding-left: 5px;padding-bottom: 10px">
			<s:select list="#costtypes" label="花费种类"  name="costInfo.ctid" listKey="id"  listValue="name" headerKey="" headerValue="" style="width:200px" />
			</td> 
		</tr>
		<tr>--%>
				<td align="left" style="padding-left: 7px;padding-bottom: 10px">账目方向:</td>
				<td style="padding-left: 5px;padding-bottom: 10px">
					<s:radio list="#{'-1':'支出','1':'收入'}" label="账目方向"  name="costInfo.amtflag" id="amtflag"  value="%{costInfo.amtflag}" style="width:50px" />
				</td>
				<td align="right" style="padding-right: 15px;padding-bottom: 10px">
					<input type="submit" value="查询"  >
				</td>
				<td align="right" style="padding-right: 15px;padding-bottom: 10px">
					<!-- <input type="button" value="清空"  onclick="clear()"> -->
					<input type="button" value="清空"  >
				</td>
		</tr>
	</s:form>
	</table>
	<br />	<br />
	<!-- <table class="datalist" style ="border-collapse:collapse;width:80%" align="center"  border="1"> -->
	<table class="datalist" align="center" >
		<tr>
			<td align="center"  colspan="2">操作</td>
			<!-- <td align="center">删除</td> -->
			<td align="center">序号</td>
			<td align="center">账单日期</td>
			<td align="center">账单人</td>
			<td align="center">账单类型</td>
			<td align="center">账单方向</td>
			<td align="center">账单金额</td>
			<td align="center">记账人</td>
			<td align="center">备注</td>
		</tr>
		<s:iterator value="#costinfos" status="ct">
		<tr>
				<td align="center"><a href="costinfo_updatePage.action?id=${id }">修改</a></td>
				 <td align="center"><a href="costinfo_delete.action?id=${id }" onclick="return confirm('确认删除？')">删除</a></td> 
				<%-- <td align="center"><a href="costinfo_delete.action?id=${id }"+  onclick="javascript:window.confirm('确认删除？');">删除</a></td> --%>
				<td align="center"><s:property value="#ct.index+1" /></td>
				<td align="center"><s:date name="spendTime" format="yyyy-MM-dd"/></td>
				<td align="center"><s:property value="spname"/></td>
				<td align="center"><s:property value="ctname"/></td>
				<td align="center"><s:if test="amtflag==1">收入</s:if><s:elseif test="amtflag==-1">支出</s:elseif></td>
				<td align="center"><s:property value="amt"/></td>
				<td align="center"><s:property value="acname"/></td>
				<td align="center" width="250px"><s:property value="comment"/></td>
			</tr>
		</s:iterator>
		<tr >
		<td align="left" colspan="10">
		<s:if test="#page.currentPage==0">
			<a  >上一页</a>
		</s:if>
		<s:else>
			<a href="costinfo_list.action?currentPage=${page.currentPage -1} ">上一页</a>
		</s:else>
		
		<a >第<s:property value="#page.currentPage+1"/>页</a>
		<s:if test="#page.currentPage==#page.totalPage-1">
			<a  >下一页</a>
		</s:if>
		<s:else>
			<a href="costinfo_list.action?currentPage=${page.currentPage+1 }">下一页</a>
		</s:else>
		
		<a  >共<s:property value="#page.totalPage"/>页</a>
		</td>
		</tr>
	</table>
</body>
</html>