<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看教学质量评估详情</title>
<style type="text/css">
#message {
	margin-top: 60px;
	margin-left: 300px;
}

#xiangqing {
	margin-top: 30px;
	margin-left: 300px;
}

#jianyi {
	margin-left: 70px;
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
	<c:choose>
		
		<c:when test="${requestScope.nofinish=='noselect' }">
			<script type="text/javascript">
				alert("该门课程尚未有学生选修！");
				location.href = "${pageContext.request.contextPath }/JsJiaoxuebanServlet";
			</script>
		</c:when>

		<c:when test="${requestScope.nofinish=='nofinish' }">
			<script type="text/javascript">
				alert("学生未完全完成教学质量评估。请过段时间再查看！");
				location.href = "${pageContext.request.contextPath }/JsJiaoxuebanServlet";
			</script>
		</c:when>
		<c:otherwise>
		<div id="welcome">工号：${sessionScope.gonghao }老师，欢迎你</div>
			<div id="pinggu">
				<button id="pjbtn" onclick="go()">教学质量评估</button>
			</div>
			<div id="message">
				查阅说明：<br>
				<div id="first">
					<font size="2px">1、每项评估分数均为学生评分的加权平均分，满分为10分。</font>
				</div>
				<br>
				<div id="second">
					<font size="2px">2、学生评教时有写建议的，建议都统计整理好，请各位教师耐心查阅建议，有则改之，无则加勉。</font>
				</div>
				<br>
			</div>

			<div id="xiangqing">
				<table>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;评估项目</td>
						<td>学生评分</td>
					</tr>

					<tr>
						<td>我不迟到，不随意旷课，不早退</td>
						<td>&nbsp;&nbsp;&nbsp;<fmt:formatNumber
								value="${pjshujuAvgs.chuqinAvg }" pattern="0.00"></fmt:formatNumber></td>
					</tr>

					<tr>

						<td>我的课程生动有趣,不照本宣科</td>
						<td>&nbsp;&nbsp;&nbsp;<fmt:formatNumber
								value="${pjshujuAvgs.ketangAvg }" pattern="0.00"></fmt:formatNumber></td>
					</tr>

					<tr>
						<td>我善于与学生沟通，课后积极解决学生的问题</td>
						<td>&nbsp;&nbsp;&nbsp;<fmt:formatNumber
								value="${pjshujuAvgs.ssguanxiAvg }" pattern="0.00"></fmt:formatNumber></td>
					</tr>

					<tr>
						<td>我会根据课程进度和学生的接受能力，合理布置作业</td>
						<td>&nbsp;&nbsp;&nbsp;<fmt:formatNumber
								value="${pjshujuAvgs.zuoyeliangAvg }" pattern="0.00"></fmt:formatNumber></td>
					</tr>

					<tr>
						<td>学生学完我的课程，学生感到有所收获</td>
						<td>&nbsp;&nbsp;&nbsp;<fmt:formatNumber
								value="${pjshujuAvgs.shouhuoAvg }" pattern="0.00"></fmt:formatNumber></td>
					</tr>
				</table>
				<br> 学生建议：<br>
				<div id="jianyi">
					<c:forEach var="jianyis" items="${requestScope.jianyis }"
						varStatus="vs">
			${vs.count} 、${jianyis }<br>
					</c:forEach>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>

<script type="text/javascript">
	function go() {
		location.href = "${pageContext.request.contextPath }/JsJiaoxuebanServlet";
	}
</script>

</html>