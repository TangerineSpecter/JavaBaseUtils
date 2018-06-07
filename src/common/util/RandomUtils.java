package common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import common.annotation.ClassInfo;
import common.annotation.MethodInfo;

/**
 * 随机工具类
 * 
 * @author TangerineSpecter
 *
 */
@ClassInfo(Name = "随机工具类")
public class RandomUtils {

	private static final String FIRST_NAME = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福";
	private static final String GIRL = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
	private static final String BOY = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
	private static final String[] EMAIL_SUFFIX = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn"
			.split(",");
	private static final String BASE = "abcdefghijklmnopqrstuvwxyz0123456789";
	private static final String[] TEL_FIRST = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153"
			.split(",");
	private static final String[] PROVINCE = "北京,天津,上海,重庆,河北,山西,辽宁,吉林,黑龙江,江苏,浙江,安徽,福建,江西,山东,河南,湖北,湖南,广东,海南,四川,贵州,云南,陕西,甘肃,青海,台湾,内蒙古,广西,西藏,宁夏,新疆,香港,澳门"
			.split(",");
	private static char[][] ENCODE_CHARS_ARRAY = new char[2][];

	static {
		ENCODE_CHARS_ARRAY[0] = new char[] { 'O', 'Y', 'x', 'F', 'd', 'C', 'q', 'X', 's', '5', 'g', 'G', '6', 'l', 'M',
				'W', '9', 'Q', 't', 'a', 'i', 'm', 'B', 'N', 'e', '2', 'D', '4', '3', 'o', 'K', 'H', 'y', 'Z', 'c', 'r',
				'p', 'V', 'v', 'A', 'U', 'R', 'T', 'b', 'I', 'u', 'S', 'n', '1', 'f', 'k', 'E', 'J', '8', 'w', '0', 'z',
				'j', '7', 'L', 'h', 'P' };
		ENCODE_CHARS_ARRAY[1] = new char[] { 'g', 'n', 'y', 'L', 'F', '2', '7', '3', 'I', 'b', 'H', 'Y', 'r', 't', 'A',
				'S', 'v', 'f', 'M', 'a', 'j', '9', 'X', 'k', 'q', 'K', '0', 'u', 'C', 'N', 'Q', 'p', 'i', 'x', 'B', 'w',
				'o', 'G', 'P', 'm', 'E', 'W', 's', 'R', 'c', '5', 'U', 'O', 'h', 'V', '8', '4', 'D', '1', 'z', 'l', 'd',
				'e', 'T', '6', 'Z', 'J' };
	}

	/**
	 * 随机数
	 * 
	 * @param start
	 *            起始数
	 * @param end
	 *            结束数
	 * @return
	 */
	@MethodInfo(Name = "随机数", paramInfo = { "起始数", "结束数" }, returnInfo = "随机数字")
	public static int getNum(int start, int end) {
		return (int) (Math.random() * (end - start + 1) + start);
	}

	/**
	 * 随机生成电话号码
	 * 
	 * @return 电话号码
	 */
	@MethodInfo(Name = "随机生成电话号码", returnInfo = "电话号码")
	public static String getTel() {
		int index = getNum(0, TEL_FIRST.length - 1);
		String first = TEL_FIRST[index];
		String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
		String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
		return first + second + thrid;
	}

	/**
	 * 随机生成中文名字
	 * 
	 * @return 中文名
	 */
	@MethodInfo(Name = "随机生成中文名字", returnInfo = "中文名")
	public static String getChineseName() {
		int index = getNum(0, FIRST_NAME.length() - 1);
		String first = FIRST_NAME.substring(index, index + 1);
		int sex = getNum(0, 1);
		String str = BOY;
		int length = BOY.length();
		if (sex == 0) {
			str = GIRL;
			length = GIRL.length();
		}
		index = getNum(0, length - 1);
		String second = str.substring(index, index + 1);
		int hasThird = getNum(0, 1);
		String third = "";
		if (hasThird == 1) {
			index = getNum(0, length - 1);
			third = str.substring(index, index + 1);
		}
		return first + second + third;
	}

	/**
	 * 随机生成Email
	 * 
	 * @param min
	 *            最小长度
	 * @param max
	 *            最大长度
	 * @return
	 */
	@MethodInfo(Name = "随机生成Email", paramInfo = { "最小长度", "最大长度" }, returnInfo = "Email")
	public static String getEmail(int min, int max) {
		int length = getNum(min, max);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = (int) (Math.random() * BASE.length());
			sb.append(BASE.charAt(number));
		}
		sb.append(EMAIL_SUFFIX[(int) (Math.random() * EMAIL_SUFFIX.length)]);
		return sb.toString();
	}

	/**
	 * 随机生成时间
	 * 
	 * @return 时间
	 */
	@MethodInfo(Name = "随机生成时间", returnInfo = "时间")
	public static String getDate() {
		Random random = new Random();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Integer year = Integer.parseInt(TimeUtils.getCurrentYear());
		cal.set((year - 50), 0, 1);
		long start = cal.getTimeInMillis();
		cal.set(year, 0, 1);
		long end = cal.getTimeInMillis();
		Date d = new Date(start + (long) (random.nextDouble() * (end - start)));
		return format.format(d);
	}

	/**
	 * 随机生成省份
	 * 
	 * @return 省份
	 */
	@MethodInfo(Name = "随机生成省份", returnInfo = "省份")
	public static String getPROVINCE() {
		int index = getNum(0, PROVINCE.length - 1);
		return PROVINCE[index];
	}

	/**
	 * 创建随机字符名字
	 * 
	 * @param num
	 * @return
	 */
	@MethodInfo(Name = "创建随机字符名字", paramInfo = { "名字长度" }, returnInfo = "随机结果")
	public static String createRandomName(long num) {
		int charArrayLength = ENCODE_CHARS_ARRAY.length;
		int encodeCharsLength = ENCODE_CHARS_ARRAY[0].length;

		StringBuffer sb = new StringBuffer();
		int bit = 0;
		while (num > 0) {
			long ch = num % encodeCharsLength;
			sb.insert(0, ENCODE_CHARS_ARRAY[bit % charArrayLength][(char) ch]);
			num = num / encodeCharsLength;
			bit++;
		}
		return sb.toString();
	}
}
