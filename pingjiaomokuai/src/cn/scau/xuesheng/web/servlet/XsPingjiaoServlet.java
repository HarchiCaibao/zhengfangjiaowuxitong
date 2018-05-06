package cn.scau.xuesheng.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scau.bean.Pingjiaoshuju;
import cn.scau.xuesheng.service.XsChengjiService;
import cn.scau.xuesheng.service.XsPingjiaoshujuService;

public class XsPingjiaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private XsChengjiService chengjiService = new XsChengjiService();
	private XsPingjiaoshujuService pingjiaoshujuService = new XsPingjiaoshujuService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 解决乱码问题
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 获取学号
		String xuehao = (String) request.getSession().getAttribute("xuehao");
		String jxbbianhao = request.getParameter("jxbbianhao");
		String gonghao = request.getParameter("gonghao");

		try {

			// 获取提交的评教数据,并保存于实例中
			Pingjiaoshuju pingjiaoshuju = new Pingjiaoshuju();
			pingjiaoshuju.setXuehao(xuehao);
			pingjiaoshuju.setGonghao(gonghao);
			pingjiaoshuju.setJxbbianhao(jxbbianhao);
			pingjiaoshuju.setChuqin(Integer.parseInt(request.getParameter("chuqin")));
			pingjiaoshuju.setKetang(Integer.parseInt(request.getParameter("ketang")));
			pingjiaoshuju.setSsguanxi(Integer.parseInt(request.getParameter("ssguanxi")));
			pingjiaoshuju.setShouhuo(Integer.parseInt(request.getParameter("shouhuo")));
			pingjiaoshuju.setZuoyeliang(Integer.parseInt(request.getParameter("zuoyeliang")));
			pingjiaoshuju.setJianyi(request.getParameter("jianyi"));

			// 禁止评分分数全为10分的情况
			if (pingjiaoshuju.getChuqin() == 10 && pingjiaoshuju.getKetang() == 10
					&& pingjiaoshuju.getSsguanxi() == 10) {
				request.setAttribute("fail", "10");
				request.setAttribute("gonghao", gonghao);
				request.setAttribute("jxbbianhao", jxbbianhao);
				request.getRequestDispatcher("/XsJiaoshiServlet").forward(request, response);
				return;
			}
			// 禁止评分分数全为1分的情况
			if (pingjiaoshuju.getChuqin() == 1 && pingjiaoshuju.getKetang() == 1 && pingjiaoshuju.getSsguanxi() == 1) {
				request.setAttribute("fail", "1");
				request.setAttribute("gonghao", gonghao);
				request.setAttribute("jxbbianhao", jxbbianhao);
				request.getRequestDispatcher("/XsJiaoshiServlet").forward(request, response);
				return;
			}

			// 保存评教数据到数据库中
			pingjiaoshujuService.savePingjiaoshuju(pingjiaoshuju);

			// 给对应已经评教的的课程的成绩表的pingjiao列设为“yes”
			chengjiService.setYes(xuehao, jxbbianhao);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		/*
		 * 判断是否完全评教完 如果没有则继续去评教 如果评教完成弹出评教完成提示
		 */
		if (chengjiService.isAllYes(xuehao)) {
			String path = request.getContextPath();
			response.sendRedirect(path + "/XsXueshengServlet");
		} else {
			request.getSession().setAttribute("msg", "yes");
			request.getRequestDispatcher("/jsps/xuesheng/pingjiaoxiangqing.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
