package common.util;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * 甜点
 * 
 * @author TangerineSpecter
 *
 */
public class LollipopUtils extends DecipheringUtils {

	private static Logger logger = Logger.getLogger(LollipopUtils.class);

	/**
	 * * 暴力破解密码
	 * 
	 * @param content
	 *            破解内容
	 * @param plies
	 *            破解层数
	 * @param isInfo
	 *            错误信息输出
	 */
	public static void rceAttack(String content, int plies, boolean isInfo) {
		String result = Constant.NULL_KEY_STR;
		ERROR_INFO = isInfo;
		// 初始化破解列表
		List<List<Integer>> lists = NumberUtils.getFullPermutation(PASSWORD_INDEX, plies);
		if (lists.isEmpty()) {
			if (ERROR_INFO) {
				logger.info("【暴戾破解密码失败】");
			}
		}
		for (List<Integer> list : lists) {
			result = content;
			for (Integer index : list) {
				switch (index) {
				case Constant.Deciphering.INDEX_MORSE:
					result = getMorseResult(result);
					break;
				case Constant.Deciphering.INDEX_RAILFENCE:
					result = getRailFenceResult(result, 2);
					break;
				case Constant.Deciphering.INDEX_PHONE_TYPEWRITING:
					result = getPhoneTypewritingResult(result);
					break;
				case Constant.Deciphering.INDEX_KEYBOARD_TYPE:
					result = getKeyboardResult(result);
					break;
				case Constant.Deciphering.INDEX_BACON:
					result = getBaconResult(result);
					break;
				case Constant.Deciphering.INDEX_REVERSE_ORDER:
					result = reverseOrder(result);
					break;
				default:
					break;
				}
				if (StringUtils.isEmpty(result)) {
					break;
				}
			}
			if (!StringUtils.isEmpty(result)) {
				logger.info(getProcessInfo(list) + result.toLowerCase());
			}
		}
	}

	/**
	 * 获取解密流程信息
	 * 
	 * @param numbers
	 *            解密流程
	 * @return
	 */
	private static String getProcessInfo(List<Integer> numbers) {
		String info = Constant.NULL_KEY_STR;
		for (int number : numbers) {
			switch (number) {
			case 0:
				info += Constant.Deciphering.MORSE_TYPE + "->";
				break;
			case 1:
				info += Constant.Deciphering.RAILFENCE_TYPE + "->";
				break;
			case 2:
				info += Constant.Deciphering.PHONE_TYPEWRITING_TYPE + "->";
				break;
			case 3:
				info += Constant.Deciphering.KEYBOARD_TYPE + "->";
				break;
			case 4:
				info += Constant.Deciphering.BACON_TYPE + "->";
				break;
			case 5:
				info += Constant.Deciphering.REVERSE_ORDER_TYPE + "->";
				break;
			default:
				break;
			}
		}
		return info;
	}
}
