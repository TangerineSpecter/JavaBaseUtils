package common.util;

/**
 * 解密工具类
 * 
 * @author TangerineSpecter
 *
 */
public class DecipheringUtils extends DecipheringBaseUtils {

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
		content = content.replaceAll("\\s*", "").toUpperCase();
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
	 *            摩斯密码 用 "/"进行分隔
	 * @return
	 */
	public static String getMorseResult(String morseCode) {
		String result = Constant.NULL_KEY_STR;
		String[] morseArray = morseCode.split("/");
		for (String morse : morseArray) {
			if (morseCodeMap.get(morse) != null) {
				result += morseCodeMap.get(morse);
			}
		}
		if (Constant.NULL_KEY_STR.equals(result)) {
			if (ERROR_INFO) {
				System.out.println(getErrorMessage(morseCode, Constant.Deciphering.MORSE_TYPE));
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
		if (StringUtils.isEmpty(code)) {
			return result;
		}
		Integer length = code.length();
		Integer cutPoint = length % key == 0 ? (length / key) : (length / key) + 1;
		for (int index = 0; index < cutPoint; index++) {
			Integer max = ((index + 1) * key) < length ? ((index + 1) * key) : length;
			codes += code.substring(index * key, max) + ",";
		}
		String[] codeArrays = codes.substring(0, codes.length() - 1).split(",");
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
		for (int index = 0; index < key; index++) {
			Integer max = ((index + 1) * cutPoint) < length ? ((index + 1) * cutPoint) : length;
			codes += code.substring(index * cutPoint, max) + ",";
		}
		String[] codeArrays = codes.substring(0, codes.length() - 1).split(",");
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

	/**
	 * 手机九宫格输入法解密
	 * 
	 * @param content
	 *            内容
	 * @return
	 */
	public static String getPhoneTypewritingResult(String content) {
		String result = Constant.NULL_KEY_STR;
		content = content.replaceAll("\\s*", "");
		boolean isNumber = content.matches("[0-9]+");
		String key = Constant.NULL_KEY_STR;
		if (isNumber && content.length() % 2 == 0) {
			try {
				for (int index = 0; index < (content.length() / 2); index++) {
					key = content.substring(index * 2, (index * 2) + 2);
					result += typewriting_box[Integer
							.valueOf(key.substring(0, 1))][(Integer.valueOf(key.substring(1, 2)) - 1)];
				}
			} catch (Exception e) {
				if (ERROR_INFO) {
					System.out.println(getErrorMessage(content, Constant.Deciphering.PHONE_TYPEWRITING_TYPE));
				}
				return Constant.NULL_KEY_STR;
			}
		} else {
			if (ERROR_INFO) {
				System.out.println(getErrorMessage(content, Constant.Deciphering.PHONE_TYPEWRITING_TYPE));
			}
		}
		return result;
	}

	/**
	 * 手机九宫格输入法加密
	 * 
	 * @param content
	 *            内容
	 * @return
	 */
	public static String setPhoneTypewritingResult(String content) {
		String result = Constant.NULL_KEY_STR;
		content = content.replaceAll("\\s*", "").toUpperCase();
		for (int i = 0; i < content.length(); i++) {
			for (int index = 0; index < typewriting_box.length; index++) {
				for (int count = 0; count < typewriting_box[index].length; count++) {
					if (typewriting_box[index][count].equals(content.substring(i, i + 1))) {
						result += String.valueOf(index) + String.valueOf(count + 1);
					}
				}
			}
		}
		return result;
	}

	/**
	 * 加密键盘密码
	 * 
	 * @param content
	 *            加密内容
	 * @return
	 */
	public static String setKeyboardResult(String content) {
		String result = Constant.NULL_KEY_STR;
		content = content.replaceAll("\\s*", "").toUpperCase();
		String[] keyboardArray = content.split("");
		for (String keyboard : keyboardArray) {
			if (keyboardCodeMap.get(keyboard) != null) {
				result += keyboardCodeMap.get(keyboard);
			}
		}
		if (Constant.NULL_KEY_STR.equals(result)) {
			if (ERROR_INFO) {
				System.out.println(getErrorMessage(content, Constant.Deciphering.KEYBOARD_TYPE));
			}
		}
		return result;
	}

	/**
	 * 解密键盘密码
	 * 
	 * @param keyboardCode
	 *            键盘密码
	 * @return
	 */
	public static String getKeyboardResult(String keyboardCode) {
		String result = Constant.NULL_KEY_STR;
		String value = Constant.NULL_KEY_STR;
		keyboardCode = keyboardCode.replaceAll("\\s*", "").toUpperCase();
		for (int index = 0; index < keyboardCode.length(); index++) {
			value = keyboardCode.substring(index, index + 1);
			for (String getKey : keyboardCodeMap.keySet()) {
				if (keyboardCodeMap.get(getKey).equals(value)) {
					result += getKey;
				}
			}
		}
		return result;
	}

	/**
	 * 加密培根密码
	 * 
	 * @param content
	 *            加密内容
	 * @return
	 */
	public static String setBaconResult(String content) {
		String result = Constant.NULL_KEY_STR;
		content = content.replaceAll("\\s*", "").toUpperCase();
		String[] baconArray = content.split("");
		for (String bacon : baconArray) {
			if (baconCodeMap.get(bacon) != null) {
				result += baconCodeMap.get(bacon);
			}
		}
		if (Constant.NULL_KEY_STR.equals(result)) {
			if (ERROR_INFO) {
				System.out.println(getErrorMessage(content, Constant.Deciphering.BACON_TYPE));
			}
		}
		return result;
	}

	/**
	 * 解密培根密码
	 * 
	 * @param baconCode
	 *            培根密码
	 * @return
	 */
	public static String getBaconResult(String baconCode) {
		String result = Constant.NULL_KEY_STR;
		String value = Constant.NULL_KEY_STR;
		baconCode = baconCode.replaceAll("\\s*", "").toUpperCase();
		for (int index = 0; index < baconCode.length(); index++) {
			value = baconCode.substring(index, index + 1);
			for (String getKey : baconCodeMap.keySet()) {
				if (baconCodeMap.get(getKey).equals(value)) {
					result += getKey;
				}
			}
		}
		return result;
	}

	/**
	 * 倒序排列
	 * 
	 * @param content
	 * @return
	 */
	public static String reverseOrder(String content) {
		return new StringBuffer(content.replaceAll("\\s*", "")).reverse().toString();
	}

	/**
	 * 解密错误提示
	 */
	private static String getErrorMessage(String content, String type) {
		String message = String.format("【错误提示】：[%s]不符合[%s]方式", content, type);
		return message;
	}
}
