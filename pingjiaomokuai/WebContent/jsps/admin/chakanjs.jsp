<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看所有教师教学信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<style type="text/css">
#title{
		font-family: verdana,arial,sans-serif;
    	font-size:32px;
   		color:#6B808F;
   		left:545px;
		top: 88px;
		position: absolute;
	}
#choose{
   		left:550px;
		top: 148px;
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
	top:198px;
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
	top: 548px;
	left: 435px;
	position: absolute;
}

</style>

</head>
<body>
	<div id="title">教师教学班信息表</div>
	<div id="choose">
		开课学年：<select id="kaikexuenian">
		
		   		<!-- 这里倒序显示学年信息 -->
		   		
		</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		开课学期：<select id="kaikexueqi">
			<option>1</option>
			<option>2</option>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button id="select-btn">确定</button>
	</div>
	<table class="xinxitable">	
		<tr>
		   <th>姓名</th>
		   <th>工号</th> 
		   <th>教学班编号</th>
		   <th>课程名称</th>
		   <th>查看学生建议</th>   
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
			showSelect();
            setTimeout("showJsXinxi(1)",500);
		});
		
		function showSelect(){
			$.ajax({
				url : "${pageContext.request.contextPath }/AdShowxqxnServlet",
				type : "post",
				dataType : "json",
				success : function(result){
					$.each(result,function(index,item){
						$("<option></option>").append(item).appendTo("#kaikexuenian");
					});
				}
			});
		}
		$("#select-btn").click(function(){
			showJsXinxi(1);
		});
		
		function showJsXinxi(page){
			var kaikexuenian = $("#kaikexuenian").val();
			var kaikexueqi = $("#kaikexueqi").val();
			$.ajax({
				url : "${pageContext.request.contextPath }/AdJiaoshiServlet",
				type : "post",
				data : "cp="+page+"&&kaikexuenian="+kaikexuenian+"&&kaikexueqi="+kaikexueqi,
				dataType : "json",
				success : function(result){
					if(result.total != 0){
						fullTable(result.beanList);
						pageList(result);
						fillPageNum(result);
					}else{
						$("#body").empty();
						$("<tr></tr>").append("该学期尚未开设课程!").appendTo("#body");
					}
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
	    //填充表格
		function fullTable(result){
			$("#body").empty();
			$.each(result,function(index,item){
				var xingming = $("<td>"+item.xingming+"</td>");
				var gonghao = $("<td>"+item.gonghao+"</td>");
				var jxbbianhao = $("<td>"+item.jxbbianhao+"</td>");
				var mingcheng = $("<td>"+item.mingcheng+"</td>");
				var checkJianyi = $("<td><a href='${pageContext.request.contextPath }/jsps/admin/selectjianyi.jsp?gonghao="+item.gonghao+
						"&&jxbbianhao="+item.jxbbianhao+"'>点击查看</a></td>");
				$("<tr></tr>").append(xingming)
							  .append(gonghao)
				              .append(jxbbianhao)
				              .append(mingcheng)
				              .append(checkJianyi)
				              .appendTo("#body")
			});
			
		}
	
	</script>


</html>