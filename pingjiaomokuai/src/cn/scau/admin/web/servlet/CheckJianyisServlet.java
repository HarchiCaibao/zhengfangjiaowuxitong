package cn.scau.admin.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scau.admin.service.AdJianyiCheckService;
import cn.scau.bean.Pingjiaoshuju;
import net.sf.json.JSONArray;

public class CheckJianyisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdJianyiCheckService adJyCheckService = new AdJianyiCheckService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		JSONArray jianyiJson = new JSONArray();
		//教师工号
		String gonghao = request.getParameter("gonghao");
		//教学班号
		String jxbbianhao = request.getParameter("jxbbianhao");
		//查出该教师对应教学班的学生建议
		List<Pingjiaoshuju> jianyis = adJyCheckService.getPjJianyi(jxbbianhao, gonghao);
		
		//将为空的评教建议加上“empty”标志，方便前端显示操作
		for (Pingjiaoshuju jianyi : jianyis) {
		    String jy = jianyi.getJianyi().trim();
		    if (jy.isEmpty()) {
				jianyi.setJianyi("empty");
			}else {
				jianyi.setJianyi(jy);
			}
		}
		
		jianyiJson = JSONArray.fromObject(jianyis);
		out.print(jianyiJson);
		out.flush();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
