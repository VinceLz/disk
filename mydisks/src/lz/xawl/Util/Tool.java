package lz.xawl.Util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

import lz.xawl.File.domain.File;

public class Tool {
	/**
	 * 生成随机四位字符串
	 * 
	 * @return
	 */
	public static String randomId() {

		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 4; ++i) {
			int number = random.nextInt(62);// [0,62)

			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public static byte[] createChecksum(InputStream fis) throws Exception {
		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;

		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	/**
	 * 获取文件的hash
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static String getMD5Checksum(InputStream in) throws Exception {
		byte[] b = createChecksum(in);
		String result = "";

		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
	
	public static void deleteFileList(List<File> myfile){
		
	}
	
}
