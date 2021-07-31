package com.tangerinespecter.javabaseutils.common.util;

import com.tangerinespecter.javabaseutils.common.annotation.ClassInfo;
import com.tangerinespecter.javabaseutils.common.annotation.MethodInfo;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;

/**
 * 字符串处理工具类
 *
 * @author TangerineSpecter
 */
@ClassInfo(Name = "字符串处理工具类")
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
     * @param length 字符串长度
     * @return
     */
    @MethodInfo(Name = "伪随机字符串", paramInfo = {"字符串长度"}, returnInfo = "随机结果")
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
     * 判断字符串是否为空
     */
    @MethodInfo(Name = "判断字符串是否为空", paramInfo = {"字符串内容"}, returnInfo = "判断结果")
    public static boolean isEmpty(String str) {
        boolean flag = true;
        if (str != null && !Constant.NULL_KEY_STR.equals(str.trim())) {
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
    @MethodInfo(Name = "判断多个字符串中是否有空值", paramInfo = {"字符串参数集"}, returnInfo = "判断结果")
    public static boolean isAnyEmpty(String... strs) {
        for (String str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 截取String开头指定长度的部分
     *
     * @param str
     * @param length
     * @return
     */
    @MethodInfo(Name = "截取字符串开头指定长度", paramInfo = {"字符串内容", "截取位置"}, returnInfo = "截取结果")
    public static String subString(String str, int length) {
        return str.substring(0, str.length() > length ? length : str.length());
    }

    /**
     * 获取本机ip地址
     *
     * @return
     */
    @MethodInfo(Name = "获取本机IP地址", returnInfo = "IP地址")
    public static String getLocalHostIp() {
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
    @MethodInfo(Name = "订单号生成", returnInfo = "订单号")
    public static String getOrderNum() {
        return DigestUtils.md5Hex(UUID.randomUUID().toString()).toUpperCase();
    }

    /**
     * 判断是否为数字,是数字返回true否则返回false
     */
    @MethodInfo(Name = "判断是否为数字", returnInfo = "判断结果")
    public static boolean isNumber(String str) {
        boolean flag = false;
        if (str != null && !Constant.NULL_KEY_STR.equals(str.trim())) {
            String regex = "\\d*";
            flag = str.matches(regex);
        }
        return flag;
    }

    /**
     * 判断所有字符串都是数字，如果都是返回true, 否则返回false
     *
     * @param strs 字符串集合
     * @return
     */
    @MethodInfo(Name = "判断所有字符串是否都为数字", paramInfo = {"字符串集"}, returnInfo = "判断结果")
    public static boolean isAllNumber(String... strs) {
        for (String str : strs) {
            if (!isNumber(str)) {
                return false;
            }
        }
        return true;
    }

}
