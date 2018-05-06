package cn.scau.xuesheng.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scau.bean.Jiaoshi;
import cn.scau.xuesheng.service.XsJiaoshiService;

public class XsJiaoshiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private XsJiaoshiService jiaoshiService = new XsJiaoshiService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String gonghao = request.getParameter("gonghao");
		
		String jxbbianhao = request.getParameter("jxbbianhao");
		
		//获得该学生所选的老师
		Jiaoshi jiaoshi = jiaoshiService.showJiaoshi(gonghao);
		
		request.setAttribute("jiaoshi",jiaoshi);
		request.setAttribute("jxbbianhao", jxbbianhao);
		request.getRequestDispatcher("/jsps/xuesheng/pingjiaoxiangqing.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
