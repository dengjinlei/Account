<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script language= "javascript" type="text/javascript" src="<%=request.getContextPath() %>/pages/js/WdatePicker.js"></script>
 <script type="text/javascript">
 	function check(costInfo){
 		var zdspid = document.getElementById("zdspid").value;
 		var zdctid = document.getElementById("zdctid").value;
 		var zdacid = document.getElementById("zdacid").value;
 		var zdrq = document.getElementById("zdrq").value;
 		var zdamt = document.getElementById("zdamt").value;
 		var testnum = /^[1-9]+.?[0-9]*$/;
 		if(zdspid==""){
 			alert("账单人不能为空！");
 			return false;
 		}
 		if(zdrq==""){
 			alert("账单日期不能为空！");
 			return false;
 		}
 		if(zdctid==""){
 			alert("账单类型不能为空！");
 			return false;
 		}
 		if(zdamt!=""){
 			if(!testnum.test(zdamt)){
 				alert("账单金额请录入数字！");
 				return false;
 			}
 		}
 		if(zdacid==""){
 			alert("记账人不能为空!");
 			return false;
 		}
         return true;
 	}
 	
 </script>
<title>Account</title>
</head>
<body>
<br/><br/><br/><br/><br/><br/>
<h2 align="center">账单管理--添加账单</h2>
<%-- <s:debug/> --%>
<table border="0" align="center">
<s:form action="costinfo_add.action" method="post" name="costInfo" theme="simple" onsubmit=" return check(this);" > 
	<s:hidden name="costInfo.id"/>
	<tr>
		<td align="left"  style="padding-left: 7px;padding-bottom: 10px">账单人:</td>
		<td style="padding-left: 5px;padding-bottom: 10px">
		<s:select list="#spenders" label="账单人"  name="costInfo.spid" id="zdspid"  listKey="id" listValue="name"   headerKey="" headerValue="" style="width:200px" />
		<a style="color: red">*</a></td>
	</tr>
	<tr>
		<td align="left" style="padding-left: 7px;padding-bottom: 10px">账单日期:</td>
		<td style="padding-left: 5px;padding-bottom: 10px"><s:textfield name="costInfo.spendTime" id ="zdrq" class="Wdate" style="width:195px" label="账单日期" onclick="WdatePicker({})" />
				<a style="color: red">*</a></td>
	</tr>
	<tr>
		<td align="left" style="padding-left: 7px;padding-bottom: 10px">账单类型:</td>
		<td style="padding-left: 5px;padding-bottom: 10px">
			<s:select list="#costtypes" label="花费种类"  name="costInfo.ctid" id ="zdctid" listKey="id"  listValue="name" headerKey="" headerValue="" style="width:200px" />
			<a style="color: red">*</a></td>
	</tr>
	<tr>
		<td align="left" style="padding-left: 7px;padding-bottom: 10px">账单金额:</td>
		<td style="padding-left: 5px;padding-bottom: 10px">
			<s:textfield name="costInfo.amt" value="" style="width:195px" id="zdamt" />
			</td>
	</tr>
	<tr>
		<td align="left" style="padding-left: 7px;padding-bottom: 10px">记账人:</td>
		<td style="padding-left: 5px;padding-bottom: 10px">
			<s:select list="#spenders" label="记账人"  id="zdacid" name="costInfo.acid"  listKey="id" listValue="name"   headerKey="" headerValue=""  style="width:200px" />
				<a style="color: red">*</a></td>
	</tr>
	<tr>
		<td align="left" style="padding-left: 7px;padding-bottom: 10px">备注:</td>
		<td style="padding-left: 5px;padding-bottom: 10px"><s:textarea name="costInfo.comment" label="备注" style=" width:195px "/></td>
	</tr>
	<tr>
			<td style="padding-left: 50px;padding-bottom: 10px">
			<s:submit value="保存"  /></td>
			<td align="right" style="padding-right: 15px;padding-bottom: 10px"><input type="button" value="返回" onclick="window.history.back()"></td>		
	</tr>
</s:form>
</table>
</body>
</html>