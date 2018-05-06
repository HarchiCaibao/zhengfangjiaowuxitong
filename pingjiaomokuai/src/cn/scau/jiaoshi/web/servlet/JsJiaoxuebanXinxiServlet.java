package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scau.bean.Jiaoxueban;
import cn.scau.bean.PageBean;
import cn.scau.jiaoshi.service.JsJiaoxuebanService;
import net.sf.json.JSONObject;
public class JsJiaoxuebanXinxiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JsJiaoxuebanService jsService = new JsJiaoxuebanService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 指定每页记录数
		int everyPageSize = 10;
		// 获得当前页数
		int currentPage = getPage(request);
		String gonghao = (String) request.getSession().getAttribute("gonghao");
		PageBean<Jiaoxueban> jiaoxuebans = jsService.getJiaoxueban(gonghao, currentPage, everyPageSize);
		JSONObject jxbsJson = JSONObject.fromObject(jiaoxuebans);
		out.println(jxbsJson);
		out.close();
	}
	// 获得jsp页面传来的当前页码
	private static int getPage(HttpServletRequest request) {
			String cp = request.getParameter("cp");
			if (cp == null || cp.trim().isEmpty()) {
				return 1;
			}
			return Integer.parseInt(cp);
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
