package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.jiaoshi.service.JsJiaoxuebanService;
import net.sf.json.JSONArray;

public class JsKechengServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JsJiaoxuebanService jxJxbService = new JsJiaoxuebanService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		//学年
		String xuenian = request.getParameter("xuenian");
		//学期
		String xueqi = request.getParameter("xueqi");
		//工号
		String gonghao = (String) request.getSession().getAttribute("gonghao");
		if(xueqi.equals("1")) {
			xuenian = xuenian.substring(xuenian.indexOf("-")+1);
		}else {
			xuenian = xuenian.substring(0, xuenian.indexOf("-"));
		}
		//根据学年学期，工号查出信息
		List<JiaoxuebanCustom> jiaoxuebanCustoms = jxJxbService.showJxbByXnXq(gonghao, xuenian, xueqi);
		JSONArray jxbCustoms = JSONArray.fromObject(jiaoxuebanCustoms);
		out.print(jxbCustoms);
		out.flush();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
