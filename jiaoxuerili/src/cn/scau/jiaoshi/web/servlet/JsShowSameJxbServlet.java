package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.jiaoshi.service.JsJiaoxuebanService;
import net.sf.json.JSONArray;

public class JsShowSameJxbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JsJiaoxuebanService jsService = new JsJiaoxuebanService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		List<JiaoxuebanCustom> jxbSame = new ArrayList<>();
		JSONArray jxbSames = new JSONArray();
		PrintWriter out = response.getWriter();
		String gonghao = (String) request.getSession().getAttribute("gonghao");
		int xueqi = Integer.valueOf(request.getParameter("xueqi"));
		int xuenian = Integer.valueOf(request.getParameter("xuenian"));
		String kcName = request.getParameter("kcName");
		List<JiaoxuebanCustom> jxb = jsService.showJxbByGh(gonghao);
		for (JiaoxuebanCustom jxbCustom : jxb) {
			//如果是同一门课程
			if (jxbCustom.getMingcheng().equals(kcName)) {
				if (jxbCustom.getKaikexuenian() > xuenian) {
					continue;
				} else if (jxbCustom.getKaikexuenian() < xuenian) {
					// 如果学年比当前课程小，即是往年课程
					jxbSame.add(jxbCustom);
				} else if (jxbCustom.getKaikexueqi() < xueqi) {
					// 如果学年相等，学期较小，即是往年课程
					jxbSame.add(jxbCustom);
				}
			}
		}
		
		jxbSames = JSONArray.fromObject(jxbSame);
		out.println(jxbSames);
		out.close();		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
