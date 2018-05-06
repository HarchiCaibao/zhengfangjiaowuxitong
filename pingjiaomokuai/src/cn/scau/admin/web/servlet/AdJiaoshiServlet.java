package cn.scau.admin.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.scau.admin.service.AdJiaoshiService;
import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.bean.PageBean;
import net.sf.json.JSONObject;

public class AdJiaoshiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdJiaoshiService adJiaoshiService = new AdJiaoshiService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 指定每页记录数
		int everyPageSize = 10;
		// 获得当前页数
		int currentPage = getPage(request);
		//开课学年
		int kaikexuenian = Integer.valueOf(request.getParameter("kaikexuenian"));
		int kaikexueqi = Integer.valueOf(request.getParameter("kaikexueqi"));
		// 分页查出所有教师信息
		PageBean<JiaoxuebanCustom> jiaoshis = adJiaoshiService.showJiaoshis(kaikexuenian,kaikexueqi,currentPage, everyPageSize);

		JSONObject jiaoshisjson = JSONObject.fromObject(jiaoshis);
		out.println(jiaoshisjson);
		out.close();
	}

	// 获得jsp页面传来的当前页码
	private int getPage(HttpServletRequest request) {
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
