package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.scau.jiaoshi.service.JsJxriliService;

//保存教师提交的教学日历信息
public class JsJxriliSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JsJxriliService jxriliService = new JsJxriliService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {
			//获得周学时
			int zhouxueshi = Integer.valueOf(request.getParameter("zhouxueshi"));
			
			// 获得主讲教师
			String zjjiaoshi = request.getParameter("zjjiaoshi");
			//主讲教师是否为空由jsp页面判断并给出提示
			
			//保存所有教学日历的字符串
			String jiaoxuerilis = "";
			
			// 获得教学日历的总数目
			String sums = request.getParameter("kecimax");
			int sum = Integer.parseInt(sums);

			// 获得教学班组成
			String jiaoxuebans = request.getParameter("jiaoxueban");
			// 将主讲教师及教学班连接在一起，用“/”分隔
			zjjiaoshi = zjjiaoshi + "/" + jiaoxuebans + "!";
			jiaoxuerilis = jiaoxuerilis.concat(zjjiaoshi);

			/*
			 * 循环取出教学日历内容，并将它们连接在一起,每课次教学日历的所有内容 
			 * 连接格式为：主讲教师/教学班组成!课次%周次*授课形式?授课内容
			 * 不同课次的教学日历采用“;;;”分隔
			 */
			for (int i = 0; i < sum; i++) {
				//连接字符串变成jsp页面提供的name属性值，以获取对应的值
				String zhouci = "zhouci".concat(String.valueOf(i));
				String xingshi = "xingshi".concat(String.valueOf(i));
				String neirong = "neirong".concat(String.valueOf(i));
				//获取每项教学日历的值
				String zc = request.getParameter(zhouci);
				String xs = request.getParameter(xingshi);
				String nr = request.getParameter(neirong);
				String kc = String.valueOf(i + 1);
				//将一次课程的教学日历连接起来
				//连接格式为：主讲教师/教学班组成!课次%周次*授课形式?授课内容
				String jiaoxuerili = kc + "%" + zc + "*" + xs + "?" + nr + ";;;";
				//将所有教学日历连接起来，用“;;;”分割隔
				jiaoxuerilis = jiaoxuerilis.concat(jiaoxuerili);
				
			}
			// 如果未曾保存过教学日历则保存，否则更新教学日历
			if (jxriliService.findJxrili(jiaoxuebans) == null) {
				jxriliService.saveJxrili(jiaoxuebans, jiaoxuerilis,zhouxueshi);
				//保存标志，让页面弹出保存成功
				request.setAttribute("msg", "save");
				request.getRequestDispatcher("/jsps/jiaoshi/jsindex.jsp").forward(request, response);
			} else {
				jxriliService.updateJxrili(jiaoxuebans, jiaoxuerilis);
				//更新标志，让页面弹出更新成功
				request.setAttribute("msg", "update");
				request.getRequestDispatcher("/jsps/jiaoshi/jsindex.jsp").forward(request, response);	
			}
			
		} catch (NumberFormatException e) {
			
			//未解决跳转后页面空白，数据被刷新的问题。（12.29）	
			PrintWriter out = response.getWriter();
			System.out.println("周学时为不合法值!");
			out.println("<script>alert('周学时为不合法值!')</script>");
		    out.close();
		    
		} 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
