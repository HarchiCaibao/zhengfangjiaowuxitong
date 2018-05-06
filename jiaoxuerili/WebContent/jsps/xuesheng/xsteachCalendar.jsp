<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生查看教学日历</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js">
	    </script>
<style type="text/css">
#title{
    font-family: verdana,arial,sans-serif;
    font-size:28px;
    color:#6B808F;
    width:1200px;
    background-color:#F3F7FB;
	text-align: center;
	margin-top: 15px;
	margin-left: 68px;
}

table.calendartext {
	font-family: verdana,arial,sans-serif;
	font-size:16px;
	color:#666666;
	background-color:#F3F7FB;
	width: 1200px;
	margin-left: 68px;
}
table.calendartext td {	
	padding: 8px;
}
.td_kecheng{
    width: 40px;
}

table.calendartable {
	font-family: verdana,arial,sans-serif;
	font-size:16px;
	color:#6B808F;
	border-width: 1px;
	border-color: #666666;
	background-color:#F3F7FB;
	border-collapse: collapse;
	width: 1200px;
	margin-left: 68px;
}
table.calendartable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-collapse: collapse;
	border-color: #666666;	
}
table.calendartable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-collapse: collapse;
	border-color: #666666;
}
.td_keci{
    width: 40px;
    padding: 8px;
    text-align: center;
    border-style: solid;
    border-collapse: collapse;
    border-width: 1px;
}
.td_zhouci{
	width: 180px;
	padding: 8px;
    text-align: center;
    border-collapse: collapse;
    border-color: #666666;
}

#kechengxinxi{
   background-color: #CEDEF3;
   margin-top: 120px;
   margin-left: 68px;
   width:1184px;
   padding: 0.3cm 0.2cm;
   line-height:250% ;
}
#kecheng{
   font-family: verdana,arial,sans-serif;
   font-size:18px;
   color:#6B808F;
   background-color: #F3F7FB;
   margin-left: 68px;
   padding: 0.3cm 0.2cm;
   line-height:80% ;
   margin-top: 5px;
}

#xueqi{
   top:220px;
   left:815px;
   font-family: verdana,arial,sans-serif;
   font-size:14px;
   color:#6B808F;
   background-color:#F3F7FB;
   width: 1200px;
    width: 284px;
   position: absolute;
}

</style>
</head>
<body>
	
    <div id="kechengxinxi">   		
              学年:<select onchange="fullkecheng()" id="xnselect">
               	<c:forEach var = "item" items = "${xuenian }">
               		<option>${item }</option>  
               	</c:forEach>
              </select>&nbsp;&nbsp;
        
              学期：<select onchange="fullkecheng()" id="xqselect">
                <option>1</option>
                <option>2</option>             
               </select>&nbsp;&nbsp;      
              课程：
              <select  onchange="fulltable()"  id="kcselect">
               	<!-- 这里显示课程 -->           
               </select>&nbsp;&nbsp;
    </div>

	<div id="title">
		<strong>华南农业大学教学日历</strong>
	</div>
	
	<div id="xueqi">
		<!-- 显示在表头下面的学期信息 -->
	</div>
	<div id="warning" style="text-align: center">
				<!-- 当某个学期没有课程时显示 -->
       </div>
	<table id="kcxinxi" class="calendartext" style="">
	   <!-- 该门课程的信息 -->
	</table> 
	 
	<table id="caltable" class="calendartable" style="">
	  <tr>
	     <th class="td_keci" rowspan="2">课<br>次</th>
	     <th rowspan="2" class="td_zhouci">周次</th>
	     <th colspan="3">授课形式</th>
	     <th rowspan="2">教学内容（限500字）</th>
	  </tr>
	  <tr>
	  	 <th rowspan="4" class="td_keci">理<br>论<br>课</th>
	  	 <th rowspan="4" class="td_keci">习题<br>课、<br>课堂<br>讨论</th>
	  	  <th rowspan="4" class="td_keci">实<br>践<br>教<br>学</th>
	  </tr>
	  
	  <tbody id="teachcalendar" class="calendartable" style="">
	    
	    <!-- 教学日历内容 -->
	  
	  </tbody> 
	</table>
	<div id="warning2" style="text-align: center">
				<!-- 当某个学期没有课程时显示 -->
       </div>
</body>
  <script type="text/javascript">
    
      //加载网页时执行，产生默认值的表格
      $(function(){  
    	  
    	  fullkecheng();
    	 
      });
      
      function fullkecheng(){ 
    	  $("#kcselect").empty();
    	  $("#warning2").empty();
    	  $("#warning").empty();
    	  var xuenian = $("#xnselect").val();
    	  var xueqi = $("#xqselect").val();
    	  $.ajax({
    		  url : "${pageContext.request.contextPath}/XsKechengServlet",
    		  data : "xuenian="+xuenian+"&&xueqi="+xueqi,
    		  type : "get",
    		  dataType : "json",
    		  success : function(result){
    			  var shangxueqi;
    			  var xiaxueqi;
    			  if(result.length!=0){
    				 $("#warning").empty();
    				 $("#kcxinxi").attr("style","");
     		    	 $("#caltable").attr("style","");
     		    	 $("#teachcalendar").attr("style","");
     		    	 $("#save").attr("style","");
    			      $.each(result,function(index,item){
    				    if(item.kaikexueqi==1){ 
    					    shangxeuqi= parseInt(item.kaikexuenian)-1;
        				    $("#kcselect").append("<option>"+item.mingcheng+"//"+item.jxbbianhao+
          						"//("+shangxeuqi+"-"+item.kaikexuenian+")-"+
          						item.kcbianhao+"-"+item.xuefen+"-"+item.zhouci+
          						"</option>");
    					  }
    				    if(item.kaikexueqi==2){ 
    					   xiaxueqi= parseInt(item.kaikexuenian)+1;
    					   $("#kcselect").append("<option>"+item.mingcheng+"//"+item.jxbbianhao+
            						"//("+item.kaikexuenian+"-"+xiaxueqi+")-"+
            						item.kcbianhao+"-"+item.xuefen+"-"+item.zhouci+
            						"</option>");
    					  }});	
    			         setTimeout("fulltable()",188);
    		    }else{
    		    	 $("#kcxinxi").attr("style","display : none");
    		    	 $("#caltable").attr("style","display : none");
    		    	 $("#teachcalendar").attr("style","display : none");
    		    	 $("#save").attr("style","display : none");
    		    	 $("#warning").append("<h1>该学期没有课程哦！</h1>");	 
    		    }
    		  }
    	  });
      }
      
      /*
      1、    页面加载完成发送ajax请求查询数据库是否曾保存了教学日历，若保存了，则取出来显示，
                   若未曾保存，显示空表格
      2、点击下拉菜单的课程信息，发送ajax请求查询数据库是否曾保存了教学日历，若保存了，则取出来显示，
                   若未曾保存，显示空表格
	 */ 
      function fulltable(){
    	  
      	//连接格式为：主讲教师//教学班组成!!课次%%周次**授课形式??授课内容
         //不同课次的教学日历采用“;;;”分隔
         
         //事先填充课程信息，方便之后ajax发请求带参数给servlet 
        getkecheng(); 
        $("#warning2").empty();
      	var jxbbianhao = $("#jxbbianhao").val();
      	$.ajax({
      		url:"${pageContext.request.contextPath}/JsJxriliShowServlet",
      		data:"jxbbianhao="+jxbbianhao,
      		dataType:"json",
      		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
      		type:"GET",
      		success:function(result){
      			if(result.status=="full"){
      				getkc(result);
          			addrow(result);
      			}else{      				
      				getkecheng();
      				$("#warning2").append("<h2>该老师尚未完成教学日历哦!</h2>");
      			}	
      		}
      		
      	});
      }
      //填充显示日历里的课程信息，此时日历未曾被保存
      function getkecheng(){
      	//清空表格
      	$("#kcxinxi").empty();
      	$("#xueqi").empty();
      	$("#teachcalendar").empty();
      	$("#warning2").empty();
      	//获得课程下拉列表的值
      	var kecheng = $("#kcselect").val();
      	//从值kecheng中取出名称
      	var mingcheng  = kecheng.substring(0,kecheng.indexOf("/"));
      	//从kecheng中取出开课学期
      	var xueqi = kecheng.substring(kecheng.indexOf("("),kecheng.indexOf(")")-1);
      	//从值kecheng中取出包含开课代码，学分，周次的字符串
      	var resultStr = kecheng.substring(kecheng.indexOf(")")+2);
      	//从值resultStr中取出开课代码
      	var kcdaima = resultStr.substring(0,resultStr.indexOf("-"));
      	//从值resultStr中取出学分
      	var xuefen = resultStr[resultStr.indexOf("-")+1];
      	//从值resultStr中取出周次
      	var zhouci = resultStr[resultStr.indexOf("-")+3];
      	//从kecheng中取出教学班组成
      	var jxb = kecheng.substring(kecheng.indexOf("/")+2,kecheng.indexOf(")"));
      	var jxbzucheng = jxb.substring(0,jxb.indexOf("/"));
      	
      	$("#kcxinxi").append(
      		"<tr> <td class='td_kecheng'>课程代码："+kcdaima+	"</td>"	+
      		 "<td class='td_kecheng'>专业："+mingcheng +"</td>"+
      		 "<td class='td_kecheng'>学分："+xuefen +"</td>"+
      		 "<td class='td_kecheng'>周学时："+zhouci +"</td>"+
      		 "</tr>"+
      		 "<tr> <td>教学班组成:<input type='text' value='" +jxbzucheng+ "'name='jiaoxueban' id='jxbbianhao' onkeydown='if(event.keyCode==13){return false;}' readonly></td>"+
      		 "<td>主讲教师:<input type='text' name='zjjiaoshi' id='jiaoshi' onkeydown='if(event.keyCode==13){return false;}' readonly></td>"+
      		 "</tr>"
      	
      	);
      	$("#xueqi").append(xueqi);
      }
       
    function addrow(result) {
    	//清空表格
    	$("#teachcalendar").empty();
    	$("#warning2").empty();
    	//填充课次最大值
    	$("#max").attr("value",result.jiaoxuerilis.length); 
    	
    	//填充表格
        $.each(result.jiaoxuerilis,function(index,item){
        	var keci = $("<td class='td_keci'></td>").append(index+1);
        	var zhouci = $("<td class='td_zhouci'><input type='text' name='zhouci"+index+"' value='"+item.zhouci+"' onkeydown='if(event.keyCode==13){return false;}'></td>");
        	var lilun = $("<td class='td_keci' ><input type='checkbox' value='理论课' name='xingshi"+ index +"'></td>");
        	var xiti = $("<td class='td_keci' ><input type='checkbox' value='习题课、课堂讨论 'name='xingshi"+ index +"'></td>");
        	var shijian = $("<td class='td_keci' ><input type='checkbox' value='实践教学' name='xingshi"+ index +"'></td>");
        	if(item.xingshi=="理论课"){lilun = $("<td class='td_keci' ><input type='checkbox' value='理论课' name='xingshi"+ index +"' checked></td>");}
        	if(item.xingshi=="习题课、课堂讨论 "){xiti = $("<td class='td_keci' ><input type='checkbox' value='习题课、课堂讨论 'name='xingshi"+ index +"' checked></td>");}
        	if(item.xingshi=="实践教学"){shijian = $("<td class='td_keci' ><input type='checkbox' value='实践教学' name='xingshi"+ index +"' checked></td>");}
        	var neirong = $("<td ><textarea cols='100' rows='4' name='neirong" +index + "'>"+item.neirong+"</textarea></td>");
        	$("<tr></tr>").append(keci)
        				  .append(zhouci)
        				  .append(lilun)
        				  .append(xiti)
        				  .append(shijian)
        				  .append(neirong)
        				  .appendTo("#teachcalendar")
        });
        
    }
    
    /*
		将数据库查询出来的信息显示。此时教学日历已经被保存一次
	*/
    function getkc(result){
    	//清空表格
    	$("#kcxinxi").empty();
    	$("#xueqi").empty();
    	$("#warning2").empty();
    	//获得课程下拉列表的值
    	var kecheng = $("#kcselect").val();
    	//从值kecheng中取出名称
    	var mingcheng  = kecheng.substring(0,kecheng.indexOf("/"));
    	//从kecheng中取出开课学期
    	var xueqi = kecheng.substring(kecheng.indexOf("("),kecheng.indexOf(")")+1);
    	//从值kecheng中取出包含开课代码，学分，周次的字符串
    	var resultStr = kecheng.substring(kecheng.indexOf(")")+2);
    	//从值resultStr中取出开课代码
    	var kcdaima = resultStr.substring(0,resultStr.indexOf("-"));
    	//从值resultStr中取出学分
    	var xuefen = resultStr[resultStr.indexOf("-")+1];
    	//从值resultStr中取出周次
    	var zhouci = resultStr[resultStr.indexOf("-")+3];
    	//从kecheng中取出教学班组成
    	var jxb = kecheng.substring(kecheng.indexOf("/")+2,kecheng.indexOf(")"));
    	var jxbzucheng = jxb.substring(0,jxb.indexOf("/"));
    	
    	$("#kcxinxi").append(
    		"<tr> <td class='td_kecheng'>课程代码："+kcdaima+	"</td>"	+
    		 "<td class='td_kecheng'>专业："+mingcheng +"</td>"+
    		 "<td class='td_kecheng'>学分："+xuefen +"</td>"+
    		 "<td class='td_kecheng'>周学时："+zhouci +"</td>"+
    		 "</tr>"+
    		 "<tr> <td>教学班组成:<input type='text' value='" +jxbzucheng+ "'name='jiaoxueban' id='jxbbianhao' onkeydown='if(event.keyCode==13){return false;}' readonly></td>"+
    		 "<td>主讲教师:<input type='text' name='zjjiaoshi' id='jiaoshi' value='"+result.zjjiaoshi+"' onkeydown='if(event.keyCode==13){return false;}' readonly></td>"+
    		 "</tr>"
    	
    	);
    	
    	$("#xueqi").append(xueqi);
    }
  
</script>
</html>