package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scau.jiaoshi.service.JiaoshiTxService;

//保存系统提供的头像，系统头像直接保存在src目录下，WebContent目录下也有一样的文件夹
public class JsXitongTxSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JiaoshiTxService xtTxService = new JiaoshiTxService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//从session域中取出工号
		String gonghao = (String) request.getSession().getAttribute("gonghao");
		//获得系统默认头像的保存路径
		String path = request.getParameter("path");
		//保存系统默认头像
		xtTxService.saveXitongTx(gonghao, path);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
