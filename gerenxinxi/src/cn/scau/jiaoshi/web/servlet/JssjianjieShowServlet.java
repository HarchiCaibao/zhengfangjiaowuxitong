package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.scau.bean.JsJianjie;
import cn.scau.bean.PageBean;
import cn.scau.jiaoshi.service.JsJianjieService;
import net.sf.json.JSONObject;

//分页获得教师上传的信息，到页面上显示
public class JssjianjieShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JsJianjieService jsJianjieService = new JsJianjieService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//指定每页记录数
		int everyPageSize = 10;
		
		//获得当前页数
		int currentPage = getPage(request);
		
		//获得所有老师的信息及分页信息
		PageBean<JsJianjie> lists = jsJianjieService.show(currentPage,everyPageSize);
		
		//转换为Json格式		
		JSONObject jsjianjieList = JSONObject.fromObject(lists);
		out.println(jsjianjieList);
		out.close();
	}
	
	//获得jsp页面传来的当前页码
	private int getPage(HttpServletRequest request) {
		String cp = request.getParameter("cp");
		if (cp==null||cp.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(cp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
