<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/pages/js/jquery-1.11.3.js"></script>
<style type="text/css">
div{
	position: relative;
	top: 20px;
	left: 30px;
}
</style>
<script type="text/javascript">
$(function(){
	var t=true;
	var s = $("ul li:hidden");
	 $("#more").click(function(){
		 if(t){
			 s.show();
			 $("#more").html("简化");
			 t=false;
		 }else{
			 t=true;
			 s.hide();
			 $("#more").html("更多");
	 	}
	 });
});
$(function(){
		$("#submit").bind("click", function(){
			var a = $("#a").val();
			var b =$("#b").val();
			$.ajax({
				url:"charts_tt.action?a="+a+"&b="+b,
				dataType:"text",
				success:function(data){
					alert(data);
				}
			});
		});
});
$(function(){
	$("#post").bind("click",function(){
		$.post("charts_pp.action" , {
			a:$("#a").val(),
			b:$("#b").val()
		}, function(data){
			alert(data);
		});
	});
});
</script>
<title>showHidden</title>
</head>
<body>
<a id="more">更多</a><br><br><br>
<a onclick="window.history.back()">返回</a>
<ul>
	<li>111</li>
	<li>222</li>
	<li>333</li>
	<li  style="display: none" >444</li>
	<li>555</li>
	<li  style="display: none">666</li>
	<li>777</li>
	<li  style="display: none">888</li>
	<li>999</li>
</ul>
<div id="test" >
	<input type="text" name="a" id="a" /><br>
	<input type="text" name="b" id="b"/><br>
	<input type="button" id="submit" value="提交"><br>
	<input type="button" id="post" value="post">
</div>
</body>
</html>