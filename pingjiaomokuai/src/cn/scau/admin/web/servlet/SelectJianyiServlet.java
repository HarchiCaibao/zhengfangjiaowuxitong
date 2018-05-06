package cn.scau.admin.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scau.dao.impl.PingjiaoshujuDaoImpl;

public class SelectJianyiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PingjiaoshujuDaoImpl pj = new PingjiaoshujuDaoImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String pjids = request.getParameter("pjids");
		try {
			String[] pjid = pjids.split("-");
			Integer[] ids = new Integer[pjid.length];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Integer.valueOf(pjid[i]);
			}
			// 执行更新
			pj.updateBatch(ids);
			response.getWriter().print("y");
		} catch (NumberFormatException e) {
			// 没有选择要删除的建议，却点击了保存
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
