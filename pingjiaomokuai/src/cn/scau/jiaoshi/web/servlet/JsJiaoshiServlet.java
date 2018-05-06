//package cn.scau.jiaoshi.web.servlet;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class JsJiaoshiServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		//模拟教师登录成功，将教师的工号保存在session域中
//		request.getSession().setAttribute("gonghao", "102");
//		
//		
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		doGet(request, response);
//	}
//
//}
