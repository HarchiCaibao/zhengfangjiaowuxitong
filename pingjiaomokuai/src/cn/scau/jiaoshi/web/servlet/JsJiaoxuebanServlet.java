package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.scau.jiaoshi.service.JsJiaoxuebanService;
/**
 * 2018.3.29修改
 * @author 王汉志
 *
 */
public class JsJiaoxuebanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JsJiaoxuebanService jsJiaoxuebanService = new JsJiaoxuebanService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//模拟教师登录成功，将教师的工号保存在session域中
		HttpSession session = request.getSession();
		session.setAttribute("gonghao", "102");
		String gonghao = (String) session.getAttribute("gonghao");
		
		//查出该老师所有开课学年
		List<Integer> jiaoxuebans = jsJiaoxuebanService.showXuenian(gonghao);
		
		request.setAttribute("jiaoxuebans", jiaoxuebans);
		//转发
		request.getRequestDispatcher("/jsps/jiaoshi/pingjiao.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
