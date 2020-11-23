package com.tangerinespecter.javabaseutils.common.util;

import com.tangerinespecter.javabaseutils.common.annotation.ClassInfo;
import com.tangerinespecter.javabaseutils.common.annotation.MethodInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 解密工具类
 *
 * @author TangerineSpecter
 */
@Slf4j
@ClassInfo(Name = "解密工具类")
public class DecipheringUtils extends DecipheringBaseUtils {

    /**
     * 加密摩斯密码
     *
     * @param content 加密内容
     * @return
     */
    @MethodInfo(Name = "加密摩斯密码", paramInfo = {"加密内容"}, returnInfo = "摩斯加密结果")
    public static String setMorseResult(String content) {
        StringBuilder result = new StringBuilder(Constant.NULL_KEY_STR);
        content = content.replaceAll("\\s*", "").toUpperCase();
        for (int index = 0; index < content.length(); index++) {
            String value = content.substring(index, index + 1);
            for (String getKey : MORSE_CODE_MAP.keySet()) {
                if (MORSE_CODE_MAP.get(getKey).equals(value)) {
                    result.append(getKey).append("/");
                }
            }
        }
        return result.substring(0, result.length() - 1);
    }

    /**
     * 解密摩斯密码
     *
     * @param morseCode 摩斯密码 用 "/"进行分隔
     * @return
     */
    @MethodInfo(Name = "解密摩斯密码", paramInfo = {"摩斯密码"}, returnInfo = "摩斯解密结果")
    public static String getMorseResult(String morseCode) {
        StringBuilder result = new StringBuilder(Constant.NULL_KEY_STR);
        String[] morseArray = morseCode.split("/");
        for (String morse : morseArray) {
            if (MORSE_CODE_MAP.get(morse) != null) {
                result.append(MORSE_CODE_MAP.get(morse));
            }
        }
        if (Constant.NULL_KEY_STR.equals(result.toString())) {
            if (ERROR_INFO) {
                log.info(getErrorMessage(morseCode, Constant.Deciphering.MORSE_TYPE));
            }
        }
        return result.toString();
    }

    /**
     * 栅栏密码解密
     *
     * @param railfence 栅栏密码
     * @param key       栅栏数
     * @return
     */
    @MethodInfo(Name = "栅栏密码解密", paramInfo = {"栅栏密码", "栅栏数"}, returnInfo = "栅栏解密结果")
    public static String getRailFenceResult(String railfence, int key) {
        StringBuilder result = new StringBuilder();
        // 剔除所有空格
        String code = railfence.replaceAll("\\s*", "");
        if (StringUtils.isEmpty(code)) {
            return result.toString();
        }
        //最小长度
        int minLength = 2;
        if (key < minLength) {
            return code;
        }
        int length = railfence.length() % key == 0 ? railfence.length() / key : railfence.length() / key + 1;
        List<StringBuilder> results = new ArrayList<>(length);
        for (int index = 0; index < length; index++) {
            results.add(new StringBuilder());
        }
        int index = 0;
        for (char c : code.toCharArray()) {
            results.get(index).append(c);
            if (index == length - 1) {
                index = 0;
                continue;
            }
            index++;
        }
        for (StringBuilder row : results) {
            result.append(row);
        }
        return result.toString();
    }

    /**
     * 栅栏密码加密
     *
     * @param railfence 栅栏密码
     * @param key       栅栏数
     * @return
     */
    @MethodInfo(Name = "栅栏密码加密", paramInfo = {"栅栏密码", "栅栏数"}, returnInfo = "栅栏加密结果")
    public static String setRailFenceResult(String railfence, int key) {
        StringBuilder result = new StringBuilder();
        // 剔除所有空格
        String code = railfence.replaceAll("\\s*", "");
        if (StringUtils.isEmpty(code)) {
            return result.toString();
        }
        List<StringBuilder> results = new ArrayList<>(key);
        //最小长度
        int minLength = 2;
        if (key < minLength) {
            return code;
        }
        for (int index = 0; index < key; index++) {
            results.add(new StringBuilder());
        }
        int index = 0;
        for (char c : code.toCharArray()) {
            results.get(index).append(c);
            if (index == key - 1) {
                index = 0;
                continue;
            }
            index++;
        }
        for (StringBuilder row : results) {
            result.append(row);
        }
        return result.toString();
    }

    /**
     * 字符串转unicode
     *
     * @param str
     * @return
     */
    @MethodInfo(Name = "字符串转unicode", paramInfo = {"字符串"}, returnInfo = "unicode结果")
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
    @MethodInfo(Name = "unicode转字符串", paramInfo = {"unicode字符串"}, returnInfo = "字符串结果")
    public static String unicode2String(String str) {
        int start = 0;
        int end;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = str.indexOf("\\u", start + 2);
            String charStr;
            if (end == -1) {
                charStr = str.substring(start + 2);
            } else {
                charStr = str.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(letter);
            start = end;
        }
        return buffer.toString();
    }

    /**
     * 手机九宫格输入法解密
     *
     * @param content 内容
     * @return
     */
    @MethodInfo(Name = "手机九宫格输入法解密", paramInfo = "解密内容", returnInfo = "解密结果")
    public static String getPhoneTypewritingResult(String content) {
        StringBuilder result = new StringBuilder(Constant.NULL_KEY_STR);
        content = content.replaceAll("\\s*", "");
        boolean isNumber = content.matches("[0-9]+");
        if (isNumber && content.length() % Constant.Number.HALF_NUMBER == 0) {
            try {
                for (int index = 0; index < (content.length() / Constant.Number.HALF_NUMBER); index++) {
                    String key = content.substring(index * 2, (index * 2) + 2);
                    result.append(TYPEWRITING_BOX[Integer
                            .parseInt(key.substring(0, 1))][(Integer.parseInt(key.substring(1, 2)) - 1)]);
                }
            } catch (Exception e) {
                if (ERROR_INFO) {
                    log.info(getErrorMessage(content, Constant.Deciphering.PHONE_TYPEWRITING_TYPE));
                }
                return Constant.NULL_KEY_STR;
            }
        }
        if (ERROR_INFO) {
            log.info(getErrorMessage(content, Constant.Deciphering.PHONE_TYPEWRITING_TYPE));
        }
        return result.toString();
    }

    /**
     * 手机九宫格输入法加密
     *
     * @param content 内容
     * @return
     */
    @MethodInfo(Name = "手机九宫格输入法加密", paramInfo = "解密内容", returnInfo = "加密结果")
    public static String setPhoneTypewritingResult(String content) {
        StringBuilder result = new StringBuilder(Constant.NULL_KEY_STR);
        content = content.replaceAll("\\s*", "").toUpperCase();
        for (int i = 0; i < content.length(); i++) {
            for (int index = 0; index < TYPEWRITING_BOX.length; index++) {
                for (int count = 0; count < TYPEWRITING_BOX[index].length; count++) {
                    if (TYPEWRITING_BOX[index][count].equals(content.substring(i, i + 1))) {
                        result.append(index).append((count + 1));
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * 加密键盘密码
     *
     * @param content 加密内容
     * @return
     */
    @MethodInfo(Name = "加密键盘密码", paramInfo = "加密内容", returnInfo = "加密结果")
    public static String setKeyboardResult(String content) {
        StringBuilder result = new StringBuilder(Constant.NULL_KEY_STR);
        content = content.replaceAll("\\s*", "").toUpperCase();
        String[] keyboardArray = content.split("");
        for (String keyboard : keyboardArray) {
            if (KEYBOARD_CODE_MAP.get(keyboard) != null) {
                result.append(KEYBOARD_CODE_MAP.get(keyboard));
            }
        }
        if (Constant.NULL_KEY_STR.equals(result.toString())) {
            if (ERROR_INFO) {
                log.info(getErrorMessage(content, Constant.Deciphering.KEYBOARD_TYPE));
            }
        }
        return result.toString();
    }

    /**
     * 解密键盘密码
     *
     * @param keyboardCode 键盘密码
     * @return
     */
    @MethodInfo(Name = "解密键盘密码", paramInfo = "键盘密码", returnInfo = "解密结果")
    public static String getKeyboardResult(String keyboardCode) {
        StringBuilder result = new StringBuilder(Constant.NULL_KEY_STR);
        keyboardCode = keyboardCode.replaceAll("\\s*", "").toUpperCase();
        for (int index = 0; index < keyboardCode.length(); index++) {
            String value = keyboardCode.substring(index, index + 1);
            for (String getKey : KEYBOARD_CODE_MAP.keySet()) {
                if (KEYBOARD_CODE_MAP.get(getKey).equals(value)) {
                    result.append(getKey);
                }
            }
        }
        return result.toString();
    }

    /**
     * 加密培根密码
     *
     * @param content 加密内容
     * @return
     */
    @MethodInfo(Name = "加密培根密码", paramInfo = "加密内容", returnInfo = "加密结果")
    public static String setBaconResult(String content) {
        StringBuilder result = new StringBuilder(Constant.NULL_KEY_STR);
        content = content.replaceAll("\\s*", "").toUpperCase();
        String[] baconArray = content.split("");
        for (String bacon : baconArray) {
            if (BACON_CODE_MAP.get(bacon) != null) {
                result.append(BACON_CODE_MAP.get(bacon));
            }
        }
        if (Constant.NULL_KEY_STR.equals(result.toString())) {
            if (ERROR_INFO) {
                log.info(getErrorMessage(content, Constant.Deciphering.BACON_TYPE));
            }
        }
        return result.toString();
    }

    /**
     * 解密培根密码
     *
     * @param baconCode 培根密码
     * @return
     */
    @MethodInfo(Name = "解密培根密码", paramInfo = "培根密码", returnInfo = "解密结果")
    public static String getBaconResult(String baconCode) {
        StringBuilder result = new StringBuilder(Constant.NULL_KEY_STR);
        baconCode = baconCode.replaceAll("\\s*", "").toUpperCase();
        for (int index = 0; index < baconCode.length(); index++) {
            String value = baconCode.substring(index, index + 1);
            for (String getKey : BACON_CODE_MAP.keySet()) {
                if (BACON_CODE_MAP.get(getKey).equals(value)) {
                    result.append(getKey);
                }
            }
        }
        return result.toString();
    }

    /**
     * 倒序排列
     *
     * @param content
     * @return
     */
    @MethodInfo(Name = "倒序排列", paramInfo = {"排列内容"}, returnInfo = "排列结果")
    public static String reverseOrder(String content) {
        return new StringBuffer(content.replaceAll("\\s*", "")).reverse().toString();
    }

    /**
     * 解密错误提示
     */
    private static String getErrorMessage(String content, String type) {
        return String.format("【错误提示】：[%s]不符合[%s]方式", content, type);
    }
}
