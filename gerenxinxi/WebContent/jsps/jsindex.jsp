<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统主页</title>
<style type="text/css">
#jiaoxuexinxi {
	text-align: center;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${requestScope.msg=='save' }">
			<script type="text/javascript">
				alert("保存成功!");
			</script>
		</c:when>
		<c:when test="${requestScope.msg=='update' }">
			<script type="text/javascript">
				alert("更新成功!");
			</script>
		</c:when>
		
	</c:choose>

	<div id="jiaoxuexinxi">
		<button id="btn" onclick="go()">个人信息</button>
	</div>
</body>
<script type="text/javascript">
	function go() {
		location.href = "${pageContext.request.contextPath}/JsIndexServlet";
	}
</script>
</html>