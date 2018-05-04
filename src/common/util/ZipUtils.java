package common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;

/**
 * 压缩和解压工具类
 * 
 * @author TangerineSpecter
 *
 */
public class ZipUtils {
	private static Logger logger = Logger.getLogger(ZipUtils.class);

	/***
	 * 压缩数据
	 * 
	 * @param data
	 *            要压缩的二进制数据
	 * @return
	 */
	public static byte[] gZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception e) {
			logger.error(String.format("【压缩数据异常】：%s", e));
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 解压数据
	 * 
	 * @param data
	 *            要解压的二进制数据
	 * @return
	 */
	public static byte[] unGZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		} catch (Exception e) {
			logger.error("【解压数据流出错】");
		}
		return b;
	}

}
