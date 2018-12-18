package common;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import common.annotation.ClassInfo;
import common.annotation.MethodInfo;
import common.util.Constant;
import common.util.FileUtil;

/**
 * 文档信息生成
 * 
 * @author TangerineSpecter
 *
 */
public class DocumentInfo {

	private static Logger logger = Logger.getLogger(DocumentInfo.class);

	/** 不需要生成文档的集合 */
	private static final Set<String> IGNORE_SET = new HashSet<>();

	/** 文件名集合 */
	private static List<String> allFileName = new ArrayList<>();

	/**
	 * 创建所有类的文档信息
	 * 
	 * @throws Exception
	 */
	public static void createDocInfo() throws Exception {
		allFileName = FileUtil.getAllFileName(Constant.UTIL_ABSOLUTE_PATH, true);
		getDocHead();
		for (String fileName : allFileName) {
			if (!IGNORE_SET.contains(fileName)) {
				createClassInfo(fileName);
			}
		}
	}

	/**
	 * 创建类文档信息
	 * 
	 * @param className
	 *            类名
	 * @throws Exception
	 */
	public static void createClassInfo(String className) throws Exception {
		boolean flag = false;
		List<String> allFilePath = FileUtil.getAllFilePath(Constant.UTIL_ABSOLUTE_PATH, false);
		for (String path : allFilePath) {
			if (path.contains(className)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			Class<?> clazz = Class.forName(Constant.UTIL_QUALIFIED_HEAD + className);
			String clazzAnno = clazz.getAnnotation(ClassInfo.class).Name();
			System.out.println(String.format("## <a id= \"Geting_%s\"></a>%s -> [%s](%s)", className, clazzAnno,
					className, Constant.GIT_HUB_BLOB_URL + className + ".java"));
			Method[] methods = clazz.getMethods();
			getDocUtilHead();
			for (Method method : methods) {
				MethodInfo annotations = method.getAnnotation(MethodInfo.class);
				if (annotations != null) {
					System.out.println(String.format("%s | %s | %s | %s(%s)", method.getName(), annotations.Name(),
							getParamInfo(method, annotations.paramInfo()), method.getReturnType().getSimpleName(),
							annotations.returnInfo()));
				}
			}
			System.out.println("---");
		} else {
			logger.info(String.format("【需要生成文档的类不存在】：%s", className));
		}
	}

	/**
	 * 获取文档目录
	 * 
	 * @throws Exception
	 */
	private static void getDocHead() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		System.out.println("### 版本号：");
		System.out.println("- " + Constant.VERSION + "\r\n");
		System.out.println("### 最后更新时间：");
		System.out.println("> " + sdf.format(cal.getTime()) + "\r\n");
		System.out.println("## <a id=\"Getting_Menu\"></a> 目录 \r\n");
		System.out.println("- [开始](#Getting_Menu)");
		System.out.println("- [API](#Geting_Api)");
		for (String fileName : allFileName) {
			if (!IGNORE_SET.contains(fileName)) {
				Class<?> clazz = Class.forName(Constant.UTIL_QUALIFIED_HEAD + fileName);
				String clazzAnno = clazz.getAnnotation(ClassInfo.class).Name();
				System.out.println(String.format("    - [%s](#Geting_%s)", clazzAnno, fileName));
			}
		}
		System.out.println("---");
	}

	/**
	 * 获取文档类标题
	 */
	private static void getDocUtilHead() {
		System.out.println("方法名     | 说明     | 参数     | 返回结果\r\n------|------|-----|-----");
	}

	/**
	 * 获取参数信息
	 * 
	 * @return
	 */
	private static String getParamInfo(Method method, String[] params) {
		String paramInfo = Constant.NULL_KEY_STR;
		int index = Constant.Number.COMMON_NUMBER_ZERO;
		Class<?>[] paramTypes = method.getParameterTypes();
		if (params[index].equals(Constant.Chinese.NOTHING)) {
			return params[index];
		}
		for (Class<?> param : paramTypes) {
			try {
				paramInfo += param.getSimpleName() + "(" + params[index] + "),";
				index++;
			} catch (Exception e) {
				logger.info(String.format("--------------------------------------------------------\r\n"
						+ "【文档生成信息有误】\r\n Method	: %s \r\n"
						+ "--------------------------------------------------------", method.getName()));
				System.exit(0);
			}
		}
		return paramInfo.substring(0, paramInfo.length() - 1).replace("[]", "\\[]");
	}

	static {
		IGNORE_SET.add("Constant");
		IGNORE_SET.add("AudioUtils");
		IGNORE_SET.add("Base64Utils");
		IGNORE_SET.add("DecipheringBaseUtils");
		IGNORE_SET.add("DecipheringUtils");
		IGNORE_SET.add("LollipopUtils");
	}
}
