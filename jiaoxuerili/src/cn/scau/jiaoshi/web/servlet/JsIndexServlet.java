package cn.scau.jiaoshi.web.servlet;

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
import cn.scau.jiaoshi.service.JsJiaoxuebanService;

//该servlet只是为了将工号保存到session域中，相当于系统中教师成功登录
//故在整合时可以去掉，但是调试时必须经过该servlet，因为之后需要用到session域的工号

public class JsIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JsJiaoxuebanService jiaoxuebanService = new JsJiaoxuebanService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//假设成功登录，将工号保存在session域中
		HttpSession session = request.getSession();
		session.setAttribute("gonghao", "102");
		//根据工号查出该老师的所教教学班增强类信息
		List<JiaoxuebanCustom> jiaoxuebans = jiaoxuebanService.showJxbByGh((String) request.getSession().getAttribute("gonghao"));
		//由于学年需要去重及倒序，这里在后台完成该工作，并发送到jsp中
		List<String> xuenian = new ArrayList<>();
		//将学年转换为 xxxx-xxxx 的形式
		for(JiaoxuebanCustom jsc : jiaoxuebans) {
			if(jsc.getKaikexueqi()==1) {
				int shangxueqi = jsc.getKaikexuenian() - 1;
				String xiaxueqi = String.valueOf(jsc.getKaikexuenian());
				xuenian.add(String.valueOf(shangxueqi)+"-"+xiaxueqi);
			}else {
				int xiaxueqi = jsc.getKaikexuenian() + 1;
				String shangxueqi = String.valueOf(jsc.getKaikexuenian());
				xuenian.add(shangxueqi+"-"+String.valueOf(xiaxueqi));
			}
		}
		//去重
		xuenian = new ArrayList<>(new HashSet<>(xuenian));
		//将学年升序排列
		Collections.sort(xuenian);
		//讲学年逆序，即变成了降序
		Collections.reverse(xuenian);
		
		//request.setAttribute("xuenian", xuenian);
		session.setAttribute("xuenian", xuenian);
		//转发到教学日历显示页面
		//request.getRequestDispatcher("/jsps/jiaoshi/jsteachCalendar.jsp").forward(request, response);
		
		String path = request.getContextPath();
		//重定向到评价主页
		response.sendRedirect(path+"/jsps/jiaoshi/jsteachCalendar.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
