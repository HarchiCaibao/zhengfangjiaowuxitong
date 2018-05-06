<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息填写</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<style type="text/css">
table.xinxitable {
	font-family: verdana, arial, sans-serif;
	font-size: 16px;
	color: #666666;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	margin-left: 208px;
	margin-top:78px;
	width:738px;
	
	
}
table.xinxitable td {
	border-width: 1px;
	padding: 3px;
	border-style: solid;
	border-color: #666666;
	text-align: center;
}
#welcom{
   margin-top: 12px;
   margin-left: 1080px;
}
#title{
		font-family: verdana,arial,sans-serif;
    	font-size:28px;
   		color:#6B808F;
   		left:490px;
		top: 68px;
		position: absolute;
	}

.ui-dialog{ 
    width: 380px;
	height: 380px;
	display: none;
    position: absolute;
	z-index: 9000;
    top: 0px;
	left: 0px;
    border: 1px solid #D5D5D5;
	background: #fff;
}
 

.ui-dialog-title{
    height: 48px;
	line-height: 48px; 
	padding:0px 20px;
	color: #535353;
	font-size: 20px;
	font-weight:bold;
	text-align: center;
    border-bottom: 1px solid #efefef;
	background: #f5f5f5;
    cursor: move;
    user-select:none;
}

 
.ui-dialog-content{
    text-align: center;
	padding:18px 20px;
	
}
.ui-dialog-select{
	width: 88px;
	height: 28px;
	background: #3b7ae3;
	border:none;
	font-size: 12px;
	color: #fff;
	text-decoration: none;
    display: block;
	text-align: center;
	top:288px;
	left:148px;
	position: absolute;
}


.ui-dialog-submit{
    width: 48px;
	height: 28px;
	background: #3b7ae3;
	border:none;
	font-size: 12px;
	color: #fff;
	text-decoration: none;
    display: block;
	text-align: center;
	top:338px;
	left:108px;
	position: absolute;
	
}
.ui-dialog-cancel{
	width: 48px;
	height: 28px;
	background: #3b7ae3;
	border:none;
	font-size: 12px;
	color: #fff;
	text-decoration: none;
    display: block;
	text-align: center;
	top:338px;
	left:228px;
	position: absolute;
}

.ui-mask{ 
    width: 100%;
	height:100%;
	background: #000;
    position: absolute;
	top: 0px;
	height: 0px;
	z-index: 8000;
    opacity:0.4; 
	filter: Alpha(opacity=40);
}           	
</style>

</head>
<body>
	<!-- 这里只将教师工号显示出来 -->
   <div id="welcom">欢迎你：（工号）${sessionScope.gonghao }</div>
   <div id="title">教师个人简介表</div>
   <!-- 禁止按回车键提交表单 -->
	<form action="${pageContext.request.contextPath }/JsjianjieSaveServlet"
		method="post"  onsubmit="return saveCheck()" id="form">
		<table class="xinxitable">
			<tr>
				<td width="68px">姓名</td>
				<td><input type="text" name="xingming" id="xingming" value="" onkeydown="if(event.keyCode==13){return false;}"></td>
				<td width="68px">性别</td>
				<td><input type="text" name="xingbie" id="xingbie" value="" onkeydown="if(event.keyCode==13){return false;}"></td>
				<td style="font-size: 14px">个人头像<div style="font-size: 9px">(点击头像可更换)</div></td>
			</tr>
			<tr>
				<td width="68px">民族</td>
				<td><input type="text" name="minzu" id="minzu" value="" onkeydown="if(event.keyCode==13){return false;}"></td>
				<td width="68px">籍贯</td>
				<td><input type="text" name="jiguan" id="jiguan" value="" onkeydown="if(event.keyCode==13){return false;}"></td>
				<td rowspan="4"  width="138px" id="img">
					<!-- 这里显示头像 -->
				</td>
			</tr>
			<tr>
				<td width="68px">出生日期</td>
				<td><input type="text" name="chushengriqi" id="chushengriqi" value="" onkeydown="if(event.keyCode==13){return false;}"></td>
				<td width="68px">健康状况</td>
				<td><input type="text" name="jkzhuangkuang" id="jkzhuangkuang" value=""  onkeydown="if(event.keyCode==13){return false;}"></td>
			</tr>
			<tr>
				<td width="68px">政治面貌</td>
				<td><input type="text" name="zzmianmao" id="zzmianmao" value="" onkeydown="if(event.keyCode==13){return false;}"></td>
				<td width="68px">学历</td>
				<td><input type="text" name="xueli" id="xueli" value="" onkeydown="if(event.keyCode==13){return false;}"></td>
			</tr>
			
			<tr>
				<td width="68px">学位</td>
				<td><input type="text" name="xuewei" id="xuewei" value="" onkeydown="if(event.keyCode==13){return false;}"></td>
				<td width="68px">职称</td>
				<td><input type="text" name="zhicheng" id="zhicheng" value="" onkeydown="if(event.keyCode==13){return false;}"></td>				
			</tr>
			<tr>
				<td width="68px">邮箱</td>
				<td><input type="text" name="youxiang" id="youxiang" value=""  onkeydown="if(event.keyCode==13){return false;}"></td>
				<td width="68px">电话</td>
				<td colspan="2"><input type="text" name="dianhua" value="" size="46" id="dianhua" onkeydown="if(event.keyCode==13){return false;}"></td>				
			</tr>
			<tr>
			   <td width="68px">研究生导师</td>
			   <td colspan="4">
			   		<input type="radio" name="yjsdaoshi" id="bsdaoshi" value="博士生导师" checked="checked">博士生导师&nbsp;&nbsp;&nbsp;
			   		<input type="radio" name="yjsdaoshi" id="ssdaoshi" value="硕士生导师">硕士生导师&nbsp;&nbsp;&nbsp;
			   		<input type="radio" name="yjsdaoshi" id="wu" value="无">无
			   </td>
			</tr>
			<tr>
				<td width="68px">教学经历</td>
				<td colspan="4"><textarea rows="12" cols="78" name="jxjingli" id="jxjingli"></textarea></td>		
			</tr>
			<tr>
				<td width="68px">研究经历</td>
				<td colspan="4"><textarea rows="12" cols="78" name="yjjingli" id="yjjingli"></textarea></td>		
			</tr>
			<tr>
				<td width="68px">研究兴趣</td>
				<td colspan="4"><textarea rows="12" cols="78" name="yjxingqu" id="yjxingqu"></textarea></td>		
			</tr>
			<tr>
				<td width="68px">获得奖励</td>
				<td colspan="4"><textarea rows="12" cols="78" name="hdjiangli" id="hdjiangli"></textarea></td>		
			</tr>
			<tr>  
			   <td colspan="5">
			   	   <input type="hidden" value="${sessionScope.gonghao }" name="gonghao" id="gonghao">   
			       <input type="submit" id="save" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			       <input type="reset"  id="reset" value="重置">
			     </td> 
			  </tr>
		</table>
	</form>
	
	<div class="ui-mask" id="mask">
		<!-- 弹出遮罩-->
	</div>
	
	<!-- 头像上传模态框 -->
	 <div class="ui-dialog" id="dialogMove" >
		<div class="ui-dialog-title" id="dialogDrag"  >上传头像</div>
		<div class="ui-dialog-content" id="photo">
			<!-- 这里显示头像 -->
			</div>
		<form id="txform">
			<input type="file" id="touxiang" name="touxiang" style="display: none" onchange="imgchange(event)">
		  </form>
		<button class="ui-dialog-select" onclick="openImg()">选择图片</button>
		<button class="ui-dialog-submit" onclick="saveTouxiang()">确定</button>
		<button class="ui-dialog-cancel" onclick="hideDialog()">取消</button>
     </div> 
     
</body>
	<script type="text/javascript">
		$(function(){
			touxiangCheck();
			showXinxi();
		});
		
		//判断用户是否第一次完善个人信息，如果是，系统随机分配头像并显示且保存到数据库
		//如果不是，查询已保存的头像并显示
		function touxiangCheck(){
			var gonghao = $("#gonghao").val();
			$.ajax({
				url : "${pageContext.request.contextPath }/JsTxShowServlet",
				type : "get",
				data : "gonghao="+gonghao,
				success : function(data){
					if(data=="no") {  
							showTouxiang_2();
						}else{
							showTouxiang_1();
						}
				}
				
			});
		}
		
		//  获取元素DOM对象  
		function getDom(id){return document.getElementById(id);}
		
		//  自动居中元素（el = Element）
		function autoCenter( el ){
			var bodyW = document.documentElement.clientWidth;
			var bodyH = document.documentElement.clientHeight;
			var elW = el.offsetWidth;
			var elH = el.offsetHeight;
			el.style.left = (bodyW-elW)/2 + 'px';
			el.style.top = (bodyH-elH)/2 + 'px';
		}
		
		//  自动扩展元素到全部显示区域
		function fillToBody( el ){
			el.style.width  = document.documentElement.clientWidth  +'px';
			el.style.height = document.documentElement.clientHeight + 'px';
		}
		//  关闭模态框
		function hideDialog(){
			getDom('dialogMove').style.display = 'none';
			getDom('mask').style.display = 'none';
		}
		
		//点击弹出本地磁盘，选择图片
		function openImg() {
			$("#touxiang").click();
		}
		//实现上传图片的预览
		function imgchange(event) {
			
			$("#photo").empty();
			var files = event.target.files, file;
			var path = event.target.value;
			var type = path.substring(path.lastIndexOf(".")+1);
			//判断文件类型
			if(type!="jpg" && type!="png" && type!="gip" && type!="bmp" && type!="gif" ){
				alert("请上传正确格式的图片!");
				showTouxiang();
				return false;
			}
			if (files && files.length > 0) {
				// 获取目前上传的文件
				file = files[0];
				// 文件大小校验的动作		
				if (file.size > 1024 * 60) {
					alert('图片大小不能超过 60kB!');
					return false;
				}
			}
			// 获取 window 的 URL 工具
			var URL = window.URL || window.webkitURL;
			// 通过 file 生成目标 url
			var imgURL = URL.createObjectURL(file);
			$("#photo")
					.append("<img alt='头像' width='168px' height='198px' src='"+imgURL+"'>");

		   }
		
		//将当前头像显示在模态框里
		//flag是为了让浏览器显示的不是当前缓存的头像
		function showTouxiang_3() {
			var gonghao = $("#gonghao").val();
			$("#photo").empty();
			$("#photo")
					 .append("<img alt='头像' width='168px' height='198px' src='${pageContext.request.contextPath }/JsTxShowServlet?gonghao="+gonghao+"&&fresh="+Math.random()+"'>");
		}
		
		//模态框确定上传的头像后，回到页面显示头像
		//必须在请求头后加入一个随机参数，如flag，flag没有任何实际作用，是为了避免浏览器显示的缓存的头像
		function showTouxiang_4(){
			var gonghao = $("#gonghao").val();
			$("#img").empty();
			$("#img")
				    .append("<img alt='头像' width='138px' height='158px' src='${pageContext.request.contextPath }/JsTxShowServlet?gonghao="+gonghao+"&&fresh="+Math.random()+"' onclick='upload()'>");
			
		}
		
		//点击头像打开头像上传模态框
		function upload(){
			getDom('dialogMove').style.display = 'block';
			getDom('mask').style.display = 'block';
			autoCenter( getDom('dialogMove') );
			fillToBody( getDom('mask') );
			showTouxiang_3();
		}
		
		function saveTouxiang(){
			//获得表单里的头像数据
			var touxiang = new FormData(document.getElementById("txform"));
			//保存头像
			$.ajax({
				url : "${pageContext.request.contextPath }/JsTxUpdateServlet",
				type : "post",
				data : touxiang,
				contentType : false, 
			    processData : false, 
				success : function(){
					//关闭模态框
					hideDialog();
					//将用户上传的头像显示在页面的指定位置中
					showTouxiang_4();
						
				}
			});
			
		}
			
		//查询数据库显示已保存的头像
		function showTouxiang_1(){
			var gonghao = $("#gonghao").val();
			$("#img").append("<img alt='头像' width='138px' height='158px' src='${pageContext.request.contextPath }/JsTxShowServlet?gonghao="+gonghao+"' onclick='upload()'>");
			
		}
		
		//显示系统默认头像并保存到数据库中
		function showTouxiang_2(){	
			
			//系统保存了五张默认头像在imgs文件夹下
			//随机生成一个1-5之间的书来给用户分配随机的默认头像
			var j = Math.random()*4+1; 
			var i = parseInt(j);
			$("#img").append("<img alt='头像'  width='138px' height='158px' src='${pageContext.request.contextPath }/imgs/"+i+".jpg' onclick='upload()'>");
			
			//保存系统提供的头像，方便在管理头像的页面查询显示
			$.ajax({
				url : "${pageContext.request.contextPath }/JsXitongTxSaveServlet",
				data : "path="+"${pageContext.request.contextPath }/imgs/"+i+".jpg",
				type : "post"
			});
		}
		
		//显示教师保存过的信息信息
		function showXinxi(){
			var gonghao = $("#gonghao").val();
			$.ajax({
				url : "${pageContext.request.contextPath }/JsXinxiShowServlet",
				data : "gonghao="+gonghao,
				dataType : "json",
				type : "get",
				success : function(data){
					if(data==null){
						return;
					}else{
					$("#xingming").attr("value",data.xingming);
					$("#xingbie").attr("value",data.xingbie);
					$("#minzu").attr("value",data.minzu);
					$("#jiguan").attr("value",data.jiguan);
					$("#chushengriqi").attr("value",data.chushengriqi);
					$("#jkzhuangkuang").attr("value",data.jkzhuangkuang);
					$("#zzmianmao").attr("value",data.zzmianmao);
					$("#xueli").attr("value",data.xueli);
					$("#xuewei").attr("value",data.xuewei);
					$("#zhicheng").attr("value",data.zhicheng);
					$("#youxiang").attr("value",data.youxiang);
					$("#dianhua").attr("value",data.dianhua);
					if(data.yjsdaoshi=="博士生导师"){
						$("#bsdaoshi").attr("checked","checked");
					}else if(data.yjsdaoshi=="硕士生导师"){
						$("#ssdaoshi").attr("checked","checked");
					}else{
						$("#wu").attr("checked","checked");
					}
					$("#jxjingli").append(data.jxjingli);
					$("#yjjingli").append(data.yjjingli);
					$("#yjxingqu").append(data.yjxingqu);
					$("#hdjiangli").append(data.hdjiangli);
				}
			}
			});
		}
		
	
		//个人基本信息提交保存时合法性校验
		function saveCheck(){
			var xingming = $("#xingming").val();
			var xingbie = $("#xingbie").val();
			var minzu = $("#minzu").val();
			var jiguan = $("#jiguan").val();
			var chushengriqi = $("#chushengriqi").val();
			var jkzhuangkuang = $("#jkzhuangkuang").val();
			var zzmianmao = $("#zzmianmao").val();
			var xueli = $("#xueli").val();
			var xuewei = $("#xuewei").val();
			var zhicheng = $("#zhicheng").val();
			var youxiang = $("#youxiang").val();
			var dianhua = $("#dianhua").val();
			var jxjingli = $("#jxjingli").val();
			var yjjingli = $("#yjjingli").val();
			var yjxingqu = $("#yjxingqu").val();
			var hdjiangli = $("#hdjiangli").val();
			if(xingming=="")  	{alert("姓名不能为空！"); return false;}
			if(xingbie=="")  	{alert("性别不能为空！"); return false;}
			if(minzu=="")		{alert("民族不能为空！"); return false;}
			if(jiguan=="")		{alert("籍贯不能为空！"); return false;}
			if(chushengriqi=="")	{alert("出生日期不能为空！"); return false;}
			if(jkzhuangkuang=="")		{alert("健康状况不能为空！"); return false;}
			if(zzmianmao=="")	{alert("政治面貌不能为空！"); return false;}
			if(xueli=="")		{alert("学历不能为空！"); return false;}
			if(xuewei=="")		{alert("学位不能为空！"); return false;}
			if(zhicheng=="")	{alert("职称不能为空！"); return false;}
			if(youxiang=="")	{alert("邮箱不能为空！"); return false;}
			if(dianhua=="")		{alert("电话不能为空！"); return false;}
			if(jxjingli=="")	{alert("教学经历不能为空！若没有，则写无。"); return false;}
			if(yjjingli=="")	{alert("研究经历不能为空！若没有，则写无。"); return false;}
			if(yjxingqu=="")	{alert("研究兴趣不能为空！若没有，则写无。"); return false;}
			if(hdjiangli=="")	{alert("获得奖励不能为空！若没有，则写无。"); return false;}	
			return true;
		}
		
	</script>

</html>