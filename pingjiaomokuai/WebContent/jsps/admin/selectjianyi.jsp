<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>筛选学生评教建议</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<style type="text/css">
#title {
	font-family: verdana, arial, sans-serif;
	font-size: 28px;
	color: #6B808F;
	left: 550px;
	top: 88px;
	position: absolute;
}

table.xinxitable {
	font-family: verdana, arial, sans-serif;
	font-size: 15px;
	color: #666666;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	left: 388px;
	top: 138px;
	width: 568px;
	position: absolute;
}

table.xinxitable td {
	border-width: 1px;
	padding: 4px;
	border-style: solid;
	border-color: #666666;
	text-align: center;
}

table.xinxitable th {
	border-width: 1px;
	padding: 4px;
	background-color: #F3F7FB;
	border-style: solid;
	border-color: #666666;
	text-align: center;
}

#save {
	margin-top:348px;
	margin-left: 798px;
	
}
</style>

</head>

<body>
	<%
		//得到工号和教学班号
		String gonghao = request.getParameter("gonghao");
		String jxbbianhao = request.getParameter("jxbbianhao");
	%>
	<div id="title">筛选学生评教建议</div>
	<input type="hidden" value="<%=gonghao%>" id="gonghao">
	<input type="hidden" value="<%=jxbbianhao%>" id="jxbbianhao">
	<div>
		<table class="xinxitable">
			<tr>
				<th>序号</th>
				<th>评教编号</th>
				<th>学生评教建议</th>
				<th>删除</th>
			</tr>
			<tbody id="jybody">

			</tbody>
		</table>
	</div>
	<div id="save">
		<button id="save_btn">保存</button>
	</div>
</body>

<script type="text/javascript">
	$(function() {
		getJianyis();
	});

	function getJianyis() {
		var gonghao = $("#gonghao").val();
		var jxbbianhao = $("#jxbbianhao").val();
		$.ajax({
			url : "${pageContext.request.contextPath }/CheckJianyisServlet",
			type : "post",
			data : "gonghao=" + gonghao + "&jxbbianhao=" + jxbbianhao,
			dataType : "json",
			success : function(result) {
				showJianyis(result);
			}
		});

	}
	function showJianyis(result) {
		var i = 1;
		if(result==""||result==null){
			$("<tr></tr>").append("学生尚未进行评教!").appendTo("#jybody");
			$("#save_btn").attr("disabled",true);
		}
		$.each(result, function(index, item) {

			var xuhao = $("<td></td>").append(i);
			var jy = item.jianyi;
			var jianyi = $("<td></td>").append(jy);
			var pjbh = $("<td></td>").append(item.pjid);
			var isDelet = item.delet;
			if (jy != "empty") {
				//序号
				i++;
				//取消选择框
				//var quxiaodelet = $("<td><input type='checkbox'></td>");	
				//如果被删除过，选择框显示选择，否则显示空的选择框
				if (isDelet) {
					var delet = $("<td><input type='checkbox' class='de_check' checked></td>");
					$("<tr></tr>").append(xuhao).append(pjbh).append(jianyi)
							.append(delet)
							//.append(quxiaodelet)
							.appendTo("#jybody");
				} else {
					var delet = $("<td><input type='checkbox' class='de_check'></td>");
					$("<tr></tr>").append(xuhao).append(pjbh).append(jianyi)
							.append(delet).appendTo("#jybody");
				}
			}
		});
	}
	$("#save_btn").click(function(){
		var pjids="";
		$.each($(".de_check:checked"),function(){
			//将待删除的建议用“-”连接
			pjids += $(this).parents("tr").find("td:eq(1)").text()+"-";
			
		});
		//切掉末尾的“-”
		pjids = pjids.substring(0,pjids.length-1);
		//发送ajax请求批量更新评教建议
		if(confirm("确认删除这些建议吗？")){
			$.ajax({
				url : "${pageContext.request.contextPath }/SelectJianyiServlet",
				data : "pjids="+pjids,
				type : "post",
				success : function(result){
					  if(result == "y"){
						alert("操作成功!");
						location.href = "chakanjs.jsp";
					  }
				}					
			});
		}
	});
</script>
</html>