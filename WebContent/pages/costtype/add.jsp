<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account</title>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/pages/js/jquery-1.11.3.js"></script>
 <script type="text/javascript">
 	function check(){
 		var zmmc = document.getElementById("zmmc").value;
 		if(zmmc==""){
 			alert("账目名称不能为空！");
 			return false;
 		}
         return true;
 	}
 	function save(){
 		if(check()){
 			$.post("costtype_add.action",$("#ctform").serializeArray(),
 				function(data){
 				/*var jdata = JSON.parse(data);
 				console.log("---");
 				console.dir(jdata);
 				alert(jdata); */
 				if(data.code==0){
 				alert(data.msg);
 				//以下两行均可完成页面跳转
 				/* window.location="costtype_list.action"; */
 				location.href="costtype_list.action";
 				<%-- <%response.sendRedirect("costtype_list.action");%> --%>
 				}
 			},"json"
 			);
 		}
 	}
 </script>
</head>
<body>
<br/><br/><br/><br/><br/><br/>
<h2 align="center">账目种类-增加</h2>
<%-- <s:debug/> --%>
<table align="center" border="0">
<%-- <s:form action="costtype_add.action" method="post" id="ctform" name="costType"  theme="simple" onsubmit=" return check();"  > --%>
<s:form action="costtype_add.action" method="post" id="ctform" name="costType"  theme="simple"  > 
	<s:hidden name="costType.id"/>
	<tr>
	<td align="left"  style="padding-left: 7px;padding-bottom: 10px">账目名称：</td>
	<td style="padding-left: 5px;padding-bottom: 10px">
		<s:textfield label="账目名称"  name="costType.name" id="zmmc" value=""  style="width:195px"/>
		<a style="color: red">*</a>
	</td>
	</tr>
	<tr>
	<td align="left"  style="padding-left: 7px;padding-bottom: 10px">账目方向：</td>
	<td style="padding-left: 5px;padding-bottom: 10px">
		<s:radio list="#{'-1':'支出','1':'收入'}" label="账目方向"  name="costType.amtflag"   value="-1" style="width:50px" />
	</td>
	</tr>
	<tr>
	<td align="left"  style="padding-left: 7px;padding-bottom: 10px">备注：</td>
	<td style="padding-left: 5px;padding-bottom: 10px">
		<s:textfield label="备注"  name="costType.context" value="" style="width:200px" />
	</td>
	</tr>
	<tr>
			<td style="padding-left: 50px;padding-bottom: 10px">
				<%-- <s:submit value="保存"  /> --%>
				<input type="button" value="保存" onclick="save(); " id="savebtn">
			</td>
			<td align="right" style="padding-right: 15px;padding-bottom: 10px"><input type="button" value="返回" onclick="window.history.back()"></td>		
	</tr>
	</s:form>	
</table>

</body>
</html>