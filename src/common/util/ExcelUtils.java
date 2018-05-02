package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel处理工具类
 * 
 * @author TangerineSpecter
 *
 */
public class ExcelUtils {

	private static Workbook wb = null;
	private static File file;
	private static FileOutputStream fos;

	public static List<String> getExcelForXlsx(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			// 根据Excel输入流获取工作簿对象
			XSSFWorkbook xss = new XSSFWorkbook(fis);
			// 获取工作表对象
			XSSFSheet sheet = xss.getSheetAt(0);
			// 获取最后一行行号
			int rowNum = sheet.getLastRowNum() + 1;
			System.out.println(rowNum);
			// 获取行
			XSSFRow row = sheet.getRow(0);
			// 获取列数据
			for (int index = 0; index <= rowNum; index++) {
				XSSFCell cell = row.getCell(index);
				System.out.println(cell);
			}
			xss.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 创建Excel文件
	 * 
	 * @param tableHead
	 *            表头
	 * @param dataList
	 *            数据列表
	 * @param isExcel
	 *            （true:新版；false:旧版）
	 * @return excel生成路径
	 */
	public static String createExcel(String[] tableHead, List<String[]> dataList, boolean isExcel) {
		String savePath = Constant.FILE_SAVE_PATH + TimeUtils.getSimpleFormat("yyyy-MM-dd");
		String excelPath = Constant.NULL_KEY_STR;
		File dir = new File(savePath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		if (isExcel) {
			excelPath = createExcelForXlsx(savePath, tableHead, dataList);
		} else {
			excelPath = createExcelForXls(savePath, tableHead, dataList);
		}
		return excelPath;
	}

	/**
	 * 生成Xlsx格式Excel
	 * 
	 * @param savePath
	 *            保存路径
	 * @param tableHead
	 *            表头
	 * @param dataList
	 *            数据列表
	 */
	private static String createExcelForXlsx(String savePath, String[] tableHead, List<String[]> dataList) {
		String path = savePath + "/" + UUID.randomUUID().toString() + ".xlsx";
		file = new File(path);
		wb = new XSSFWorkbook();
		createExcelData(tableHead, dataList);
		return path;
	}

	/**
	 * 生成Xls格式Excel
	 * 
	 * @param savePath
	 *            保存路径
	 * @param tableHead
	 *            表头
	 * @param dataList
	 *            数据列表
	 */
	private static String createExcelForXls(String savePath, String[] tableHead, List<String[]> dataList) {
		String path = savePath + "/" + UUID.randomUUID().toString() + ".xls";
		file = new File(path);
		wb = new HSSFWorkbook();
		createExcelData(tableHead, dataList);
		return path;
	}

	/**
	 * 生成Excel文件
	 * 
	 * @param tableHead
	 *            表头
	 * @param dataList
	 *            数据列表
	 */
	private static void createExcelData(String[] tableHead, List<String[]> dataList) {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			Sheet sheet = wb.createSheet("errorInfo");
			Row titleRow = sheet.createRow(0);
			for (int index = 0; index < tableHead.length; index++) {
				titleRow.createCell(index).setCellValue(tableHead[index]);
			}
			createExcelData(dataList);
			wb.write(fos);
			wb.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 创建数据
	 * 
	 * @param dataList
	 *            数据列表
	 */
	private static void createExcelData(List<String[]> dataList) {
		for (String[] data : dataList) {
			write(data);
		}
	}

	/**
	 * 写入数据
	 * 
	 * @param data
	 *            数据
	 */
	private static void write(String[] data) {
		Sheet sheet = wb.getSheet("errorInfo");
		int lastRowNum = sheet.getLastRowNum();
		Row currentRow = sheet.createRow(lastRowNum + 1);
		for (int index = 0; index < data.length; index++) {
			currentRow.createCell(index).setCellValue(data[index]);
		}
	}

}
