package common.test;

import java.util.ArrayList;
import java.util.List;

import common.DocumentInfo;

public class UtilTest {

	public static void main(String[] args) throws Exception {
		DocumentInfo.createDocInfo();
		String filePath = "F://world.pdf";
		List<String> text = new ArrayList();
		text.add("         Episode 1         ");
		text.add("  Hello World!Welcome to my Codehouse!  ");
		// PDFUtils.createPdf(filePath, text);
		//FileUtil.createFile(text, FileTypeEnum.TXT_FILE);
	}
}
