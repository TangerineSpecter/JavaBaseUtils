package common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import common.annotation.ClassInfo;
import common.annotation.MethodInfo;
import common.enums.FileTypeEnum;

/**
 * 文件工具类
 * 
 * @author TangerSpecter
 *
 */
@ClassInfo(Name = "文件工具类")
public class FileUtil {

	private static Logger logger = Logger.getLogger(FileUtil.class);

	private static final String TXT_FILE_SUFFIX = ".txt";

	/**
	 * 读取文件并压缩数据然后转Base64编码
	 * 
	 * @param filePath
	 *            文件的绝对路径地址
	 * @return
	 */
	@MethodInfo(Name = "读取文件并压缩数据然后转Base64编码", paramInfo = "文件的绝对路径地址", returnInfo = "转码结果")
	public static String base64(String filePath) {
		byte[] data = ImageUtils.getPicData(filePath);
		if (data == null) {
			return null;
		}
		byte[] zipData = ZipUtils.gZip(data);
		return Base64.encodeBase64String(zipData);
	}

	/**
	 * 将二进制压缩数据转成Base64编码
	 * 
	 * @param data
	 *            二进制压缩数据
	 * @return bese64编码
	 */
	@MethodInfo(Name = "将二进制压缩数据转成Base64编码", paramInfo = { "二进制压缩数据" }, returnInfo = "base64编码")
	public static String base64(byte[] data) {
		if (data == null) {
			return null;
		}
		byte[] zipData = ZipUtils.gZip(data);
		return Base64.encodeBase64String(zipData);
	}

	/**
	 * 二进制文件写入文件
	 * 
	 * @param data
	 *            二进制数据
	 * @param fileName
	 *            文件名
	 * @param path
	 *            路径地址
	 */
	@MethodInfo(Name = "二进制文件写入文件", paramInfo = { "二进制数据", "文件名", "路径地址" })
	public static void writeFile(byte[] data, String fileName, String path) {
		try {
			String url = path + "//" + fileName;
			FileUtils.writeByteArrayToFile(new File(url), data);
		} catch (Exception e) {
			logger.error(String.format("【文件写入出错】：%s", e));
		}
	}

	/**
	 * 把压缩过的base64串解码解压写入磁盘中
	 * 
	 * @param base64
	 *            压缩过的base64串
	 * @param fileName
	 *            文件名
	 * @param path
	 *            路径地址
	 */
	@MethodInfo(Name = "把压缩过的base64串解码解压写入磁盘中", paramInfo = { "压缩过的base64串", "文件名", "路径地址" })
	public static void decode(String base64, String fileName, String path) {
		// 解码
		byte[] data = Base64.decodeBase64(base64);
		data = ZipUtils.unGZip(data);
		writeFile(data, fileName, path);
	}

	/**
	 * 获取路径下的所有文件/文件夹
	 * 
	 * @param directoryPath
	 *            需要遍历的文件夹路径
	 * @param isAddDirectory
	 *            是否将子文件夹的路径也添加到list集合中
	 * @return 文件路径集合
	 */
	@MethodInfo(Name = "获取路径下的所有文件/文件夹", paramInfo = { "需要遍历的文件夹路径", "是否将子文件夹的路径也添加到list集合中" }, returnInfo = "文件路径集合")
	public static List<String> getAllFilePath(String directoryPath, boolean isAddDirectory) {
		List<String> list = new ArrayList<String>();
		File baseFile = new File(directoryPath);
		if (baseFile.isFile() || !baseFile.exists()) {
			return list;
		}
		File[] files = baseFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				if (isAddDirectory) {
					list.add(file.getAbsolutePath());
				}
				list.addAll(getAllFilePath(file.getAbsolutePath(), isAddDirectory));
			} else {
				list.add(file.getAbsolutePath());
			}
		}
		return list;
	}

	/**
	 * 获取路径下的所有文件名
	 * 
	 * @param directoryPath
	 *            需要遍历的文件夹路径
	 * @param isPostfix
	 *            是否切割后缀
	 * @return 文件名集合
	 */
	@MethodInfo(Name = "获取路径下的所有文件名", paramInfo = { "需要遍历的文件夹路径", "是否切割后缀" }, returnInfo = "文件名集合")
	public static List<String> getAllFileName(String directoryPath, boolean isPostfix) {
		List<String> list = new ArrayList<String>();
		File baseFile = new File(directoryPath);
		if (baseFile.isFile() || !baseFile.exists()) {
			return list;
		}
		File[] files = baseFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				list.addAll(getAllFileName(file.getName(), isPostfix));
			} else {
				if (isPostfix) {
					list.add(file.getName().substring(0, file.getName().lastIndexOf(".")));
				} else {
					list.add(file.getName());
				}
			}
		}
		return list;
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 *            生成路径
	 * @param fileName
	 *            文件名
	 * @param text
	 *            文本内容
	 * @param type
	 *            文件类型
	 */
	@MethodInfo(Name = "创建文件", paramInfo = { "生成路径", "文件名", "文本内容", "文件类型" })
	public static void createFile(String path, String fileName, List<String> text, FileTypeEnum type) {
		judgeFileType(path, fileName, text, type);
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 *            生成路径
	 * @param text
	 *            文本内容
	 * @param type
	 *            文件类型
	 */
	@MethodInfo(Name = "创建文件", paramInfo = { "生成路径", "文本内容", "文件类型" })
	public static void createFile(String path, List<String> text, FileTypeEnum type) {
		judgeFileType(path, UUID.randomUUID().toString(), text, type);
	}

	/**
	 * 创建文件
	 * 
	 * @param text
	 *            文本内容
	 * @param type
	 *            文件类型
	 */
	@MethodInfo(Name = "创建文件", paramInfo = { "文本内容", "文件类型" })
	public static void createFile(List<String> text, FileTypeEnum type) {
		judgeFileType(Constant.FILE_SAVE_PATH, UUID.randomUUID().toString(), text, type);
	}

	/**
	 * 根据类型区分生成文件
	 * 
	 * @param path
	 *            生成路径
	 * @param fileName
	 *            文件名
	 * @param text
	 *            文本内容
	 * @param type
	 *            文件类型
	 */
	private static void judgeFileType(String path, String fileName, List<String> text, FileTypeEnum type) {
		switch (type) {
		case TXT_FILE:
			createTxtFile(path, fileName, text);
		}
	}

	/**
	 * 创建Txt文件
	 * 
	 * @param path
	 *            生成路径
	 * @param fileName
	 *            文件名
	 * @param text
	 *            文本内容
	 */
	private static void createTxtFile(String path, String fileName, List<String> text) {
		try {
			String filePath = path + "/" + fileName + TXT_FILE_SUFFIX;
			File file = new File(filePath);
			// 如果文件不存在
			if (!file.exists()) {
				file.createNewFile();
				logger.info(String.format("【文件创建成功】：文件路径：%s", filePath));
				writeFileContent(file, text);
			} else {
				logger.info(String.format("【文件已存在】：文件路径：%s", filePath));
			}
		} catch (Exception e) {
			logger.warn(String.format("【文件创建失败】:%s", e.getMessage()));
		}
	}

	/**
	 * 写入文件内容
	 * 
	 * @param file
	 *            文件
	 * @param text
	 *            内容
	 * @throws IOException
	 */
	private static void writeFileContent(File file, List<String> text) throws IOException {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		String temp = Constant.NULL_KEY_STR;

		try {
			// 将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buffer = new StringBuffer();

			// 文件原有内容并换行
			for (; (temp = br.readLine()) != null;) {
				buffer.append(temp + "\r\n");
			}
			for (String strt : text) {
				buffer.append(strt + "\r\n");
			}

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buffer.toString().toCharArray());
			pw.flush();
		} catch (Exception e) {
			logger.warn(String.format("【文件写入失败】：%s", e.getMessage()));
		} finally {
			// 不要忘记关闭
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	}
}
