<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师系统主页</title>
<style type="text/css">
#pinggu {
	margin-top: 18px;
	margin-left: 680px;
}
</style>

</head>
<body>
	<h1>教师界面</h1>
	<div id="pinggu">
		<button id="pjbtn" onclick="go()">教学质量评估</button>
	</div>

</body>
<script type="text/javascript">
	function go() {
		location.href = "${pageContext.request.contextPath }/JsJiaoxuebanServlet";
	}
</script>

</html>