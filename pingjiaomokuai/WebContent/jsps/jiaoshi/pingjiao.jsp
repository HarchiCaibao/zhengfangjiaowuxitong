<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生教学质量评估查阅</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<style type="text/css">
#welcome {
	margin-left: 1110px;
}

#pinggu {
	margin-top: 18px;
	margin-left: 680px;
}
#jiaoxueban {
	margin-top: 200px;
	margin-left: 300px;
}
.page{
	top: 718px;
	left: 435px;
	position: absolute;
}
</style>
</head>
<!-- 2018.3.29修改 -->

<body>
	<div id="welcome">工号：${sessionScope.gonghao }老师，欢迎你</div>
	<div id="pinggu">
		<button id="pjbtn" onclick="go()">教学质量评估</button>
	</div>
	
	<div id="jiaoxueban">
		<table width="380" cellspacing="0">
			<tr>
				<td>课程名称</td>
				<td>教学班号</td>
				<td>开课学年</td>
				<td>开课学期</td>
				<td>操作</td>
			</tr>
			<tbody id="kctbody">
			
			</tbody>
		</table>
	</div>
	
	<div class="page" id="pageList" style="display:inline">
		<!-- 这里显示分页条 -->
	</div>

</body>
<script type="text/javascript">
    $(function(){
    	getKc(1);
    });
	
	function go() {
		location.href = "${pageContext.request.contextPath }/JsJiaoxuebanServlet";
	}
	
	function getKc(page){
		var kaikexuenian = $("#kaikexuenian").val();
		var kaikexueqi = $("#kaikexueqi").val();
		
		$.ajax({
			     url : "${pageContext.request.contextPath}/JsJiaoxuebanXinxiServlet",
		         data : "cp="+page,
		         type : "get",
		         dataType : "json",
		         success: function(result){
		        	 showTbody(result.beanList);
		        	 pageList(result);
		        	 fillPageNum(result);
		         }
			
		});
		
	}
	
	function showTbody(result){
		$("#kctbody").empty();
		$.each(result,function(index,item){
			var kcname = $("<td>"+item.mingcheng+"</td>");
			var jxbbianhao = $("<td>"+item.jxbbianhao+"</td>");
			var kaikexuenian = $("<td>"+item.kaikexuenian+"</td>");
			var kaikexueqi = $("<td>"+item.kaikexueqi+"</td>");
			var caozuo = $("<td><a href='${pageContext.request.contextPath }/JsPingjiaoshujuServlet?jxbbianhao="+
					item.jxbbianhao+"'>查看评教</td>");
			$("<tr></tr>").append(kcname)
							.append(jxbbianhao)
							.append(kaikexuenian)
							.append(kaikexueqi)
							.append(caozuo)
							.appendTo("#kctbody")
		});
	}
	
	
	
	//解析分页条信息
	function pageList(result){
		
		$("#pageList").empty();
		//如果没有下一页了
		if(result.currentPage==result.totalPage){
			var nextPage = $("<button style='background: none; border:none'></button>").append("下一页").attr("disabled", "true");
		}else{
			var nextPage = $("<a></a>").append("&nbsp;&nbsp;下一页&nbsp;&nbsp;").attr("href", "#");
		}
		
		//如果没有上一页了
		if(result.currentPage!=1){
			var prePage = $("<a></a>").append("上一页&nbsp;&nbsp;").attr("href", "#");
		}else{
			var prePage = $("<button style='background: none; border:none'></button>").append("上一页").attr("disabled", "true");
		}
		
		var firstPage = $("<a></a>").append("首页&nbsp;&nbsp;").attr("href", "#");
		var lastPage = $("<a></a>").append("尾页").attr("href", "#");
		
		//用来显示一行页码用的
		var pageNums = $("<div style='display:inline' id='pageNum'></div>");
		
		
		$("#pageList").append("第"+result.currentPage+"页/共"+result.totalPage+"页&nbsp;&nbsp;&nbsp;&nbsp;")
					  .append(firstPage)
					  .append(prePage)
					  .append(pageNums)
					  .append(nextPage)
					  .append(lastPage);
		
		//为各个元素添加点击事件，实现对应页面跳转
		firstPage.click(function(){
			//首页
			getKc(1);
		});
		lastPage.click(function(){
			//尾页
			getKc(result.totalPage);
		});
		prePage.click(function(){
			//上一页
			getKc(result.currentPage-1);
		});
		nextPage.click(function(){
			//下一页
			getKc(result.currentPage+1);
		});	
	}
	
	function fillPageNum(result){
		
		var cp = result.currentPage;//当前页码
		var tp = result.totalPage;//总页数
		var nz = 6;//指定分页条一次性显示多少页的页码，这里显示6页的页码
		
		var begin = cp - 3;//页码起始数字
		var end = cp+2;//页码结束数字

		//总页数小于6页,将显示所有页码
		if(tp<=nz){
			begin = 1;
			end = tp;
			showNum(begin,end);
			return;
		}
		//页头溢出
		if(begin<1){
			begin = 1;
			end = nz;
			showNum(begin,end);
			return;
		}
		//页尾溢出
		if(end>tp){
			begin = tp - 5;
			end = tp;
			showNum(begin,end);
			return;
		}
		//正常显示页码
		showNum(begin,end);
	}
	
	//显示一行页码
	function showNum(begin,end){
		for(var i = begin;i<=end;i++){	
			$("#pageNum").append("[<a href='#' onclick='getKc("+i+")'>"+i+"</a>]");
		}	
	}
	
	
</script>

</html>