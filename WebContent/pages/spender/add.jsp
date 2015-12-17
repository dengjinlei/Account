<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.lang.Math"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/pages/js/jquery-1.11.3.js"></script>
<title>Account</title>
</head>
<script type="text/javascript">
//通过jQuery的ajax验证用户名是否重复
function uniqueCheck(){
	var spname = document.getElementById("spname").value;
	var xhr = new XMLHttpRequest();
	if(spname!=""){
		//ajax的缓存机制导致添加一个用户后，再添加此用户时验重方法取得是缓存的结果，所以在请求结尾添加随机数
		//让ajax每次都发出查询请求  或者+timestamp= new Date().getTime()
		$.get("spender_checkUnique.action" , {spname:spname , t:Math.random()} , 
			function(data){
				$("#spnameresult").html(data.msg);
		},"json"
		);
	}
}
function save(){
	var spname = $("#spname").val();
	if(spname!=""){
		 $.post("spender_add.action", $("form[name='spender']").serializeArray(),
				 function(data){
			 			if(data.code==0){
			 				alert(data.msg);
			 				location.href="spender_list.action";
			 			}else
			 				alert(data.msg);
		 },"json");
	}else
		alert("用户名不能为空！");
}
/* 	xhr.open("GET", "spender_checkUnique.action?spname="+spname+"&t="+Math.random());
		xhr.setRequestHeader("Context-Type", "Charset=UTF-8");  
		xhr.send();
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4){
				if(xhr.status==200){
					 //document.getElementById("spnameresult").innerHTML=xhr.responseText; 
					$("#spnameresult").html(xhr.responseText);
					return true;
				}else {
					alert("发生错误"+xhr.status);
					return false;
				}
			}
		} */
</script>
<!-- <body onload="saveResult()"> -->
<body>
<br/><br/><br/><br/><br/><br/>
<h2 align="center">用户管理--添加用户</h2>
<table border="0" align="center">
<s:form action="spender_add.action" method="post" name="spender"  theme="simple"   > 
 <s:hidden name="sender.id"/>
	<tr>
		<td align="left" style="padding-left: 7px;padding-bottom: 10px">用户名:</td>
		<td style="padding-left: 5px;padding-bottom: 10px">
			<s:textfield label="用户名"  name="spender.name" value="" style="width:195px" id="spname"  onchange="uniqueCheck()"/>
			<a style="color: red">*</a>
			<a id="spnameresult" style="font-size: 10px"></a>
			</td>
	</tr>
 	<tr>
		<td align="left" style="padding-left: 7px;padding-bottom: 10px">密码:</td>
		<td style="padding-left: 5px;padding-bottom: 10px">
			<s:password label="密码" name="spender.password" value="" style="width:195px" id="sppwd" />
			<!-- <a style="color: red">*</a> -->
			 </td>
				<tr>
	 	<tr>
		<td align="left" style="padding-left: 7px;padding-bottom: 10px">级别:</td>
		<td style="padding-left: 5px;padding-bottom: 10px">
			<s:textfield label="级别" name="spender.level" value="3" style="width:195px" id="level" />
			<!-- <a style="color: red">*</a> -->
			 </td>
				<tr>				
			<td style="padding-left: 50px;padding-bottom: 10px">
			<input type="button" value="保存" onclick="save();"></td>
			<td align="right" style="padding-right: 15px;padding-bottom: 10px">
				<input type="button" value="返回" onclick="window.history.back()"></td>		
	</tr>
</s:form>
</table>

</body>
</html>