<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account</title>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/pages/js/jquery-1.11.3.js"></script>
<%-- <script type="text/javascript">
$(function(){
	 alert("操作失败！"+${ });
	 window.location = "<%=request.getAttribute("bean")+"_list.action"%>";
}); --%>
</script>
</head>
<body>
<h3>操作失败</h3>
<p>${errorinfo }</p>
<a href="${bean}_list.action">返回列表</a><br/>
<a href="${beanback }.action">返回重新添加</a><br>
<a href="${pageContext.request.contextPath }/index.jsp">返回主页</a>
</body>
</html>