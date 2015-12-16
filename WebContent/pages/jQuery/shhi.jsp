<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/pages/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
	var t=true;
	var s = $("ul li:hidden");
	 $("a").click(function(){
		 if(t){
			 s.show();
			 $("a").html("简化");
			 t=false;
		 }else{
			 t=true;
			 s.hide();
			 $("a").html("更多");
	 	}
	 });
});
</script>
<title>showHidden</title>
</head>
<body>
<a>更多</a>
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
</body>
</html>