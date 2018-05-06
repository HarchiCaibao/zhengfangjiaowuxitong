package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.scau.bean.JsJianjie;
import cn.scau.jiaoshi.service.JsJianjieService;
import net.sf.json.JSONObject;

public class JsXinxiShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JsJianjieService jsJianjieService  = new JsJianjieService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//使用flag判断是学生查看信息，还是教师个人查看信息
		String flag = request.getParameter("flag");
		
		if("admin".equals(flag)) {
			//获得姓名
			String xingming = request.getParameter("xingming");
			//解决乱码问题
			xingming = new String(xingming.getBytes("ISO8859-1"),"UTF-8");
			//获得工号
			String gonghao = jsJianjieService.getGonghao(xingming);
			//获得教师的个人信息
			JsJianjie jsJianjie = jsJianjieService.showByGh(gonghao);
			request.setAttribute("jsXinxi", jsJianjie);
			
			request.getRequestDispatcher("jsps/jiaoshixinxi.jsp").forward(request, response);
			
		}else {
			//System.out.println("教师登录！");
			//通过工号获得教师个人信息
			String gonghao = request.getParameter("gonghao");
			JsJianjie jsJianjie = jsJianjieService.showByGh(gonghao);
			request.setAttribute("jsXinxi", jsJianjie);
			
			PrintWriter out = response.getWriter();
			//转换成json方便在jsp页面输出
			JSONObject jsXinxi = JSONObject.fromObject(jsJianjie);
			out.println(jsXinxi);
			out.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
