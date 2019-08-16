package com.tangerinespecter.javabaseutils.common;

import com.tangerineSpecter.javaBaseUtils.common.annotation.ClassInfo;
import com.tangerineSpecter.javaBaseUtils.common.annotation.MethodInfo;
import com.tangerinespecter.javabaseutils.common.util.Constant;
import com.tangerinespecter.javabaseutils.common.util.FileTypeEnum;
import com.tangerinespecter.javabaseutils.common.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文档信息生成
 *
 * @author TangerineSpecter
 */
@Slf4j
public class DocumentInfo {

    /**
     * 不需要生成文档的集合
     */
    private static final Set<String> IGNORE_SET = new HashSet<>();

    /**
     * 文件名集合
     */
    private static List<String> allFileName = new ArrayList<>();

    /**
     * 创建所有类的文档信息
     *
     * @throws Exception
     */
    public static void createDocInfo() throws Exception {
        List<String> textInfo = new ArrayList<>();
        allFileName = FileUtil.getAllFileName(Constant.UTIL_ABSOLUTE_PATH, true);
        getDocHead(textInfo);
        for (String fileName : allFileName) {
            if (!IGNORE_SET.contains(fileName)) {
                createClassInfo(fileName, textInfo);
            }
        }
        FileUtil.deleteFile(System.getProperty("user.dir"), "README.md");
        FileUtil.createFile(System.getProperty("user.dir"), "README", textInfo, FileTypeEnum.MARKDOWN_FILE);
    }

    /**
     * 创建类文档信息
     *
     * @param className 类名
     * @throws Exception
     */
    public static void createClassInfo(String className, List<String> textInfo) throws Exception {
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
            textInfo.add(String.format("## <a id= \"Geting_%s\"></a>%s -> [%s](%s)", className, clazzAnno, className,
                    Constant.GIT_HUB_BLOB_URL + className + ".java"));
            Method[] methods = clazz.getMethods();
            getDocUtilHead(textInfo);
            for (Method method : methods) {
                MethodInfo annotations = method.getAnnotation(MethodInfo.class);
                if (annotations != null) {
                    textInfo.add(String.format("%s | %s | %s | %s(%s)", method.getName(), annotations.Name(),
                            getParamInfo(method, annotations.paramInfo()), method.getReturnType().getSimpleName(),
                            annotations.returnInfo()));
                }
            }
            textInfo.add("---");
        } else {
            log.info(String.format("【需要生成文档的类不存在】：%s", className));
        }
    }

    /**
     * 获取文档目录
     *
     * @throws Exception
     */
    private static void getDocHead(List<String> textInfo) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        textInfo.add("# JavaBaseUtils\r\n");
        textInfo.add("## 简介");
        textInfo.add("	主要收集一些平时常用的Java开发工具类，内容在不断更新补充中...");
        textInfo.add("<img src=\"com/tangerineSpecter/javaBaseUtils/common/img/show_logo.gif\">\r\n");
        textInfo.add("### Java基本工具包：");
        textInfo.add("- 工具包地址：https://github.com/TangerineSpecter/JavaBaseUtils\r\n");
        textInfo.add("### 版本号：");
        textInfo.add("- 项目版本：" + Constant.VERSION + "\r\n");
        textInfo.add("- JDK版本：1.8\r\n");
        textInfo.add("### 最后更新时间：");
        textInfo.add("> " + sdf.format(cal.getTime()) + "\r\n");
        textInfo.add("## <a id=\"Getting_Menu\"></a> 目录 \r\n");
        textInfo.add("- [开始](#Getting_Menu)");
        textInfo.add("- [API](#Geting_Api)");
        for (String fileName : allFileName) {
            if (!IGNORE_SET.contains(fileName)) {
                Class<?> clazz = Class.forName(Constant.UTIL_QUALIFIED_HEAD + fileName);
                String clazzAnno = clazz.getAnnotation(ClassInfo.class).Name();
                textInfo.add(String.format("    - [%s](#Geting_%s)", clazzAnno, fileName));
            }
        }
        textInfo.add("---");
    }

    /**
     * 获取文档类标题
     */
    private static void getDocUtilHead(List<String> textInfo) {
        textInfo.add("方法名     | 说明     | 参数     | 返回结果\r\n------|------|-----|-----");
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
                log.info(String.format("--------------------------------------------------------\r\n"
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
        IGNORE_SET.add("FileTypeEnum");
        IGNORE_SET.add("BaseUtils");
        IGNORE_SET.add("logWordPool");
    }
}
