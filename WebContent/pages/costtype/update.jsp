<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/pages/js/jquery-1.11.3.js"></script>
<title>Account</title>
 <script type="text/javascript">
 	function check(){
 		var zmmc = document.getElementById("zmmc").value;
 		if(zmmc==""){
 			alert("账目名称不能为空！");
 			return false;
 		}
         return true;
 	}
 	function update(){
 		if(check()){
 		$.post("costtype_update.action", $("form[name='costType']").serializeArray(),
 		function(data){
 				if(data.code==0){
 					alert(data.msg);
 					window.location="costtype_list.action";
 				}
 			});
 		}
 	}
 </script>
</head>
<body>
<br/><br/><br/><br/><br/><br/>
<h2 align="center">账目种类-修改</h2>
<%-- <s:debug/> --%>
<table align="center" border="0">
<s:form action="costtype_update.action" method="post" name="costType"  theme="simple" onsubmit=" return check();"  > 
	<s:hidden name="costType.id"/>
	<tr>
	<td align="left"  style="padding-left: 7px;padding-bottom: 10px">账目名称：</td>
	<td style="padding-left: 5px;padding-bottom: 10px">
		<s:textfield label="账目名称"  name="costType.name" id="zmmc" value="%{costType.name}" style="width:195px"/>
		<a style="color: red">*</a>
	</td>
	</tr>
	<tr>
	<td align="left"  style="padding-left: 7px;padding-bottom: 10px">账目方向：</td>
	<td style="padding-left: 5px;padding-bottom: 10px">
		<s:radio list="#{'-1':'支出','1':'收入'}" label="账目方向"  name="costType.amtflag" value="%{costType.amtflag}" style="width:50px" />
	</td>
	</tr>
	<tr>
	<td align="left"  style="padding-left: 7px;padding-bottom: 10px">备注：</td>
	<td style="padding-left: 5px;padding-bottom: 10px">
		<s:textfield label="备注"  name="costType.context" value="%{costType.context}" style="width:200px" />
	</td>
	</tr>
	<tr>
			<td style="padding-left: 50px;padding-bottom: 10px">
			<input type="button" onclick="update();" value="更新"  /></td>
			<td align="right" style="padding-right: 15px;padding-bottom: 10px"><input type="button" value="返回" onclick="window.history.back()"></td>		
	</tr>
	</s:form>	
	</table>
</body>
</html>