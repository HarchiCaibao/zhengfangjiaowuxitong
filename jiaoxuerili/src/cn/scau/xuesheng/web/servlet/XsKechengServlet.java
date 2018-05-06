package cn.scau.xuesheng.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.xuesheng.service.XsJiaoxuebanService;
import net.sf.json.JSONArray;

//11.10晚改
public class XsKechengServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private XsJiaoxuebanService xsService = new XsJiaoxuebanService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		//学年
		String xuenian = request.getParameter("xuenian");
		//学期
		String xueqi = request.getParameter("xueqi");
		//学号
		String xuehao = (String) request.getSession().getAttribute("xuehao");
		if(xueqi.equals("1")) {
			xuenian = xuenian.substring(xuenian.indexOf("-")+1);
		}else {
			xuenian = xuenian.substring(0, xuenian.indexOf("-"));
		}
		//根据学年学期，学号查出信息
		List<JiaoxuebanCustom> jiaoxuebanCustoms = xsService.XsShowJxbByXnXq(xuehao, xuenian, xueqi);
		JSONArray jxbCustoms = JSONArray.fromObject(jiaoxuebanCustoms);
		out.print(jxbCustoms);
		out.flush();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
