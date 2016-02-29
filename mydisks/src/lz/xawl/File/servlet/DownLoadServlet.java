package lz.xawl.File.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Encoder;

import lz.xawl.File.domain.File;
import lz.xawl.File.service.FileService;

public class DownLoadServlet extends HttpServlet {

	private FileService fileService = new FileService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String fid = req.getParameter("fid");
		String cid = req.getParameter("cid");
		File myfile = fileService.findByFid(fid);
		String path = this.getServletContext().getRealPath(myfile.getfPath());

		String framename = filenameEncoding(myfile.getfName(), req);

		String contentType = myfile.getfType();// 通过文件名称获取MIME类型
		String contentDisposition = "attachment;filename=" + framename;
		// 一个流
		FileInputStream input = new FileInputStream(path);

		// 设置头
		resp.setHeader("Content-Type", contentType);
		resp.setHeader("Content-Disposition", contentDisposition);
		resp.setHeader("content-Length", myfile.getfSize() + "");
		// resp.setContentType("application/octet-stream");
		// 获取绑定了响应端的流
		ServletOutputStream output = resp.getOutputStream();
		try {

			int count = Integer.parseInt(myfile.getfDowncount());
			count++;
			fileService.editcount(count + "", myfile.getfId());
			// IOUtils.copy(input, output);// 把输入流中的数据写入到输出流中。
			byte[] bytes = new byte[1024 * 4];
			int len = 0;
			while ((len = input.read(bytes)) != -1) {
				output.write(bytes, 0, len);

			}

		} catch (Exception e) {
			System.out.println("报错了");
		} finally {
			System.out.println("清空了");
			input.close();
			output.flush();
			output.close();

		}
	}

	public static String filenameEncoding(String filename,
			HttpServletRequest request) throws IOException {
		String agent = request.getHeader("User-Agent"); // 获取浏览器
		if (agent.contains("Firefox")) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?"
					+ base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
		} else if (agent.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;

	}

}
