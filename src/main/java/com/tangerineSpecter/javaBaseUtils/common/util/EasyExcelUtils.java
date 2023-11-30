package com.tangerinespecter.javabaseutils.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.tangerinespecter.javabaseutils.common.test.pojo.ExcelTestHead;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyExcel处理工具类
 *
 * @author TangerineSpecter
 */
public class EasyExcelUtils {

	/**
	 * 简单读取
	 *
	 * @param file excel文件
	 */
	public static void simpleRead(MultipartFile file) throws IOException {
		ExcelReaderBuilder read = EasyExcel.read(file.getInputStream());
		List<List<String>> list = new ArrayList<>();
		read.head(list);
		read.doReadAll();
		for (List<String> strings : list) {
			System.out.println(strings);
		}
	}

	/**
	 * 将列表以 Excel 响应给前端
	 *
	 * @param response  响应
	 * @param filename  文件名
	 * @param sheetName Excel sheet 名
	 * @param head      Excel head 头
	 * @param data      数据列表哦
	 * @param <T>       泛型，保证 head 和 data 类型的一致性
	 * @throws IOException 写入失败的情况
	 */
	public static <T> void write(HttpServletResponse response, String filename, String sheetName,
								 Class<T> head, List<T> data) throws IOException {
		// 输出 Excel
		EasyExcel.write(response.getOutputStream(), head)
				// 不要自动关闭，交给 Servlet 自己处理
				.autoCloseStream(false)
				// 基于 column 长度，自动适配。最大 255 宽度
				.registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
				.sheet(sheetName).doWrite(data);
		// 设置 header 和 contentType。写在最后的原因是，避免报错时，响应 contentType 已经被修改了
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	}

	public static <T> List<T> read(MultipartFile file, Class<T> head) throws IOException {
		return EasyExcel.read(file.getInputStream(), head, null)
				// 不要自动关闭，交给 Servlet 自己处理
				.autoCloseStream(false)
				.doReadAllSync();
	}

	public static <T> List<T> read(InputStream inputStream, Class<T> head) throws IOException {
		return EasyExcel.read(inputStream, head, null)
				// 不要自动关闭，交给 Servlet 自己处理
				.autoCloseStream(false)
				.doReadAllSync();
	}

	public static void main(String[] args) throws IOException {
		String filePath = "/Users/zhouliangjun/Downloads/生产环境机构及员工信息-0729.xlsx";
		File file = new File(filePath);
		if (file.isFile()) {
			System.out.println("这是一个文件");
		}
		List<ExcelTestHead> read = read(new FileInputStream(file), ExcelTestHead.class);
		System.out.println(read);
//        ExcelReaderBuilder read = EasyExcel.read(file);
//        ExcelReaderSheetBuilder sheet = read.sheet();
//        List<Object> objects = sheet.doReadSync();
//        System.out.println(objects);
//		System.out.println(BackgroundRemoval.hexToRgb("ffcc99"));
//		System.out.println(String.format("#%02x%02x%02x", 15, 204, 153));
	}
}
