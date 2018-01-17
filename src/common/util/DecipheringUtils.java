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
	 * 栅栏密码解密
	 * 
	 * @param railfence
	 *            栅栏密码
	 * @param number
	 *            栅栏数
	 * @return
	 */
	public static String getRailFenceResult(String railfence, Integer number) {
		String result = Constant.NULL_KEY_STR;
		String[] codes = new String[number];
		String code = railfence.replaceAll("\\s*", "");// 剔除所有空格
		Integer length = code.length();
		Integer cutPoint = length % number == 0 ? (length / number) : (length / number) + 1;
		for (int index = 0; index < number; index++) {
			code.substring(index, index + 1);
			Integer max = ((index + 1) * cutPoint) > length ? length : ((index + 1) * cutPoint);
			codes[index] = code.substring(index * cutPoint, max);
		}
		System.out.println(Arrays.toString(codes));
		for (int index = 0; index < codes[0].length(); index++) {
			for (int count = 0; count < codes.length; count++) {
				if ((index + 1) <= codes[count].length()) {
					result += codes[count].substring(index, index + 1);
				}
			}
		}
		System.out.println(cutPoint);
		return result;
	}
}
