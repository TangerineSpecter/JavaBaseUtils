package com.tangerinespecter.javabaseutils.common.util;

import com.tangerinespecter.javabaseutils.common.annotation.ClassInfo;
import com.tangerinespecter.javabaseutils.common.annotation.MethodInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author TangerineSpecter
 */
@ClassInfo(Name = "正则表达式工具类")
public class RegExUtils {

    /**
     * 校验邮箱合法化
     *
     * @param email 邮箱地址
     */
    @MethodInfo(Name = "校验邮箱合法化", paramInfo = {"邮箱地址"}, returnInfo = "校验结果")
    public static boolean checkEmail(String email) {
        String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 校验数字为小数后两位以内
     *
     * @param number 校验数字
     */
    @MethodInfo(Name = "校验数字为小数后两位以内", paramInfo = {"校验数字"}, returnInfo = "校验结果")
    public static boolean check2Point(String number) {
        String regEx = "^[0-9]+(.[0-9]{2})?$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * 校验密码以字母开头（长度6~18，只能包含数字、字母、下划线）
     *
     * @param password 密码
     */
    @MethodInfo(Name = "校验密码以字母开头", paramInfo = {"密码"}, returnInfo = "校验结果")
    public static boolean checkPassword(String password) {
        String regEx = "^[a-zA-Z]\\w{5,17}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * 移除特殊字符(只保留汉字字母数字)
     *
     * @param str
     * @return
     */
    @MethodInfo(Name = "移除特殊字符", paramInfo = {"字符串内容"}, returnInfo = "处理结果")
    public static String removeSpecialCharacter(String str) {
        return str.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]", "");
    }

    /**
     * 去除富文本中的与html相关的字符
     *
     * @param htmlString
     * @return
     */
    @MethodInfo(Name = "去除富文本中html相关字符", paramInfo = {"富文本内容"}, returnInfo = "处理结果")
    public static String filterHtml(String htmlString) {
        return htmlString.replaceAll("&.*?;", "").replaceAll("<.*?>", "").replaceAll("<.*?/>", "");
    }
}
