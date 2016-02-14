package lz.xawl.File.servlet;

public class fileUploadStatus {
	private long pBytesRead = 0L;
	private long pContentLength = 0L;
	public fileUploadStatus(){
		pBytesRead = 0L;
		pContentLength = 0L;
	}
	public long getPBytesRead() {
		return pBytesRead;
	}
	public void setPBytesRead(long bytesRead) {
		pBytesRead = bytesRead;
	}
	public long getPContentLength() {
		return pContentLength;
	}
	public void setPContentLength(long contentLength) {
		pContentLength = contentLength;
	}
}
