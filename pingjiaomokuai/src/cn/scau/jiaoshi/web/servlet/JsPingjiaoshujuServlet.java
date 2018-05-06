package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.scau.jiaoshi.service.JsChengjiService;
import cn.scau.jiaoshi.service.JsPingjiaoshujuService;

public class JsPingjiaoshujuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JsPingjiaoshujuService jsPjsjService = new JsPingjiaoshujuService();
	private JsChengjiService jsChengjiService = new JsChengjiService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gonghao = (String) request.getSession().getAttribute("gonghao");
		String jxbbianhao = request.getParameter("jxbbianhao");
		// 学生是否选了该门课
		if (!jsChengjiService.xsIsSelect(jxbbianhao)) {
			request.setAttribute("nofinish", "noselect");
			request.getRequestDispatcher("/jsps/jiaoshi/chakanpingjiao.jsp").forward(request, response);
		} else {//是否已经评教完成
			if (jsChengjiService.xsIsAllfinishPj(jxbbianhao)) {
				Map<String, Double> pjshujuAvgs = jsPjsjService.getPjshujuAvg(jxbbianhao, gonghao);
				List<String> jianyis = jsPjsjService.getPjJianyi(jxbbianhao, gonghao);
				request.setAttribute("pjshujuAvgs", pjshujuAvgs);
				request.setAttribute("jianyis", jianyis);
				request.getRequestDispatcher("/jsps/jiaoshi/chakanpingjiao.jsp").forward(request, response);
			} else {
				request.setAttribute("nofinish", "nofinish");
				request.getRequestDispatcher("/jsps/jiaoshi/chakanpingjiao.jsp").forward(request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
