package cn.scau.jiaoshi.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import cn.scau.jiaoshi.service.JiaoshiTxService;

/*获取用户上传的头像并更新保存到数据库*/
public class JsTxUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JiaoshiTxService jsTxService = new JiaoshiTxService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 从session域中取出工号
		String gonghao = (String) request.getSession().getAttribute("gonghao");
		// 创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 得到解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 解析request，获得表单数据
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> file = sfu.parseRequest(request);
			// 得到上传的图片的文件对象
			FileItem imgFile = file.get(0);
			// 获取用户上传图片的大小
			long size = imgFile.getSize();
			// 如果用户并没有自定义上传头像
			// 则不更新
			if (size == 0) {
				return;
			} else {
				// 转换为输入流
				InputStream in = imgFile.getInputStream();
				// 保存头像
				jsTxService.updateGerenTx(gonghao, in);
			}
		} catch (FileUploadException e) {
			// 跳转回到提交页面
			//request.getRequestDispatcher("/jsps/fillxinxi.jsp").forward(request, response);
			//PrintWriter out = response.getWriter();
			//out.print("error");
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
