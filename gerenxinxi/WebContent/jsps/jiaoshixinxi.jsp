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
	margin-top:108px;
	width:738px;
	
	
}
table.xinxitable td {
	border-width: 1px;
	padding: 3px;
	border-style: solid;
	border-color: #666666;
	text-align: center;
}
#title{
		font-family: verdana,arial,sans-serif;
    	font-size:28px;
   		color:#6B808F;
   		left:480px;
		top: 68px;
		position: absolute;
	}
</style>

</head>
<body>	
		<div id="title">教师个人简介表</div>
		<table class="xinxitable">
			<tr>
				<td width="68px">姓名</td>
				<td><input type="text" name="xingming" id="xingming" value="${jsXinxi.xingming }" readonly></td>
				<td width="68px">性别</td>
				<td><input type="text" name="xingbie" id="xingbie" value="${jsXinxi.xingbie }" readonly></td>
				<td rowspan="5"  width="138px" id="img">
					<img alt="头像" width="138px" height="168px" src="${pageContext.request.contextPath }/JsTxShowServlet?gonghao=${jsXinxi.gonghao }">
				</td>
			</tr>
			<tr>
				<td width="68px">民族</td>
				<td><input type="text" name="minzu" id="minzu" value="${jsXinxi.minzu }" readonly></td>
				<td width="68px">籍贯</td>
				<td><input type="text" name="jiguan" id="jiguan" value="${jsXinxi.jiguan }" readonly></td>
			</tr>
			<tr>
				<td width="68px">出生日期</td>
				<td><input type="text" name="chushengriqi" id="chushengriqi" value="${jsXinxi.chushengriqi }" readonly></td>
				<td width="68px">健康状况</td>
				<td><input type="text" name="jkzhuangkuang" id="jkzhuangkuang" value="${jsXinxi.jkzhuangkuang }" readonly></td>
			</tr>
			<tr>
				<td width="68px">政治面貌</td>
				<td><input type="text" name="zzmianmao" id="zzmianmao" value="${jsXinxi.zzmianmao }" readonly></td>
				<td width="68px">学历</td>
				<td><input type="text" name="xueli" id="xueli" value="${jsXinxi.xueli }" readonly></td>
			</tr>
			
			<tr>
				<td width="68px">学位</td>
				<td><input type="text" name="xuewei" id="xuewei" value="${jsXinxi.xuewei }" readonly></td>
				<td width="68px">职称</td>
				<td><input type="text" name="zhicheng" id="zhicheng" value="${jsXinxi.zhicheng }" readonly></td>				
			</tr>
			<tr>
				<td width="68px">邮箱</td>
				<td><input type="text" name="youxiang" id="youxiang" value="${jsXinxi.youxiang }" readonly></td>
				<td width="68px">电话</td>
				<td colspan="2"><input type="text" name="dianhua" size="40" id="dianhua" value="${jsXinxi.dianhua }" readonly></td>				
			</tr>
			<tr>
			   <td width="68px">研究生导师</td>
			   <td colspan="4">
			   		${jsXinxi.yjsdaoshi}
			   </td>
			</tr>
			<tr>
				<td width="68px">教学经历</td>
				<td colspan="4"><textarea rows="10" cols="78" name="jxjingli" id="jxjingli" readonly>${jsXinxi.jxjingli }</textarea></td>		
			</tr>
			<tr>
				<td width="68px">研究经历</td>
				<td colspan="4"><textarea rows="10" cols="78" name="yjjingli" id="yjjingli" readonly>${jsXinxi.yjjingli }</textarea></td>		
			</tr>
			<tr>
				<td width="68px">研究兴趣</td>
				<td colspan="4"><textarea rows="10" cols="78" name="yjxingqu" id="yjxingqu" readonly>${jsXinxi.yjxingqu }</textarea></td>		
			</tr>
			<tr>
				<td width="68px">获得奖励</td>
				<td colspan="4"><textarea rows="10" cols="78" name="hdjiangli" id="hdjiangli" readonly>${jsXinxi.hdjiangli }</textarea></td>		
			</tr>
		</table>
</body>
</html>