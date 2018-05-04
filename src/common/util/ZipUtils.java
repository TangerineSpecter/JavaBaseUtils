package common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * 压缩和解压工具类
 * 
 * @author TangerineSpecter
 *
 */
public class ZipUtils {
	private static Logger logger = Logger.getLogger(ZipUtils.class);

	/***
	 * 压缩数据
	 * 
	 * @param data
	 *            要压缩的二进制数据
	 * @return
	 */
	public static byte[] gZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception e) {
			logger.error(String.format("【压缩数据异常】：%s", e));
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 解压数据
	 * 
	 * @param data
	 *            要解压的二进制数据
	 * @return
	 */
	public static byte[] unGZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		} catch (Exception e) {
			logger.error("【解压数据流出错】");
		}
		return b;
	}

	/**
	 * 压缩文件
	 * 
	 * @param srcFilePath
	 *            源文件路径
	 * @param destFilePath
	 *            压缩目的路径
	 */
	public static void compress(String srcFilePath, String destFilePath) {
		File srcFile = new File(srcFilePath);
		if (!srcFile.exists()) {
			logger.info("【需要压缩的文件不存在...】");
		}
		File destFile = new File(destFilePath);
		try {
			FileOutputStream fos = new FileOutputStream(destFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			String baseDir = Constant.ZIP_SAVE_PATH;
			compressBy(srcFile, zos, baseDir);
		} catch (Exception e) {
			logger.error(String.format("【压缩文件出现异常】：%s", e));
		}
	}

	/**
	 * 对路径下文件进行压缩
	 * 
	 * @param srcFile
	 *            源文件
	 * @param zos
	 *            压缩流
	 * @param baseDir
	 *            压缩路径
	 */
	private static void compressBy(File srcFile, ZipOutputStream zos, String baseDir) {
		if (!srcFile.exists()) {
			logger.info("【压缩源文件不存在!】");
			return;
		}
		logger.info(String.format("【压缩路径】：%s", baseDir + srcFile.getName()));
		// 判断压缩是文件还是文件夹
		if (srcFile.isFile()) {
			compressFile(srcFile, zos, baseDir);
		} else if (srcFile.isDirectory()) {
			compressDir(srcFile, zos, baseDir);
		} else {
			logger.info("【压缩不明】");
		}
	}

	/**
	 * 压缩文件
	 * 
	 * @param srcFile
	 *            源文件
	 * @param zos
	 *            压缩流
	 * @param baseDir
	 *            压缩路径
	 */
	private static void compressFile(File srcFile, ZipOutputStream zos, String baseDir) {
		if (!srcFile.exists()) {
			logger.info("【压缩文件不存在】");
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
			ZipEntry entry = new ZipEntry(baseDir);
			zos.putNextEntry(entry);
			byte[] b = new byte[1024];
			while ((bis.read(b)) != -1) {
				zos.write(b);
			}
			bis.close();
		} catch (Exception e) {
			logger.error(String.format("【压缩文件出现异常】：%s", e));
			e.printStackTrace();
		}
	}

	/**
	 * 压缩文件夹
	 * 
	 * @param srcFile
	 *            源文件
	 * @param zos
	 *            压缩流
	 * @param baseDir
	 *            压缩路径
	 */
	private static void compressDir(File srcDir, ZipOutputStream zos, String baseDir) {
		if (!srcDir.exists()) {
			logger.info("【压缩文件夹不存在】");
			return;
		}

		File[] files = srcDir.listFiles();
		if (files.length == 0) {
			try {
				zos.putNextEntry(new ZipEntry(baseDir + srcDir.getName() + File.separator));
			} catch (Exception e) {
				logger.error(String.format("【压缩文件夹出现异常】：%s", e));
				e.printStackTrace();
			}
		}
		for (File file : files) {
			compressBy(file, zos, baseDir);
		}
	}

}
