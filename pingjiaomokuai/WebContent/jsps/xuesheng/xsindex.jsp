<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生系统主页</title>
<style type="text/css">
#pinggu {
	margin-top: 18px;
	margin-left: 680px;
}
</style>

</head>
<body>
	<h2 >学生界面</h2>
	<br>
	<div id="pinggu">
		<button id="pjbtn" onclick="go()">教学质量评估</button>
	</div>
</body>

<script type="text/javascript">
	function go() {
		//跳转到servlet以查询数据库判断学生是否完成评教
		location.href = "${pageContext.request.contextPath }/XsXueshengServlet";
	}
</script>
</html>