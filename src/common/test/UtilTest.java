package common.test;

import java.util.ArrayList;
import java.util.List;

import common.DocumentInfo;
import common.util.FileTypeEnum;
import common.util.FileUtil;

public class UtilTest {

	public static void main(String[] args) throws Exception {
		DocumentInfo.createDocInfo();
		// String filePath = "F://world.pdf";
		List<String> text = new ArrayList();
		text.add("         Episode 1         ");
		text.add("  Hello World!Welcome to my Codehouse!  ");
		// PDFUtils.createPdf(filePath, text);
		// FileUtil.createFile(text, FileTypeEnum.MARKDOWN_FILE);
		// FileUtil.deleteDirFile(Constant.FILE_SAVE_PATH, true);
		// FileUtil.moveFuzzyFileDir("test", "F://testFile", "F://test", true);
		//String loadingFile = FileUtil.loadingFile("F://testFile/tesw2.md");
		//System.out.println(loadingFile);
		FileUtil.deleteFileSuffix("F://testFile",FileTypeEnum.XLS_EXCEL_FILE);
	}
}
