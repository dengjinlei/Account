<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
function P (name,sex){
	this.name = name;
	this.sex = sex;
}
 function aaa(){
	 /* console.log("显示一下"); */
	alert("a");
	var pp = new P("pp","M");
	alert(pp.name);
	
} 
 function cc(){
	 var n = {'name':'王',
						'sex':'M'	 		
	 };
	 alert(n.name);
 }
</script>
<title>Ajax_Json</title>
</head>
<body>

<form>
<input type="button" onclick="cc();"  value="点"/>
</form>
</body>
</html>