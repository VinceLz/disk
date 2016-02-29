package lz.xawl.File.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class myProgressListener implements ProgressListener {

	private HttpSession session;

	public myProgressListener(HttpServletRequest req) {
		session = req.getSession();
		fileUploadStatus status = new fileUploadStatus();
		session.setAttribute("status", status);
	}

	/*
	 * pBytesRead 到目前为止读取文件的比特数 pContentLength 文件总大小
	 */
	public void update(long pBytesRead, long pContentLength, int pItems) {
		fileUploadStatus status = (fileUploadStatus) session
				.getAttribute("status");
		status.setPBytesRead(pBytesRead);
		status.setPContentLength(pContentLength);
	}

}
