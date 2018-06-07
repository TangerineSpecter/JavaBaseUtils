package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.annotation.ClassInfo;
import common.annotation.MethodInfo;

/**
 * Excel处理工具类
 * 
 * @author TangerineSpecter
 *
 */
@ClassInfo(Name = "Excel处理工具类")
public class ExcelUtils {

	private static Logger logger = Logger.getLogger(ExcelUtils.class);

	private static Workbook wb = null;
	private static File file;
	private static FileOutputStream fos;

	/**
	 * 获取Excel数据
	 * 
	 * @param filePath
	 * @return
	 */
	@MethodInfo(Name = "获取Excel数据", paramInfo = { "Excel路径" }, returnInfo = "数据列表")
	public static List<String[]> getExcel(String filePath) {
		return getExcelForXlsx(filePath);
	}

	private static List<String[]> getExcelForXlsx(String filePath) {
		try {
			List<String[]> dataList = new ArrayList<>();
			FileInputStream fis = new FileInputStream(filePath);
			// 根据Excel输入流获取工作簿对象
			XSSFWorkbook xss = new XSSFWorkbook(fis);
			// 获取工作表对象
			XSSFSheet sheet = xss.getSheetAt(0);
			Row headerRow = sheet.getRow(0);
			// 获取最后一列列号
			int cellNum = headerRow.getLastCellNum();
			// 获取最后一行行号
			int rowNum = sheet.getLastRowNum();
			for (int count = 1; count < rowNum; count++) {
				String[] data = new String[cellNum];
				// 获取行
				XSSFRow row = sheet.getRow(count);
				// 获取列数据
				for (int index = 0; index < cellNum; index++) {
					XSSFCell cell = row.getCell(index);
					data[index] = cell.toString();
				}
				dataList.add(data);
			}
			xss.close();
			return dataList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(String.format("【Excel数据读取异常】：%s", e));
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
	@MethodInfo(Name = "创建Excel", paramInfo = { "表头", "数据列表", "新旧版本" }, returnInfo = "生成路径")
	public static String createExcel(String[] tableHead, List<String[]> dataList, boolean isExcel) {
		String dateName = TimeUtils.getSimpleFormat("yyyy-MM-dd");
		String uuid = UUID.randomUUID().toString();
		String savePath = Constant.FILE_SAVE_PATH + dateName + "/" + uuid + "/";
		String excelPath = Constant.NULL_KEY_STR;
		if (dataList.isEmpty()) {
			return null;
		}
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
		} catch (Exception e) {
			logger.error(String.format("【excel生成异常】：%s", e));
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("【流关闭失败】：%s", e);
				}
			}
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
