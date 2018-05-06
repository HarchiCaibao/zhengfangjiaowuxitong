package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scau.jiaoshi.service.JiaoshiTxService;

//查询数据库显示用户头像
public class JsTxShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JiaoshiTxService jsTxService = new JiaoshiTxService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//获得工号
		String gonghao = request.getParameter("gonghao");
		InputStream in = jsTxService.showJiaoshiTx(gonghao);
		//用户第一完善个人信息，数据未保存过头像
		if (in == null) {
			PrintWriter out = response.getWriter();
			out.write("no");
		} else {//数据库已保存过头像，查出来显示
			
			OutputStream out = response.getOutputStream();
			int len = 0;
			byte buffer[] = new byte[1024];
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.flush();
			in.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
