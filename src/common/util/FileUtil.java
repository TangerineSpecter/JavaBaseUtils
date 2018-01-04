package common.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

/**
 * 文件操作工具类
 * 
 * @author TangerSpecter
 *
 */
public class FileUtil {

	/**
	 * 读取文件并压缩数据然后转Base64编码
	 * 
	 * @param filePath
	 *            文件的绝对路径地址
	 * @return
	 */
	public static String base64(String filePath) {
		byte[] data = ImageUtils.getPicData(filePath);
		if (data == null) {
			return null;
		}
		byte[] zipData = RarUtils.gZip(data);
		return Base64.encodeBase64String(zipData);
	}

	/**
	 * 将二进制压缩数据转成Base64编码
	 * 
	 * @param data
	 *            二进制压缩数据
	 * @return
	 */
	public static String base64(byte[] data) {
		if (data == null) {
			return null;
		}
		byte[] zipData = RarUtils.gZip(data);
		return Base64.encodeBase64String(zipData);
	}

	/**
	 * 二进制文件写入文件
	 * 
	 * @param data
	 *            二进制数据
	 * @param fileName
	 *            文件名
	 * @param path
	 *            路径地址
	 */
	public static void writeFile(byte[] data, String fileName, String path) {
		try {
			String url = path + "//" + fileName;
			FileUtils.writeByteArrayToFile(new File(url), data);
		} catch (IOException e) {
			System.out.println("写文件出错:" + e);
		}
	}

	/**
	 * 把经过压缩过的base64串解码解压并写入打磁盘中
	 * 
	 * @param base64
	 *            压缩过的base64串
	 * @param fileName
	 *            文件名
	 * @param path
	 *            路径地址
	 */
	public static void decode(String base64, String fileName, String path) {
		// 解码
		byte[] data = Base64.decodeBase64(base64);
		data = RarUtils.unGZip(data);
		writeFile(data, fileName, path);
	}
}
