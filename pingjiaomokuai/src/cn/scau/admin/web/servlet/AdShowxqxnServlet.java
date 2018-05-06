package cn.scau.admin.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scau.admin.service.AdJiaoshiService;
import net.sf.json.JSONArray;

public class AdShowxqxnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdJiaoshiService adJiaoshiService = new AdJiaoshiService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		JSONArray xuenian = new JSONArray();
		List<Integer> xuenians = adJiaoshiService.showXuenians();
		//去重
		xuenians = new ArrayList<>(new HashSet<>(xuenians));
		//顺序排列
		Collections.sort(xuenians);
		//逆序排列
		Collections.reverse(xuenians);
		
		xuenian = JSONArray.fromObject(xuenians);
		response.getWriter().println(xuenian);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
