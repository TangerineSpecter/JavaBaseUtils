package com.tangerinespecter.javabaseutils.common.util;

import com.tangerineSpecter.javaBaseUtils.common.annotation.ClassInfo;
import com.tangerinespecter.javabaseutils.common.annotation.MethodInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * 分词工具类
 *
 * @author TangerineSpecter
 */
@Slf4j
@ClassInfo(Name = "分词工具类")
public class IKTokenizerTool {

    /**
     * 切分分词
     *
     * @param keyword
     * @param bol     true:开启智能切分
     * @return
     */
    @MethodInfo(Name = "切分分词", paramInfo = {"关键词", "智能切分"}, returnInfo = "分词结果")
    public static String tokenizeKeyWord(String keyword, boolean bol) {
//        StringBuilder sb = new StringBuilder();
//        IKTokenizer tokenizer = new IKTokenizer(new StringReader(keyword), bol);
//        try {
//            while (tokenizer.incrementToken()) {
//                TermAttribute termAtt = tokenizer.getAttribute(TermAttribute.class);
//                sb.append("|").append(termAtt.term());
//            }
//            if (sb.length() > 0) {
//                return sb.substring(1).toString();
//            }
//            return keyword;
//        } catch (IOException e) {
//            log.error("切分失败", e);
//            return keyword;
//        } finally {
//            if (tokenizer != null) {
//                try {
//                    tokenizer.close();
//                } catch (IOException e) {
//                    log.error("切分失败", e);
//                }
//            }
//        }
        return null;
    }

    /**
     * 切分分词
     *
     * @param keyword
     * @param bol     true:开启智能切分
     * @return
     */
    @MethodInfo(Name = "切分分词", paramInfo = {"关键词", "智能切分"}, returnInfo = "分词结果")
    public static List<String> tokenizeKeyWordList(String keyword, boolean bol) {
//        List<String> list = new ArrayList<String>();
//        IKTokenizer tokenizer = new IKTokenizer(new StringReader(keyword), bol);
//        try {
//            while (tokenizer.incrementToken()) {
//                TermAttribute termAtt = tokenizer.getAttribute(TermAttribute.class);
//                list.add(termAtt.term());
//            }
//            return list;
//        } catch (IOException e) {
//            log.error("切分失败", e);
//            return list;
//        } finally {
//            if (tokenizer != null) {
//                try {
//                    tokenizer.close();
//                } catch (IOException e) {
//                    log.error("切分失败", e);
//                }
//            }
//        }
        return null;
    }
}
