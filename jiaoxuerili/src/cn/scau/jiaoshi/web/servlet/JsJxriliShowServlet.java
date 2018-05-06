package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.scau.bean.Jiaoxuerili;
import cn.scau.jiaoshi.service.JsJxriliService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//显示教学日历
//使用json来传输数据到jsp页面
public class JsJxriliShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JsJxriliService jxriliService = new JsJxriliService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String jxbbianhao = request.getParameter("jxbbianhao");
		Jiaoxuerili jiaoxuerili = jxriliService.findJxrili(jxbbianhao);
		JSONObject rili = new JSONObject();
		
		//如果教师尚未保存过教学日历信息
		if (jiaoxuerili == null) {
			rili.put("status", "null");
			out.print(rili);
			out.close();
		}else {
			
			//连接格式为：主讲教师/教学班组成!课次%周次*授课形式?授课内容
	        //不同课次的教学日历采用“;;;”分隔
			//获得各种教学日历项的值
			String jxrilis= jiaoxuerili.getJiaoxuerili();
			//获得周学时
			int zhouxueshi = jiaoxuerili.getZhouxueshi();
			String zjjiaoshi = jxrilis.substring(0, jxrilis.indexOf("/"));
			String jxrilis2 = jxrilis.substring(jxrilis.indexOf("/"));
			//将每次的教学日历一“;;;”分割开并保存在数组中
			String[] jxrilis3 = jxrilis2.split(";;;");
			//定义一个JSON数组
			JSONArray jxriliArray = new JSONArray();
			
			//将每次的教学日历信息保存在一个JSONObject中，并将每次的JSONObject作为JSON数组的一个元素
			for(int i= 0;i<jxrilis3.length;i++) {
				JSONObject dayRili = new JSONObject();
				dayRili.put("zhouci", jxrilis3[i].substring(jxrilis3[i].indexOf("%")+1, jxrilis3[i].indexOf("*")));
				dayRili.put("xingshi", jxrilis3[i].substring(jxrilis3[i].indexOf("*")+1, jxrilis3[i].indexOf("?")));
				dayRili.put("neirong", jxrilis3[i].substring(jxrilis3[i].indexOf("?")+1));
				//将每次的JSONObject作为JSON数组的一个元素
				jxriliArray.add(i, dayRili);
			}
			//将教学日历数组，主讲教师，判断数据库是否保存有教学日历的值放进JSONObject中，并传输到jsp页面中
			rili.put("jiaoxuerilis", jxriliArray);
			rili.put("zjjiaoshi", zjjiaoshi);
			//添加周学时显示
			rili.put("zhouxueshi", zhouxueshi);
			rili.put("status", "full");
			out.print(rili);
			out.close();
		}	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
