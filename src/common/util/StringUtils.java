package common.util;

import java.util.Random;

/**
 * 字符串处理工具类
 */
public class StringUtils {

	private static Random randGen = new Random();

	/**
	 * 数字和字母的混合数组， 两份数字提高数字出现几率
	 */
	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
			+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

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
	
}
