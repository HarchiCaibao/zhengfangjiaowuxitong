<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师教学日历</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>

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
	border-color: #666666;	
}
table.calendartable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
}
.td_keci{
    width: 40px;
    padding: 8px;
    text-align: center;
    border-style: solid;
    border-width: 1px;
}
.td_zhouci{
	width: 180px;
	padding: 8px;
    text-align: center;
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
#save{
    margin-top:10px;
    margin-left: 1080px;
}
#sumbtn{
   top:140px;
   left:338px;
   font-family: verdana,arial,sans-serif;
   font-size:12px;
   color:#6B808F;
   position: absolute;

}
#xueqi{
   top:255px;
   left:820px;
   font-family: verdana,arial,sans-serif;
   font-size:14px;
   color:#6B808F;
   background-color:#F3F7FB;
   width: 284px;
   position: absolute;
}

</style>
</head>
<body>
	<div id="sumbtn">
	
	<button id="maxbtn" onclick="add()">确定</button><br>
	
	</div>
	
	<form action=" "  name="riliform" method="post" >
    <div id="kechengxinxi">
    
        <!-- 把session域的工号取出，方便在页面使用 -->
   		<input type="hidden" value="${sessionScope.gonghao }" id="gonghao">
   		
   		课次最大值:<input type="text" id="max" value="35" name="kecimax" onkeydown="if(event.keyCode==13){return false;}"><br>  		
               学年:<select onchange="fullkecheng()" id="xnselect">
               	<c:forEach var = "item" items = "${sessionScope.xuenian }">
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
              选择复制：
              <select id="cpselect">
              	<!-- 这里显示选择复制功能的教学班 -->
              </select>&nbsp;&nbsp;&nbsp;&nbsp;
              
              <input type="button" id="cpbtn" onclick="copy()" value="确定">
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
	
	<div id="save"> <input type="button" value="保存" id="subbtn" onclick="subform()">
		</div>
	</form>
	
</body>
    <script type="text/javascript">
		
       //全局变量主讲教师
       var zhujiangjiaoshi; 
    
      //加载网页时执行，产生默认值的表格
      $(function(){  
    	  
    	  fullkecheng();
    	 
      });
      
      function fullkecheng(){ 
    	  $("#kcselect").empty();
    	  var xuenian = $("#xnselect").val();
    	  var xueqi = $("#xqselect").val();
    	  $.ajax({
    		  url : "${pageContext.request.contextPath}/JsKechengServlet",
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
    			    	  
    			    	zhujiangjiaoshi = item.xingming;
    				    if(item.kaikexueqi==1){ 
    					    shangxeuqi= parseInt(item.kaikexuenian)-1;
        				    $("#kcselect").append("<option>"+item.mingcheng+"//"+item.jxbbianhao+
          						"//("+shangxeuqi+"-"+item.kaikexuenian+")-"+
          						item.kcbianhao+"-"+item.xuefen+
          						"</option>");
    					  }
    				    if(item.kaikexueqi==2){ 
    					   xiaxueqi= parseInt(item.kaikexuenian)+1;
    					   $("#kcselect").append("<option>"+item.mingcheng+"//"+item.jxbbianhao+
            						"//("+item.kaikexuenian+"-"+xiaxueqi+")-"+
            						item.kcbianhao+"-"+item.xuefen+"-"+item.zhouci+
            						"</option>");
    					  }});
    			     	 //填充教学日历表格
    			         setTimeout("fulltable()",188);
    			         var xueqi = $("#xqselect").val();
    			         var xuenian = $("#xnselect").val();
    			         var kc = $("#kcselect").val();
    			         $("#cpselect").empty();
    			         if(xueqi!="" && xuenian!="" && kc!=""){
    			        	 showSameKc(xueqi,xuenian,kc);
    			         }
     	 
    		    }else{
    		    	 $("#kcxinxi").attr("style","display : none");
    		    	 $("#caltable").attr("style","display : none");
    		    	 $("#teachcalendar").attr("style","display : none");
    		    	 $("#save").attr("style","display : none");
    		    	 $("#warning").empty();
    		    	 $("#warning").append("<h1>该学期没有课程哦！</h1>");	 
    		    }
    		  }
    	  });
      }
      
      //根据某课程的学期学年，选择显示该课程曾经开设的教学班
      function showSameKc(xueqi,xuenian,kc){
    	  //得到与数据库表匹配的学期形式
    	  var xn,kcName;
    	  var index = xuenian.indexOf("-")+1;
    	  var kcName = kc.substring(0,kc.indexOf("/"));
    	  if(xueqi==1){
    		 xn = xuenian.substring(index);
    	  }else{
    		 xn = xuenian.substring(0,index);
    	  }
    	  $.ajax({
    		  url: "${pageContext.request.contextPath}/JsShowSameJxbServlet",
    		  data: "xueqi="+xueqi+"&&xuenian="+xn+"&&kcName="+kcName,
    		  type: "get",
    		  dataType: "json",
    		  success: function(result){
			     	
    			  if(result==""){
  			     		 $("#cpselect").append("<option>无</option>");	
  			     		 return;
    			  }
			       //直接取教学班号，可能产生bug：教学班号一致，如果教学班号规定不一致就没事
			     	$.each(result,function(index,item){
			     		 $("#cpselect").append("<option>"+item.jxbbianhao+"</option>");	     		 
			     	});
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
      				add();
      			}	
      		}
      		
      	});
      }
       
    function addrow(result) {
    	//清空表格
    	$("#teachcalendar").empty();
    	
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
    
    //根据课次最大值添加行数
    function add() {
    	
    	$("#teachcalendar").empty();
    	$("#max").empty();
    	var num = 1;
    	var keci = 0;
        var sum = 0;
        sum=$("#max").val();
    	
        for(var i = 0;i<sum;i++){
		   keci = parseInt(num)+parseInt(i);
           $("#teachcalendar").append(
            "<tr id='line"+ i + "'>" + 
            "<td class='td_keci'>" + keci + "</td>" +
            "<td class='td_zhouci'><input type='text' name='zhouci"+i+     
            "' onkeydown='if(event.keyCode==13){return false;}'></td>" +
            "<td class='td_keci' ><input type='checkbox' value='理论课' name='xingshi"+ i +"'></td>" +
            "<td class='td_keci' ><input type='checkbox' value='习题课、课堂讨论 'name='xingshi"+ i +"'></td>" +
            "<td class='td_keci' ><input type='checkbox' value='实践教学' name='xingshi"+ i +"'></td>" +
            "<td ><textarea cols='100' rows='4' name='neirong" + i +  "'></textarea></td>" +
        "</tr>");
		}
    }
    
    /*
		将数据库查询出来的信息显示。此时教学日历已经被保存一次
	*/
    function getkc(result){
    	//清空表格
    	$("#kcxinxi").empty();
    	$("#xueqi").empty();
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
    	//var zhouci = resultStr[resultStr.indexOf("-")+3];
    	//从kecheng中取出教学班组成
    	var jxb = kecheng.substring(kecheng.indexOf("/")+2,kecheng.indexOf(")"));
    	var jxbzucheng = jxb.substring(0,jxb.indexOf("/"));
    	
    	$("#kcxinxi").append(
    		"<tr> <td class='td_kecheng'>课程代码："+kcdaima+	"</td>"	+
    		 "<td class='td_kecheng'>专业："+mingcheng +"</td>"+
    		 "<td class='td_kecheng'>学分："+xuefen +"</td>"+
    		 "<td class='td_kecheng'>周学时：<input type='text' name='zhouxueshi' id='zhouci' value='"+result.zhouxueshi +"' onkeydown='if(event.keyCode==13){return false;}'></td>"+
    		 "</tr>"+
    		 "<tr> <td>教学班组成:<input type='text' value='" +jxbzucheng+ "'name='jiaoxueban' id='jxbbianhao' onkeydown='if(event.keyCode==13){return false;}'></td>"+
    		 "<td>主讲教师:<input type='text' name='zjjiaoshi' id='jiaoshi' value='"+result.zjjiaoshi+"' style='display:none'>"+zhujiangjiaoshi+"</td>"+
    		 "</tr>"
    	
    	);
    	
    	$("#xueqi").append(xueqi);
    }
    
    
    
    //填充显示日历里的课程信息，此时日历未曾被保存
    function getkecheng(){
    	//清空表格
    	$("#kcxinxi").empty();
    	$("#xueqi").empty();
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
    	//var zhouci = resultStr[resultStr.indexOf("-")+3];
    	//从kecheng中取出教学班组成
    	var jxb = kecheng.substring(kecheng.indexOf("/")+2,kecheng.indexOf(")"));
    	var jxbzucheng = jxb.substring(0,jxb.indexOf("/"));
    	
    	$("#kcxinxi").append(
    		"<tr> <td class='td_kecheng'>课程代码："+kcdaima+	"</td>"	+
    		 "<td class='td_kecheng'>专业："+mingcheng +"</td>"+
    		 "<td class='td_kecheng'>学分："+xuefen +"</td>"+
    		 "<td class='td_kecheng'>周学时:<input type='text' name='zhouxueshi' id='zhouci' onkeydown='if(event.keyCode==13){return false;}'></td>"+
    		 "</tr>"+
    		 "<tr> <td>教学班组成:<input type='text' value='" +jxbzucheng+ "'name='jiaoxueban' id='jxbbianhao' onkeydown='if(event.keyCode==13){return false;}'></td>"+
    		 "<td>主讲教师:<input type='text' name='zjjiaoshi' id='jiaoshi' style='display:none' value='"+zhujiangjiaoshi+"'>"+zhujiangjiaoshi+"</td>"+
    		 "</tr>"  	
    	);
    	$("#xueqi").append(xueqi);
    }
    
   function sub(){
    	var zhouci = $("#zhouci").val();
    	if(zhouci == ""){
    		return false;
    	}else{
    		return true;
    	}
    }
   //复制教学日历到当前课程的教学日历
   function copy(){
	   var jxbbianhao = $("#cpselect").val();
	   $.ajax({
     		url:"${pageContext.request.contextPath}/JsJxriliShowServlet",
     		data:"jxbbianhao="+jxbbianhao,
     		dataType:"json",
     		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
     		type:"GET",
     		success:function(result){
     			if(result.status=="full"){
     				//如果数据库保存有教学日历，则复制过来
         			addrow(result);
     				alert("拷贝成功!");
     			}else{      				
     				//否则弹出错误提示
     				alert("你尚未给该教学班填写教学日历!");
     				return;
     			}	
     		}
     		
     	});   
   }
   
   //由于form表单里存在多个按钮，使用js控制各个按钮的功能
   function subform(){
		var zhouci = $("#zhouci").val();
   		if(zhouci == ""){
   			alert("周学时不能为空！");
   			return;
   		}else{
   	   		document.riliform.action = "${pageContext.request.contextPath}/JsJxriliSaveServlet";
   	  		document.riliform.submit();
   	}
   
}
     
</script>
</html>