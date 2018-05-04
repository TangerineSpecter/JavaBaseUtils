package common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

	private static final int BUFFER_SIZE = 2 * 1024;

	/***
	 * 压缩数据
	 * 
	 * @param data
	 *            要压缩的二进制数据
	 * @return
	 */
	public static byte[] gZip(byte[] data) {
		ByteArrayOutputStream bos = null;
		GZIPOutputStream gzip = null;
		byte[] b = null;
		try {
			bos = new ByteArrayOutputStream();
			gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = bos.toByteArray();
		} catch (Exception e) {
			logger.error(String.format("【压缩数据异常】：%s", e));
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					logger.warn(String.format("【字节流关闭异常】：%s", e));
				}
			}
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					logger.warn(String.format("【压缩流关闭异常】：%s", e));
				}
			}
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
		ByteArrayInputStream bis = null;
		GZIPInputStream gzip = null;
		byte[] b = null;
		try {
			bis = new ByteArrayInputStream(data);
			gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[BUFFER_SIZE];
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
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					logger.warn(String.format("【字节流关闭异常】：%s", e));
				}
			}
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					logger.warn(String.format("【压缩流关闭异常】：%s", e));
				}
			}
		}
		return b;
	}

	/**
	 * 压缩文件
	 * 
	 * @param srcFilePath
	 *            源文件路径
	 * @param destFileName
	 *            压缩包名字
	 */
	public static void compress(String srcFilePath, String destFileName) {
		File srcFile = new File(srcFilePath);
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		if (!srcFile.exists()) {
			logger.info("【需要压缩的文件不存在...】");
		}
		String destFilePath = Constant.ZIP_SAVE_PATH + destFileName;
		File destDir = new File(Constant.ZIP_SAVE_PATH);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		File destFile = new File(destFilePath);
		try {
			fos = new FileOutputStream(destFile);
			zos = new ZipOutputStream(fos);
			String baseDir = "";
			compressBy(srcFile, zos, baseDir);
		} catch (Exception e) {
			logger.error(String.format("【压缩文件出现异常】：%s", e));
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.warn(String.format("【文件流关闭异常】：%s", e));
				}
			}
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					logger.warn(String.format("【压缩流关闭异常】：%s", e));
				}
			}
		}
	}

	/**
	 * 对路径下文件根据类型进行压缩
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
		logger.info(String.format("【压缩源文件路径】：%s", baseDir + srcFile.getName()));
		// 判断压缩是文件还是文件夹
		if (srcFile.isFile()) {
			compressFile(srcFile, zos, baseDir);
		} else if (srcFile.isDirectory()) {
			compressDir(srcFile, zos, baseDir);
		} else {
			logger.info("【压缩类型不明】");
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
		BufferedInputStream bis = null;
		if (!srcFile.exists()) {
			logger.info("【压缩文件不存在】");
			return;
		}
		try {
			bis = new BufferedInputStream(new FileInputStream(srcFile));
			ZipEntry entry = new ZipEntry(baseDir + srcFile.getName());
			zos.putNextEntry(entry);
			int len;
			byte[] b = new byte[BUFFER_SIZE];
			while ((len = bis.read(b)) != -1) {
				zos.write(b, 0, len);
			}
		} catch (Exception e) {
			logger.error(String.format("【压缩文件出现异常】：%s", e));
			e.printStackTrace();
		} finally {
			if (zos != null) {
				try {
					zos.closeEntry();
				} catch (IOException e) {
					logger.warn(String.format("【压缩流关闭异常】：%s", e));
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					logger.warn(String.format("【文件流关闭异常】：%s", e));
					e.printStackTrace();
				}
			}
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
