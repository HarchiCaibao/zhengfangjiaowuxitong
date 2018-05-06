package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.scau.bean.JsJianjie;
import cn.scau.jiaoshi.service.JsJianjieService;
import cn.scau.utils.JavaBeanUtils;

//获得提交的除开头像的信息，并保存或者更新
public class JsjianjieSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JsJianjieService jsJianjieService = new JsJianjieService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String gonghao = request.getParameter("gonghao");
		Map<String, String[]> xinxiMap = request.getParameterMap();
		JsJianjie jsJianjie = (JsJianjie) JavaBeanUtils.toBean(xinxiMap, JsJianjie.class);
		PrintWriter out = response.getWriter();
		
		if (jsJianjie.getXingming().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('姓名不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getXingbie().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('性别不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getMinzu().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('民族不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getJiguan().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('籍贯不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getChushengriqi().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('出生日期不能为空!'); window.history.go(-1);</script>");
			return;
		}/*else{
			String born = jsJianjie.getChushengriqi();
			String date = "\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}";
			Pattern pattern = Pattern.compile(date);
			if (!pattern.matcher(born).matches()) {
				out.print("<script type='text/javascript'>alert('出生日期格式错误!'); window.history.go(-1);</script>");
				return;
			}
		}*/
		if (jsJianjie.getJkzhuangkuang().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('健康状况不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getZzmianmao().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('政治面貌况不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getXueli().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('学历不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getXuewei().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('学位不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getZhicheng().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('职称不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getYouxiang().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('邮箱不能为空!'); window.history.go(-1);</script>");
			return;
		}else {
			String youxiang = jsJianjie.getYouxiang();
			String email = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
			Pattern pattern = Pattern.compile(email);
			if (!pattern.matcher(youxiang).matches()) {
				out.print("<script type='text/javascript'>alert('邮箱格式错误!'); window.history.go(-1);</script>");
				return;
			}	
		}
		if (jsJianjie.getDianhua().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('电话不能为空!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getJxjingli().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('教学经历不能为空!若没有，则写无!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getYjjingli().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('研究经历不能为空!若没有，则写无!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getYjxingqu().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('研究兴趣不能为空!若没有，则写无!'); window.history.go(-1);</script>");
			return;
		}
		if (jsJianjie.getHdjiangli().trim().isEmpty()) {
			out.print("<script type='text/javascript'>alert('获得奖励不能为空!若没有，则写无!'); window.history.go(-1);</script>");
			return;
		}
		//如果从未保存过信息
		if (jsJianjieService.showByGh(gonghao)!=null) {
			jsJianjieService.updateXinxi(gonghao, jsJianjie);
			request.setAttribute("msg", "update");
		}else {
			jsJianjieService.save(jsJianjie);
			request.setAttribute("msg", "save");
		}	
		request.getRequestDispatcher("/jsps/jsindex.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
