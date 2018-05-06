//package cn.scau.jiaoshi.web.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import cn.scau.bean.JiaoxuebanCustom;
//import cn.scau.jiaoshi.service.JsJiaoxuebanService;
//import net.sf.json.JSONArray;
//
////类似教师登录功能，将工号保存在session域中，
////点击页面的教学日历按钮跳转到教学日历显示页面
//public class JsJiaoxuebanServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private JsJiaoxuebanService jsJxbService = new JsJiaoxuebanService();
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		
//		PrintWriter out = response.getWriter();
//		String gonghao = request.getParameter("gonghao");		
//		//查询获得该老师所教的教学班信息
//		List<JiaoxuebanCustom> jiaoxuebans = jsJxbService.showJxbByGh(gonghao);	
//		JSONArray jiaoxuebanList = JSONArray.fromObject(jiaoxuebans);
//		out.print(jiaoxuebanList);
//		out.flush();
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}
