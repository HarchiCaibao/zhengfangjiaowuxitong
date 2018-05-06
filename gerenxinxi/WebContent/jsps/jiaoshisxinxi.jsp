<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看教师简介</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<style type="text/css">
#title{
		font-family: verdana,arial,sans-serif;
    	font-size:28px;
   		color:#6B808F;
   		left:550px;
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
	top:138px;
	width:498px;
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
	background-color:#F3F7FB;
	border-style: solid;
	border-color: #666666;
	text-align: center;
}
.page{
	top: 488px;
	left: 428px;
	position: absolute;
}

</style>

</head>
<body>
	<div id="title">教师简介表</div>
	<table class="xinxitable">	
		<tr>
		   <th>姓名</th>
		   <th>性别</th> 
		   <th>学历</th>
		   <th>职称</th>
		   <th>研究生导师</th>
		   <th>详细信息</th>
		 </tr>
		<tbody id="body">
			<!-- 显示所有教师的基本信息表格 -->
		</tbody>	
	</table>

	<div class="page" id="pageList" style="display:inline">
		<!-- 这里显示分页条 -->
	</div>
</body>

	<script type="text/javascript">
		$(function(){			
			showJsXinxi(1);
		});
		
		function showJsXinxi(page){
			$.ajax({
				url : "${pageContext.request.contextPath }/JssjianjieShowServlet",
				type : "post",
				data : "cp="+page,
				dataType : "json",
				success : function(result){
					fullTable(result.beanList);
					pageList(result);
					fillPageNum(result);
					
				}
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
				showJsXinxi(1);
			});
			lastPage.click(function(){
				//尾页
				showJsXinxi(result.totalPage);
			});
			prePage.click(function(){
				//上一页
				showJsXinxi(result.currentPage-1);
			});
			nextPage.click(function(){
				//下一页
				showJsXinxi(result.currentPage+1);
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
				$("#pageNum").append("[<a href='#' onclick='showJsXinxi("+i+")'>"+i+"</a>]");
			}	
		}
	
		function fullTable(result){
			$("#body").empty();
			$.each(result,function(index,item){
				var xingming = $("<td>"+item.xingming+"</td>");
				var xingbie = $("<td>"+item.xingbie+"</td>");
				var yjsdaoshi = $("<td>"+item.yjsdaoshi+"</td>");
				var xueli = $("<td>"+item.xueli+"</td>");
				var zhicheng = $("<td>"+item.zhicheng+"</td>");
				var xiangxixinxi = $("<td><a href='${pageContext.request.contextPath }/JsXinxiShowServlet?xingming="+item.xingming+"&&flag=admin'>点击查看</a></td>");
				$("<tr></tr>").append(xingming)
							  .append(xingbie)
				              .append(xueli)
				              .append(zhicheng)
				              .append(yjsdaoshi)
				              .append(xiangxixinxi)
				              .appendTo("#body")
			});
			
		}
	
	</script>


</html>