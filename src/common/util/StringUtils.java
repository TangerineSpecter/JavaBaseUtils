package common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 字符串处理工具类
 * 
 * @author TangerineSpecter
 */
public class StringUtils {

	private static Random randGen = new Random();

	/**
	 * 数字和字母的混合数组， 两份数字提高数字出现几率
	 */
	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
			+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

	private static char[][] encodeCharsArray = new char[2][];

	static {
		encodeCharsArray[0] = new char[] { 'O', 'Y', 'x', 'F', 'd', 'C', 'q', 'X', 's', '5', 'g', 'G', '6', 'l', 'M',
				'W', '9', 'Q', 't', 'a', 'i', 'm', 'B', 'N', 'e', '2', 'D', '4', '3', 'o', 'K', 'H', 'y', 'Z', 'c', 'r',
				'p', 'V', 'v', 'A', 'U', 'R', 'T', 'b', 'I', 'u', 'S', 'n', '1', 'f', 'k', 'E', 'J', '8', 'w', '0', 'z',
				'j', '7', 'L', 'h', 'P' };
		encodeCharsArray[1] = new char[] { 'g', 'n', 'y', 'L', 'F', '2', '7', '3', 'I', 'b', 'H', 'Y', 'r', 't', 'A',
				'S', 'v', 'f', 'M', 'a', 'j', '9', 'X', 'k', 'q', 'K', '0', 'u', 'C', 'N', 'Q', 'p', 'i', 'x', 'B', 'w',
				'o', 'G', 'P', 'm', 'E', 'W', 's', 'R', 'c', '5', 'U', 'O', 'h', 'V', '8', '4', 'D', '1', 'z', 'l', 'd',
				'e', 'T', '6', 'Z', 'J' };
	}

	/**
	 * 伪随机字符串（数字英文混合）
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String randomString(int length) {
		if (length < 1) {
			return null;
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * 移除特殊字符(只保留汉字字母数字)
	 * 
	 * @param str
	 * @return
	 */
	public static String removeSpecialCharacter(String str) {
		return str.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]", "");
	}

	/**
	 * 去除富文本中的与html相关的字符
	 * 
	 * @param htmlString
	 * @return
	 */
	public static String filterHtml(String htmlString) {
		return htmlString.replaceAll("&.*?;", "").replaceAll("<.*?>", "").replaceAll("<.*?/>", "");
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @return
	 */
	public static boolean isEmpty(String str) {
		boolean flag = true;
		if (str != null && !str.trim().equals("")) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断多个字符串是否有任意一个为空
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isAnyEmpty(String... strs) {
		for (String str : strs) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 创建随机字符名字
	 * 
	 * @param num
	 * @return
	 */
	public static String createRandomName(long num) {
		int charArrayLength = encodeCharsArray.length;
		int encodeCharsLength = encodeCharsArray[0].length;

		StringBuffer sb = new StringBuffer();
		int bit = 0;
		while (num > 0) {
			long ch = num % encodeCharsLength;
			sb.insert(0, encodeCharsArray[bit % charArrayLength][(char) ch]);
			num = num / encodeCharsLength;
			bit++;
		}
		return sb.toString();
	}

	/**
	 * 截取String开头指定长度的部分
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subString(String str, int length) {
		return str.substring(0, str.length() > length ? length : str.length());
	}

	/**
	 * 获取本机ip地址
	 * 
	 * @return
	 */
	public static String getLocalhostIP() {
		String ip = Constant.NULL_KEY_STR;
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			ip = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

	/**
	 * 生成订单号
	 * 
	 * @return
	 */
	public static String getOrderNum() {
		return DigestUtils.md5Hex(UUID.randomUUID().toString()).toUpperCase();
	}

	/**
	 * 判断是否为数字,是数字返回true否则返回false
	 */
	public static boolean isNumber(String str) {
		boolean flag = false;
		if (str != null && !str.trim().equals("") && str.length() <= 18) {
			String regex = "\\d*";
			flag = str.matches(regex);
		}
		return flag;
	}

	/**
	 * 判断所有字符串都是数字，如果都是返回true, 否则返回false
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isAllNumber(String... strs) {
		for (String str : strs) {
			if (!isNumber(str)) {
				return false;
			}
		}
		return true;
	}

}
