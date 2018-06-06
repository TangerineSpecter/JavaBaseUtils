package common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * 加密工具类
 * 
 * @author TangerineSpecter
 *
 */
public class EncrypUtils {

	private static Logger logger = Logger.getLogger(EncrypUtils.class);
	/**
	 * 
	 */
	private static Map<String, MessageDigest> digests = new ConcurrentHashMap<String, MessageDigest>();

	/**
	 * 哈希加密算法
	 * 
	 * @param data
	 *            需要加密的数据
	 * @param algorithm
	 *            请求算法的名称，如：MD5、SHA等
	 * @return 加密后的数据
	 */
	public static String hash(String data, String algorithm) {
		try {
			return hash(data.getBytes("UTF-8"), algorithm);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return data;
	}

	/**
	 * 哈希加密算法
	 * 
	 * @param data
	 *            需要加密的字节数组
	 * @param algorithm
	 *            请求算法的名称，如：MD5、SHA等
	 * @return 加密后的数据
	 */
	public static String hash(byte[] bytes, String algorithm) {
		synchronized (algorithm.intern()) {
			MessageDigest digest = digests.get(algorithm);
			if (digest == null) {
				try {
					digest = MessageDigest.getInstance(algorithm);
					digests.put(algorithm, digest);
				} catch (NoSuchAlgorithmException nsae) {
					logger.error("加载MessageDigest类失败：" + algorithm, nsae);
					return null;
				}
			}
			// 计算哈希值
			digest.update(bytes);
			return encodeHex(digest.digest());
		}
	}

	/**
	 * 将字节数组转换成十六进制字符串
	 * 
	 * @param bytes
	 *            字节数组
	 * @return 十六进制字符串
	 */
	public static String encodeHex(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
}
