package cn.scau.xuesheng.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.scau.bean.Jiaoshi;
import cn.scau.xuesheng.service.XsChengjiService;
import cn.scau.xuesheng.service.XsXueshengService;

public class XsXueshengServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private XsXueshengService xueshengService = new XsXueshengService();
	private XsChengjiService chengjiService = new XsChengjiService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 假设成功登录，获得了xuehao属性
		HttpSession session = request.getSession();
		//模拟将学号放入senssion域中
		session.setAttribute("xuehao", "2011");
		
		String xuehao = (String) session.getAttribute("xuehao");
		/*判断是否完全评教完
		 * 如果是则弹出提示
		 * 如果不是则继续评教
		*/
		if (chengjiService.isAllYes(xuehao)) {
			
			//得到已经评教的班级，让jsp页面显示已评教
			List<String> jxbbianhaos = chengjiService.isYes(xuehao);
			//得到该学生所选的老师
			List<Jiaoshi> jiaoshis = xueshengService.getJiaoshi(xuehao);
			request.setAttribute("jiaoshis", jiaoshis);
			request.setAttribute("jxbbianhaos", jxbbianhaos);
			request.getRequestDispatcher("/jsps/xuesheng/pingjiao.jsp").forward(request, response);
		}else {
			//已经完成评教
			response.getWriter().println("<script type='text/javascript'>alert('你已经完成教学质量评估,不能重复评价！');"
					+ "location.href = 'jsps/xuesheng/xsindex.jsp' "
					+ "</script>");
			
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
