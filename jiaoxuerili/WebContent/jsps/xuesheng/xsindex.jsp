<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生系统主页</title>
<style type="text/css">
   #jiaoxuerili{
       text-align: center;  
   }
</style>
</head>
<body>   
     <div id="jiaoxuerili">
     <button id="btn" onclick="go()">教学日历</button>
     </div>
</body>
  <script type="text/javascript">
      function go(){
    	  location.href="${pageContext.request.contextPath}/XsIndexServlet";
      }
  </script>
</html>