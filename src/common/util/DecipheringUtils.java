package common.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 解密工具类
 * 
 * @author TangerineSpecter
 *
 */
public class DecipheringUtils {

	/** 摩斯密码 */
	private static Map<String, Object> morseCodeMap = new HashMap<>();
	/** 摩斯key */
	private static final String[] morse_key = ".----,..---,...--,....-,.....,-....,--...,---..,----.,-----,.-,-...,-.-.,-..,.,..-.,--.,....,..,.---,-.-,.-..,--,-.,---,.--.,--.-,.-.,...,-,..-,...-,.--,-..-,-.--,--.."
			.split(",");
	/** 摩斯value */
	private static final String[] morse_value = "1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"
			.split(",");

	static {
		morseCodeMap = getMorseCode();
	}

	/**
	 * 获取摩斯密码
	 * 
	 * @return
	 */
	public static Map<String, Object> getMorseCode() {
		Map<String, Object> morseMap = new HashMap<>();
		if (morse_key.length == morse_value.length) {
			for (int index = 0; index < morse_key.length; index++) {
				morseMap.put(morse_key[index], morse_value[index]);
			}
		} else {
			System.out.println("摩斯密码key,value数量不对等!");
		}
		return morseMap;
	}

	/**
	 * 加密摩斯密码
	 * 
	 * @param content
	 *            加密内容
	 * @return
	 */
	public static String setMorseResult(String content) {
		String result = Constant.NULL_KEY_STR;
		String value = Constant.NULL_KEY_STR;
		content = content.trim();
		for (int index = 0; index < content.length(); index++) {
			value = content.substring(index, index + 1);
			for (String getKey : morseCodeMap.keySet()) {
				if (morseCodeMap.get(getKey).equals(value)) {
					result += getKey + "/";
				}
			}
		}
		return result.substring(0, result.length() - 1);
	}

	/**
	 * 解密摩斯密码
	 * 
	 * @param morseCode
	 *            摩斯密码
	 * @return
	 */
	public static String getMorseResult(String morseCode) {
		String result = Constant.NULL_KEY_STR;
		String[] morseArray = morseCode.split("/");
		for (String morse : morseArray) {
			result += morseCodeMap.get(morse);
		}
		return result;
	}

	/**
	 * 栅栏密码加密
	 * 
	 * @param railfence
	 *            栅栏密码
	 * @param number
	 *            栅栏数
	 * @return
	 */
	public static String setRailFenceResult(String railfence, Integer key) {
		String result = Constant.NULL_KEY_STR;
		String codes = Constant.NULL_KEY_STR;
		String code = railfence.replaceAll("\\s*", "");// 剔除所有空格
		Integer length = code.length();
		Integer cutPoint = length % key == 0 ? (length / key) : (length / key) + 1;
		for (int index = 0; index < cutPoint; index++) {
			Integer max = ((index + 1) * key) < length ? ((index + 1) * key) : length;
			codes += code.substring(index * key, max) + ",";
		}
		String[] codeArrays = codes.substring(0, codes.length() - 1).split(",");
		System.out.println(Arrays.toString(codeArrays));
		for (int index = 0; index < codeArrays[0].length(); index++) {
			for (int count = 0; count < codeArrays.length; count++) {
				if ((index + 1) <= codeArrays[count].length()) {
					result += codeArrays[count].substring(index, index + 1);
				}
			}
		}
		return result;
	}

	/**
	 * 栅栏密码解密
	 * 
	 * @param railfence
	 *            栅栏密码
	 * @param number
	 *            栅栏数
	 * @return
	 */
	public static String getRailFenceResult(String railfence, Integer key) {
		String result = Constant.NULL_KEY_STR;
		String codes = Constant.NULL_KEY_STR;
		String code = railfence.replaceAll("\\s*", "");// 剔除所有空格
		Integer length = code.length();
		Integer cutPoint = length % key == 0 ? (length / key) : (length / key) + 1;
		for (int index = 0; index < key; index++) {
			Integer max = ((index + 1) * cutPoint) < length ? ((index + 1) * cutPoint) : length;
			codes += code.substring(index * cutPoint, max) + ",";
		}
		String[] codeArrays = codes.substring(0, codes.length() - 1).split(",");
		System.out.println(Arrays.toString(codeArrays));
		for (int index = 0; index < codeArrays[0].length(); index++) {
			for (int count = 0; count < codeArrays.length; count++) {
				if ((index + 1) <= codeArrays[count].length()) {
					result += codeArrays[count].substring(index, index + 1);
				}
			}
		}
		return result;
	}

	/**
	 * 字符串转unicode
	 * 
	 * @param str
	 * @return
	 */
	public static String string2Unicode(String str) {
		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			// 取出每一个字符
			char c = str.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	/**
	 * unicode转字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String unicode2String(String str) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = str.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = str.substring(start + 2, str.length());
			} else {
				charStr = str.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16);
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}
}
