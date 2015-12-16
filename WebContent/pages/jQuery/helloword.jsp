<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="${pageContext.request.contextPath }/pages/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
 $(function(){  
	 $("div:last").html("jQuery添加文字");
	 $("div:eq(1)").css("color","red");
	 $("div:last").attr("class",function(){return $("#msg").attr("id");});
	var str="";
	$("#btnShow").bind("click" , function(){$("#msg").show();} );
	$("#btnHidden").bind("click" , function(){$("#msg").hide();});
	$("#btnChange").bind("click" , function(){
		str = prompt("请输入更改文字：","");
		$("#msg").html(str);
		$("#msg").show();	
	});
	$("#btn").bind("click" , function(){
		var s = $("#msg:hidden").length;
		if(s=="0")
			$("#msg").fadeOut(2000);
		else
			$("#msg").fadeIn(2000);
	});
		
 });  
</script>
<style type="text/css">
<!--
 .msg{
	width: 300px;
	height: 100px;
	position: relative;
	margin:20px;
/* 	top: 100px;
	left: 100px;  */
 	background-color: gray;
	text-align: center;
	text-shadow: orange;
}
-->
</style>
<title>hello World </title>

</head>
<body>
<div id="msg"  class="msg">Hello World</div><br>
<div></div>
<input type="button" value="显示" id="btnShow">
<input type="button" value="隐藏" id="btnHidden">
<input type="button" value="显示/隐藏" id="btn">
<input type="button" value="更改文字" id="btnChange">
</body>
</html>