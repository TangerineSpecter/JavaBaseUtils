package common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 解密工具基础类
 * 
 * @author TangerineSpecter
 *
 */
public class DecipheringBaseUtils {

	/** 控制台输出信息开关 */
	protected static Boolean ERROR_INFO = false;
	/** 密码索引列表 */
	protected static final int[] PASSWORD_INDEX = { 0, 1, 2, 3, 4, 5 };
	/** 摩斯密码 */
	protected static Map<String, Object> morseCodeMap = new HashMap<>();
	/** 键盘密码 */
	protected static Map<String, Object> keyboardCodeMap = new HashMap<>();
	/** 培根密码 */
	protected static Map<String, Object> baconCodeMap = new HashMap<>();
	/** 摩斯key */
	protected static final String[] morse_key = ".----,..---,...--,....-,.....,-....,--...,---..,----.,-----,.-,-...,-.-.,-..,.,..-.,--.,....,..,.---,-.-,.-..,--,-.,---,.--.,--.-,.-.,...,-,..-,...-,.--,-..-,-.--,--.."
			.split(",");
	/** 摩斯value */
	protected static final String[] morse_value = "1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"
			.split(",");

	/** 手机九宫格 */
	protected static final String[][] typewriting_box = { { " " }, { "，", "。", "！", "?" }, { "A", "B", "C" },
			{ "D", "E", "F" }, { "G", "H", "I" }, { "J", "K", "L" }, { "M", "N", "O" }, { "P", "Q", "R", "S" },
			{ "T", "U", "V" }, { "W", "X", "Y", "Z" } };

	/** 字母表 */
	protected static final String[] alphabet = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");

	/** 键盘字母表 */
	protected static final String[] keyboard_value = "Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Z,X,C,V,B,N,M".split(",");

	/** 培根密码表 */
	protected static final String[] bacon_value = "aaaaa,aaaab,aaaba,aaabb,aabaa,aabab,aabba,aabbb,abaaa,abaab,ababa,ababb,abbaa,abbab,abbba,abbbb,baaaa,baaab,baaba,baabb,babaa,babab,babba,babbb,bbaaa,bbaab"
			.split(",");

	static {
		morseCodeMap = getMorseCode();
		keyboardCodeMap = getKeyboardCode();
		baconCodeMap = getBaconCode();
	}

	/**
	 * 获取摩斯密码
	 * 
	 * @return
	 */
	private static Map<String, Object> getMorseCode() {
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
	 * 获取键盘密码
	 * 
	 * @return
	 */
	private static Map<String, Object> getKeyboardCode() {
		Map<String, Object> keyboardMap = new HashMap<>();
		if (alphabet.length == keyboard_value.length) {
			for (int index = 0; index < alphabet.length; index++) {
				keyboardMap.put(alphabet[index], keyboard_value[index]);
			}
		} else {
			System.out.println("键盘密码key,value数量不对等!");
		}
		return keyboardMap;
	}

	/**
	 * 获取培根密码
	 * 
	 * @return
	 */
	private static Map<String, Object> getBaconCode() {
		Map<String, Object> baconMap = new HashMap<>();
		if (alphabet.length == bacon_value.length) {
			for (int index = 0; index < alphabet.length; index++) {
				baconMap.put(alphabet[index], bacon_value[index]);
			}
		} else {
			System.out.println("培根密码key,value数量不对等!");
		}
		return baconMap;
	}
}
