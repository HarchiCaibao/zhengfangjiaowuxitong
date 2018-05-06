<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教学质量评估</title>
<style type="text/css">
#form {
	margin-top: 50px;
	margin-left: 300px;
}

#message {
	margin-top: 60px;
	margin-left: 300px;
}

#pinggu {
	margin-top: 60px;
	margin-left: 680px;
}

#save {
	margin-top: 50px;
	margin-left: 580px;
}

#jianjie {
	margin-top: 10px;
	margin-left: 300px;
}
#welcome {
	margin-left: 1110px;
}
</style>

</head>
<body>

	<div id="pinggu">
		<button id="pjbtn" onclick="go()">教学质量评估</button>
	</div>
	<div id="welcome">学号：${sessionScope.xuehao }同学，欢迎你</div>
	<div id="message">
		评估说明：<br>
		<div id="first"><font size="2px">1、请根据实际情况给每位老师打上恰当的分数，满分为10分。</font></div>
		<br>
		<div id="second"><font size="2px">2、我们将采用无记名教学质量评估，请大胆说出你的建议。</font></div>
		<br>
	</div>
	<div id="jianjie">
		教学班：${jxbbianhao }&nbsp;&nbsp;&nbsp;教师名称：${jiaoshi.xingming }<br>
		教师简介：${jiaoshi.beizhu }<br>
	</div>
	<c:choose>
		<c:when test="${requestScope.fail=='10' }">
			<script type="text/javascript">
				alert("评分不能全为10分，请重新评价!");
				location.href = "${pageContext.request.contextPath }/XsJiaoshiServlet?gonghao=${jiaoshi.gonghao }&jxbbianhao=${jxbbianhao}";
			</script>

		</c:when>
		<c:when test="${requestScope.fail=='1' }">
			<script type="text/javascript">
				alert("评分不能全为1分，请重新评价!");
				location.href = "${pageContext.request.contextPath }/XsJiaoshiServlet?gonghao=${jiaoshi.gonghao }&jxbbianhao=${jxbbianhao}";
			</script>
		</c:when>
		<c:when test="${sessionScope.msg!='yes' }">
			<div id="form">
				<form
					action="${pageContext.request.contextPath }/XsPingjiaoServlet?jxbbianhao=${jxbbianhao }&gonghao=${jiaoshi.gonghao}"
					method="POST" name="pingjiao">
					老师不迟到，不随意旷课，不早退&nbsp; <select name="chuqin">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
						<option>8</option>
						<option>9</option>
						<option>10</option>
					</select><br> 
					老师课程生动有趣,不照本宣科&nbsp; <select name="ketang">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
						<option>8</option>
						<option>9</option>
						<option>10</option>
					</select><br> 
					老师善于与学生沟通，课后积极解决学生的问题&nbsp; <select name="ssguanxi">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
						<option>8</option>
						<option>9</option>
						<option>10</option>
					</select><br>
					老师根据课程进度和我们的接受能力，合理布置作业&nbsp; <select name="zuoyeliang">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
						<option>8</option>
						<option>9</option>
						<option>10</option>
					</select><br> 
					学完本门课程，我感到有所收获&nbsp; <select name="shouhuo">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
						<option>8</option>
						<option>9</option>
						<option>10</option>
					</select><br> 
					
					建议：<br>
					<textarea rows="7" cols="40" name="jianyi"  >
			 </textarea>
					<div id="save">
						<input type="submit" value="保存">
					</div>
				</form>
			</div>
		</c:when>

		<c:otherwise>
			<script type="text/javascript">
				alert("恭喜你完成了教学评估！");
				location.href = "${pageContext.request.contextPath }/index.jsp";
			</script>
		</c:otherwise>
	</c:choose>
</body>

<script type="text/javascript">
	function go() {
		location.href = "${pageContext.request.contextPath }/XsXueshengServlet";
	}
</script>
</html>