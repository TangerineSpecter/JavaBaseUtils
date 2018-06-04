package common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * 
 * @author TangerineSpecter
 *
 */
public class RegExUtils {

	/**
	 * 校验邮箱合法化
	 * 
	 * @param email
	 *            邮箱地址
	 */
	public static boolean checkEmail(String email) {
		String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * 校验数字为小数后两位以内
	 * 
	 * @param number
	 *            校验数字
	 */
	public static boolean check2Point(String number) {
		String regEx = "^[0-9]+(.[0-9]{2})?$";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}

	/**
	 * 校验密码以字母开头（长度6~18，只能包含数字、字母、下划线）
	 * 
	 * @param password
	 *            密码
	 */
	public static boolean checkPassword(String password) {
		String regEx = "^[a-zA-Z]\\w{5,17}$";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
