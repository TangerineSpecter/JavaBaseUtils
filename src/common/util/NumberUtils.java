package common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字处理工具类
 * 
 * @author TangerineSpecter
 *
 */
public class NumberUtils {

	private static List<List<Integer>> lists = new ArrayList<>();

	/**
	 * 从Array中拿出n个元素进行全排列
	 * 
	 * @param number
	 *            总的数字数组
	 * @param n
	 *            需要取出进行排列的元素个数
	 * @return
	 */
	public static List<List<Integer>> getFullPermutation(int[] number, int n) {
		if (n <= 0 || number == null) {
			return null;
		}
		List<Integer> intList = new ArrayList<>();
		// 通过这一步初始化序列的长度
		for (int i = 0; i < n; i++) {
			intList.add(-1);
		}
		listAll(intList, number, n);
		return lists;
	}

	/**
	 * 从m个元素中任取n个并对结果进行全排列
	 * 
	 * @param list
	 *            用于承载可能的排列情况的List
	 * @param number
	 *            总的字符数组，长度为m
	 * @param n
	 *            从中取得字符个数
	 */
	public static void listAll(List<Integer> list, int[] number, int n) {
		if (n == 0) {
			List<Integer> li = new ArrayList<>();
			li.addAll(list);
			lists.add(li);// 添加一种可能的排列
			return;
		}
		for (int num : number) {
			if (!list.contains(num)) { // 若List中不包含这一位元素
				list.set(list.size() - n, num); // 将当前元素加入
			} else { // 否则跳到下一位
				continue;
			}
			listAll(list, number, n - 1); // 下一位
			list.set(list.size() - n, -1); // 还原
		}
	}

	/**
	 * 从Array中拿出n个元素进行全排列
	 * 
	 * @param chars
	 *            总的字符数组
	 * @param n
	 *            需要取出进行排列的元素个数
	 * @return
	 */
	public static void getFullPermutation(char[] chars, int n) {
		if (n <= 0 || chars == null) {
			return;
		}
		List<Character> charList = new ArrayList<>();
		// 通过这一步初始化序列的长度
		for (int i = 0; i < n; i++) {
			charList.add('#');
		}
		listAll(charList, chars, n);
	}

	/**
	 * 从m个元素中任取n个并对结果进行全排列
	 * 
	 * @param list
	 *            用于承载可能的排列情况的List
	 * @param chars
	 *            总的字符数组，长度为m
	 * @param n
	 *            从中取得字符个数
	 */
	public static void listAll(List<Character> list, char[] chars, int n) {
		if (n == 0) {
			System.out.println(list);
			return;// 输出一种可能的排列
		}
		for (char c : chars) {
			if (!list.contains(c)) { // 若List中不包含这一位元素
				list.set(list.size() - n, c); // 将当前元素加入
			} else { // 否则跳到下一位
				continue;
			}
			listAll(list, chars, n - 1); // 下一位
			list.set(list.size() - n, '#'); // 还原
		}
		System.out.println(lists);
	}
}
