package com.tangerineSpecter.javaBaseUtils.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
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
			PageSize pageSize = PageSize.A4;
			PdfPage page = pdf.addNewPage(pageSize);
			PdfCanvas pdfCanvas = new PdfCanvas(page);
			Document document = new Document(pdf);
			document.add(new Paragraph("Hello World!"));
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@MethodInfo(Name = "创建PDF", paramInfo = { "生成路径", "文本内容" })
	public static void createPdf(String path, List<String> text) {
		try {
			File file = new File(path);
			PdfWriter writer = new PdfWriter(file);
			PdfDocument pdf = new PdfDocument(writer);
			PageSize pageSize = PageSize.A4;
			PdfPage page = pdf.addNewPage(pageSize);
			PdfCanvas canvas = new PdfCanvas(page);
			canvas.concatMatrix(1, 0, 0, 1, 0, pageSize.getHeight());
			canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER_BOLD), 14)
					.setLeading(14 * 1.2f).moveText(70, -40);
			for (String s : text) {
				canvas.newlineShowText(s);
			}
			canvas.endText();
			Document document = new Document(pdf);
			document.add(new Paragraph("Hello World!"));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
