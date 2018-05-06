package cn.scau.xuesheng.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.xuesheng.service.XsJiaoxuebanService;

//类似系统主页功能
//11.10晚改
//点击主页按钮进入教学日历查看页面
public class XsIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private XsJiaoxuebanService xsjxbService = new XsJiaoxuebanService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 假设成功登录，将工号保存在session域中
		HttpSession session = request.getSession();
		session.setAttribute("xuehao", "2011");
		// 取出学号
		String xuehao = (String) session.getAttribute("xuehao");

		List<JiaoxuebanCustom> jiaoxuebans = xsjxbService.showJxbByXh(xuehao);
		// 由于学年需要去重及倒序，这里在后台完成该工作，并发送到jsp中
		List<String> xuenian = new ArrayList<>();
		// 将学年转换为 xxxx-xxxx 的形式
		for (JiaoxuebanCustom jsc : jiaoxuebans) {
			if (jsc.getKaikexueqi() == 1) {
				int shangxueqi = jsc.getKaikexuenian() - 1;
				String xiaxueqi = String.valueOf(jsc.getKaikexuenian());
				xuenian.add(String.valueOf(shangxueqi) + "-" + xiaxueqi);
			} else {
				int xiaxueqi = jsc.getKaikexuenian() + 1;
				String shangxueqi = String.valueOf(jsc.getKaikexuenian());
				xuenian.add(shangxueqi + "-" + String.valueOf(xiaxueqi));
			}
		}
		// 去重
		xuenian = new ArrayList<>(new HashSet<>(xuenian));
		// 将学年升序排列
		Collections.sort(xuenian);
		// 讲学年逆序，即变成了降序
		Collections.reverse(xuenian);
		request.setAttribute("xuenian", xuenian);
		request.getRequestDispatcher("/jsps/xuesheng/xsteachCalendar.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
