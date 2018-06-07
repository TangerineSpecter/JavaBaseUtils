package common.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKTokenizer;

import common.annotation.ClassInfo;
import common.annotation.MethodInfo;

/**
 * 分词工具类
 * 
 * @author TangerineSpecter
 *
 */
@ClassInfo(Name = "分词工具类")
public class IKTokenizerTool {
	private static Logger log = Logger.getLogger(IKTokenizerTool.class);

	/**
	 * 切分分词
	 * 
	 * @param keyword
	 * @param bol
	 *            true:开启智能切分
	 * @return
	 */
	@MethodInfo(Name = "切分分词", paramInfo = { "关键词", "智能切分" }, returnInfo = "分词结果")
	public static String tokenizeKeyWord(String keyword, boolean bol) {
		StringBuilder sb = new StringBuilder();
		IKTokenizer tokenizer = new IKTokenizer(new StringReader(keyword), bol);
		try {
			while (tokenizer.incrementToken()) {
				TermAttribute termAtt = tokenizer.getAttribute(TermAttribute.class);
				sb.append("|").append(termAtt.term());
			}
			if (sb.length() > 0) {
				return sb.substring(1).toString();
			}
			return keyword;
		} catch (IOException e) {
			log.error("切分失败", e);
			return keyword;
		} finally {
			if (tokenizer != null) {
				try {
					tokenizer.close();
				} catch (IOException e) {
					log.error("切分失败", e);
				}
			}
		}

	}

	/**
	 * 切分分词
	 * 
	 * @param keyword
	 * @param bol
	 *            true:开启智能切分
	 * @return
	 */
	@MethodInfo(Name = "切分分词", paramInfo = { "关键词", "智能切分" }, returnInfo = "分词结果")
	public static List<String> tokenizeKeyWordList(String keyword, boolean bol) {
		List<String> list = new ArrayList<String>();
		IKTokenizer tokenizer = new IKTokenizer(new StringReader(keyword), bol);
		try {
			while (tokenizer.incrementToken()) {
				TermAttribute termAtt = tokenizer.getAttribute(TermAttribute.class);
				list.add(termAtt.term());
			}
			return list;
		} catch (IOException e) {
			log.error("切分失败", e);
			return list;
		} finally {
			if (tokenizer != null) {
				try {
					tokenizer.close();
				} catch (IOException e) {
					log.error("切分失败", e);
				}
			}
		}
	}
}
