package com.tangerinespecter.javabaseutils.common.util;

import com.tangerineSpecter.javaBaseUtils.common.annotation.ClassInfo;
import com.tangerineSpecter.javaBaseUtils.common.annotation.MethodInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩和解压工具类
 *
 * @author TangerineSpecter
 */
@Slf4j
@ClassInfo(Name = "压缩和解压工具类")
public class ZipUtils extends BaseUtils {

    private static final int BUFFER_SIZE = 2 * 1024;

    /***
     * 压缩数据
     *
     * @param data
     *            要压缩的二进制数据
     * @return
     */
    @MethodInfo(Name = "压缩数据", paramInfo = {"二进制数据"}, returnInfo = "压缩结果")
    public static byte[] gZip(byte[] data) {
        ByteArrayOutputStream bos = null;
        GZIPOutputStream gzip = null;
        byte[] b = null;
        try {
            bos = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            b = bos.toByteArray();
        } catch (Exception e) {
            log.error(ZipLogger.ZIP_DATA_ERROR, e.getMessage());
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.warn(ZipLogger.STREAM_CLOSE_ERROR, e.getMessage());
                }
            }
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    log.warn(ZipLogger.STREAM_CLOSE_ERROR, e.getMessage());
                }
            }
        }
        return b;
    }

    /**
     * 解压数据
     *
     * @param data 要解压的二进制数据
     * @return
     */
    @MethodInfo(Name = "解压数据", paramInfo = {"二进制数据"}, returnInfo = "解压结果")
    public static byte[] unGZip(byte[] data) {
        ByteArrayInputStream bis = null;
        GZIPInputStream gzip = null;
        ByteArrayOutputStream baos = null;
        byte[] b = null;
        try {
            bis = new ByteArrayInputStream(data);
            gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[BUFFER_SIZE];
            int num = -1;
            baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
        } catch (Exception e) {
            log.error(ZipLogger.UNZIP_DATA_ERROR, e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.warn(ZipLogger.STREAM_CLOSE_ERROR, e.getMessage());
                }
            }
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    log.warn(ZipLogger.STREAM_CLOSE_ERROR, e.getMessage());
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    log.warn(ZipLogger.STREAM_CLOSE_ERROR, e.getMessage());
                }
            }
        }
        return b;
    }

    /**
     * 压缩文件
     *
     * @param srcFilePath  源文件路径
     * @param destFileName 压缩包名字
     */
    @MethodInfo(Name = "压缩文件", paramInfo = {"源文件路径", "压缩包名字"})
    public static void compress(String srcFilePath, String destFileName) {
        File srcFile = new File(srcFilePath);
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        if (!srcFile.exists()) {
            log.info(FileLogger.FILE_NOT_FOUND);
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
            log.error(String.format("【压缩文件出现异常】：%s", e));
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.warn(ZipLogger.STREAM_CLOSE_ERROR, e.getMessage());
                }
            }
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    log.warn(ZipLogger.STREAM_CLOSE_ERROR, e.getMessage());
                }
            }
        }
    }

    /**
     * 对路径下文件根据类型进行压缩
     *
     * @param srcFile 源文件
     * @param zos     压缩流
     * @param baseDir 压缩路径
     */
    @MethodInfo(Name = "对路径下文件根据类型进行压缩", paramInfo = {"源文件", "压缩流", "压缩路径"})
    private static void compressBy(File srcFile, ZipOutputStream zos, String baseDir) {
        if (!srcFile.exists()) {
            log.info(FileLogger.FILE_NOT_FOUND);
            return;
        }
        log.info(ZipLogger.SOURCE_FILE_PATH, baseDir + srcFile.getName());
        // 判断压缩是文件还是文件夹
        if (srcFile.isFile()) {
            compressFile(srcFile, zos, baseDir);
        } else if (srcFile.isDirectory()) {
            compressDir(srcFile, zos, baseDir);
        } else {
            log.info(FileLogger.FILE_TYPE_UNKNOWN);
        }
    }

    /**
     * 压缩文件
     *
     * @param srcFile 源文件
     * @param zos     压缩流
     * @param baseDir 压缩路径
     */
    @MethodInfo(Name = "压缩文件", paramInfo = {"源文件", "压缩流", "压缩路径"})
    private static void compressFile(File srcFile, ZipOutputStream zos, String baseDir) {
        BufferedInputStream bis = null;
        if (!srcFile.exists()) {
            log.info(FileLogger.FILE_NOT_FOUND);
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
            log.error(ZipLogger.ZIP_DATA_ERROR, e.getMessage());
            e.printStackTrace();
        } finally {
            if (zos != null) {
                try {
                    zos.closeEntry();
                } catch (IOException e) {
                    log.warn(ZipLogger.STREAM_CLOSE_ERROR, e.getMessage());
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.warn(ZipLogger.STREAM_CLOSE_ERROR, e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 压缩文件夹
     *
     * @param srcDir  源文件
     * @param zos     压缩流
     * @param baseDir 压缩路径
     */
    @MethodInfo(Name = "压缩文件夹", paramInfo = {"源文件", "压缩流", "压缩路径"})
    private static void compressDir(File srcDir, ZipOutputStream zos, String baseDir) {
        if (!srcDir.exists()) {
            log.info(FileLogger.DIRFILE_NOT_FOUND);
            return;
        }

        File[] files = srcDir.listFiles();
        if (files.length == 0) {
            try {
                zos.putNextEntry(new ZipEntry(baseDir + srcDir.getName() + File.separator));
            } catch (Exception e) {
                log.error(ZipLogger.ZIP_DATA_ERROR, e.getMessage());
                e.printStackTrace();
            }
        }
        for (File file : files) {
            compressBy(file, zos, baseDir);
        }
    }

}
