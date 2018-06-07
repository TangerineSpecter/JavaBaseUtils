package common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 解密工具基础类
 * 
 * @author TangerineSpecter
 *
 */
public class DecipheringBaseUtils {

	private static Logger logger = Logger.getLogger(DecipheringBaseUtils.class);

	/** 日志输出信息开关 */
	protected static Boolean ERROR_INFO = false;
	/** 密码索引列表 */
	protected static final int[] PASSWORD_INDEX = { 0, 1, 2, 3, 4, 5 };
	/** 摩斯密码 */
	protected static Map<String, Object> MORSE_CODE_MAP = new HashMap<>();
	/** 键盘密码 */
	protected static Map<String, Object> KEYBOARD_CODE_MAP = new HashMap<>();
	/** 培根密码 */
	protected static Map<String, Object> BACON_CODE_MAP = new HashMap<>();
	/** 摩斯key */
	protected static final String[] MORSE_KEY = ".----,..---,...--,....-,.....,-....,--...,---..,----.,-----,.-,-...,-.-.,-..,.,..-.,--.,....,..,.---,-.-,.-..,--,-.,---,.--.,--.-,.-.,...,-,..-,...-,.--,-..-,-.--,--.."
			.split(",");
	/** 摩斯value */
	protected static final String[] MORSE_VALUE = "1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"
			.split(",");

	/** 手机九宫格 */
	protected static final String[][] TYPEWRITING_BOX = { { " " }, { "，", "。", "！", "?" }, { "A", "B", "C" },
			{ "D", "E", "F" }, { "G", "H", "I" }, { "J", "K", "L" }, { "M", "N", "O" }, { "P", "Q", "R", "S" },
			{ "T", "U", "V" }, { "W", "X", "Y", "Z" } };

	/** 字母表 */
	protected static final String[] ALPHABET = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");

	/** 键盘字母表 */
	protected static final String[] KEYBOARD_VALUE = "Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Z,X,C,V,B,N,M".split(",");

	/** 培根密码表 */
	protected static final String[] BACON_VALUE = "aaaaa,aaaab,aaaba,aaabb,aabaa,aabab,aabba,aabbb,abaaa,abaab,ababa,ababb,abbaa,abbab,abbba,abbbb,baaaa,baaab,baaba,baabb,babaa,babab,babba,babbb,bbaaa,bbaab"
			.split(",");

	static {
		MORSE_CODE_MAP = getMorseCode();
		KEYBOARD_CODE_MAP = getKeyboardCode();
		BACON_CODE_MAP = getBaconCode();
	}

	/**
	 * 获取摩斯密码
	 * 
	 * @return
	 */
	private static Map<String, Object> getMorseCode() {
		Map<String, Object> morseMap = new HashMap<>();
		if (MORSE_KEY.length == MORSE_VALUE.length) {
			for (int index = 0; index < MORSE_KEY.length; index++) {
				morseMap.put(MORSE_KEY[index], MORSE_VALUE[index]);
			}
		} else {
			logger.warn("摩斯密码key,value数量不对等!");
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
		if (ALPHABET.length == KEYBOARD_VALUE.length) {
			for (int index = 0; index < ALPHABET.length; index++) {
				keyboardMap.put(ALPHABET[index], KEYBOARD_VALUE[index]);
			}
		} else {
			logger.warn("键盘密码key,value数量不对等!");
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
		if (ALPHABET.length == BACON_VALUE.length) {
			for (int index = 0; index < ALPHABET.length; index++) {
				baconMap.put(ALPHABET[index], BACON_VALUE[index]);
			}
		} else {
			logger.warn("培根密码key,value数量不对等!");
		}
		return baconMap;
	}
}
