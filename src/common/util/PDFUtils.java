package common.util;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import common.annotation.ClassInfo;
import common.annotation.MethodInfo;

/**
 * PDF工具类
 * 
 * @author PDF工具类 
 * @doc http://itextpdf.com/
 */
@ClassInfo(Name = "PDF工具类")
public class PDFUtils {

	@MethodInfo(Name = "创建PDF", paramInfo = { "无" })
	public static void createPdf() {
		try {
			File file = new File("F://world.pdf");
			PdfWriter writer = new PdfWriter(file);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf);
			document.add(new Paragraph("Hello World!"));
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
