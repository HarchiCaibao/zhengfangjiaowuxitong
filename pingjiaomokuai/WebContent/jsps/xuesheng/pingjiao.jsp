<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教学质量评估</title>
<style type="text/css">
#jiaoshi {
	margin-top: 200px;
	margin-left: 300px;
}

#welcome {
	margin-left: 1110px;
}

#pinggu {
	margin-top: 18px;
	margin-left: 680px;
}
</style>

</head>
<body>
	<div id="welcome">学号：${sessionScope.xuehao }同学，欢迎你</div>
	<br>
	<div id="pinggu">
		<button id="pjbtn" onclick="go()">教学质量评估</button>
	</div>
	<br>
	<div id="jiaoshi">
		<c:choose>
			<c:when test="${sessionScope.msg!='yes' }">
				<table width="300" cellspacing="0">
					<tr>
						<td>课程名称</td>
						<td>教学班号</td>
						<td>教师名称</td>
					</tr>
					<c:forEach var="jiaoshi" items="${jiaoshis }">
						<tr>
							<td>${jiaoshi.kecheng.mingcheng }</td>
							<td>${jiaoshi.jiaoxueban.jxbbianhao }</td>
							<td>${jiaoshi.xingming }</td>
							<td>
							<c:set var="contains" value="no" />
							<c:forEach var="list" items="${jxbbianhaos }" begin="0" end="${fn:length(jxbbianhaos)}">
								<c:if test="${list == jiaoshi.jiaoxueban.jxbbianhao }">
									<c:set var="contains" value="yes" />
								</c:if>
							</c:forEach>
								<c:choose>
									<c:when test="${contains=='yes' }">
							       	<a href="javascript:return false;">已评教</a>
							   </c:when>
							   <c:otherwise>
							   		 <a href="${pageContext.request.contextPath }/XsJiaoshiServlet?gonghao=${jiaoshi.gonghao }&jxbbianhao=${jiaoshi.jiaoxueban.jxbbianhao}">评教</a>		
							   </c:otherwise>
							       </c:choose>
							   </td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					function finish() {
						alert("你已完成教学质量评估，不能重复评价！");
						location.href = "${pageContext.request.contextPath }/index.jsp";
					}
					finish();
				</script>
			</c:otherwise>
		</c:choose>
	</div>
</body>

<script type="text/javascript">
	function go() {
		location.href = "${pageContext.request.contextPath }/XsXueshengServlet";
	}
</script>
</html>