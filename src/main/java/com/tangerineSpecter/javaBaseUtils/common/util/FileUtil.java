package com.tangerinespecter.javabaseutils.common.util;

import com.tangerineSpecter.javaBaseUtils.common.annotation.ClassInfo;
import com.tangerineSpecter.javaBaseUtils.common.annotation.MethodInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件工具类
 *
 * @author TangerSpecter
 */
@Slf4j
@ClassInfo(Name = "文件工具类")
public class FileUtil extends BaseUtils {

    /**
     * txt文件
     */
    private static final String TXT_FILE_SUFFIX = ".txt";
    /**
     * markdown文件
     */
    private static final String MARKDOWN_FILE_SUFFIX = ".md";
    /**
     * xls文件
     */
    private static final String XLS_FILE_SUFFIX = ".xls";
    /**
     * xlsx文件
     */
    private static final String XLSX_FILE_SUFFIX = ".xlsx";

    /**
     * 读取文件并压缩数据然后转Base64编码
     *
     * @param filePath 文件的绝对路径地址
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
     * @param data 二进制压缩数据
     * @return bese64编码
     */
    @MethodInfo(Name = "将二进制压缩数据转成Base64编码", paramInfo = {"二进制压缩数据"}, returnInfo = "base64编码")
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
     * @param data     二进制数据
     * @param fileName 文件名
     * @param path     路径地址
     */
    @MethodInfo(Name = "二进制文件写入文件", paramInfo = {"二进制数据", "文件名", "路径地址"})
    public static void writeFile(byte[] data, String fileName, String path) {
        try {
            String url = path + "//" + fileName;
            FileUtils.writeByteArrayToFile(new File(url), data);
        } catch (Exception e) {
            log.error(FileLogger.FILE_WRITE_ERROR, e.getMessage());
        }
    }

    /**
     * 把压缩过的base64串解码解压写入磁盘中
     *
     * @param base64   压缩过的base64串
     * @param fileName 文件名
     * @param path     路径地址
     */
    @MethodInfo(Name = "把压缩过的base64串解码解压写入磁盘中", paramInfo = {"压缩过的base64串", "文件名", "路径地址"})
    public static void decode(String base64, String fileName, String path) {
        // 解码
        byte[] data = Base64.decodeBase64(base64);
        data = ZipUtils.unGZip(data);
        writeFile(data, fileName, path);
    }

    /**
     * 获取路径下的所有文件/文件夹
     *
     * @param directoryPath  需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return 文件路径集合
     */
    @MethodInfo(Name = "获取路径下的所有文件/文件夹", paramInfo = {"需要遍历的文件夹路径", "是否将子文件夹的路径也添加到list集合中"}, returnInfo = "文件路径集合")
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
     * @param directoryPath 需要遍历的文件夹路径
     * @param isPostfix     是否切割后缀
     * @return 文件名集合
     */
    @MethodInfo(Name = "获取路径下的所有文件名", paramInfo = {"需要遍历的文件夹路径", "是否切割后缀"}, returnInfo = "文件名集合")
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
     * @param path     生成路径
     * @param fileName 文件名
     * @param text     文本内容
     * @param type     文件类型
     */
    @MethodInfo(Name = "创建文件", paramInfo = {"生成路径", "文件名", "文本内容", "文件类型"})
    public static void createFile(String path, String fileName, List<String> text, FileTypeEnum type) {
        judgeFileType(path, fileName, text, type);
    }

    /**
     * 创建文件
     *
     * @param path 生成路径
     * @param text 文本内容
     * @param type 文件类型
     */
    @MethodInfo(Name = "创建文件", paramInfo = {"生成路径", "文本内容", "文件类型"})
    public static void createFile(String path, List<String> text, FileTypeEnum type) {
        judgeFileType(path, UUID.randomUUID().toString(), text, type);
    }

    /**
     * 创建文件
     *
     * @param text 文本内容
     * @param type 文件类型
     */
    @MethodInfo(Name = "创建文件", paramInfo = {"文本内容", "文件类型"})
    public static void createFile(List<String> text, FileTypeEnum type) {
        judgeFileType(Constant.FILE_SAVE_PATH, UUID.randomUUID().toString(), text, type);
    }

    /**
     * 删除文件
     *
     * @param path 文件路径
     */
    @MethodInfo(Name = "删除文件", paramInfo = {"文件路径"})
    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            file.delete();
            log.info(FileLogger.FILE_DELETE_SUCCESS, file.getName());
        }
    }

    /**
     * 删除文件
     *
     * @param path     文件路径
     * @param fileName 文件名
     */
    @MethodInfo(Name = "删除文件", paramInfo = {"文件路径", "文件名"})
    public static void deleteFile(String path, String fileName) {
        File file = new File(path + "/" + fileName);
        if (file.exists() && file.isFile()) {
            file.delete();
            log.info(FileLogger.FILE_DELETE_SUCCESS, file.getName());
        }
    }

    /**
     * 删除目录下指定后缀的文件
     *
     * @param path       目录路径
     * @param fileSuffix 文件后缀
     */
    @MethodInfo(Name = "目录路径", paramInfo = {"目录路径", "文件后缀"})
    public static void deleteFileSuffix(String path, FileTypeEnum fileSuffix) {
        File dirFile = new File(path);
        if (dirFile.exists() && dirFile.isDirectory()) {
            File[] files = dirFile.listFiles();
            String suffix = getFileSuffix(fileSuffix);
            for (File file : files) {
                if (file.isFile() && !StringUtils.isEmpty(suffix) && (file.getName().indexOf(suffix) != -1)) {
                    file.delete();
                    log.info(FileLogger.FILE_DELETE_SUCCESS, file.getName());
                }
            }
        }
    }

    /**
     * 删除文件夹
     *
     * @param path 文件夹路径
     * @param flag 是否删除文件夹内容
     */
    @MethodInfo(Name = "删除文件夹", paramInfo = {"文件夹路径", "是否删除文件夹内容"})
    public static void deleteDirFile(String path, boolean flag) {
        int count = Constant.Number.COMMON_NUMBER_ZERO;
        File dirFile = new File(path);
        if (dirFile.exists() && dirFile.isDirectory()) {
            File[] files = dirFile.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirFile(path + file.getName(), flag);
                    log.info(FileLogger.DIRFILE_DELETE_SUCCESS, file.getName());
                } else if (flag) {
                    file.delete();
                    count++;
                    log.info(FileLogger.FILE_DELETE_SUCCESS, file.getName());
                }
            }
            log.info(FileLogger.FILE_DELETE_TOTAL_COUNT, count);
        }
    }

    /**
     * 文件重命名
     *
     * @param path    文件路径
     * @param oldName 旧文件名字
     * @param newName 新文件名字
     */
    @MethodInfo(Name = "文件路径", paramInfo = {"旧文件名字", "新文件名字"})
    private static void renameFile(String path, String oldName, String newName) {
        if (!oldName.equals(newName)) {
            File oldFile = new File(path + "/" + oldName);
            File newFile = new File(path + "/" + newName);
            if (newFile.exists()) {
                log.info(FileLogger.FILE_SAME_NAME_EXIST, newFile.getName());
            } else {
                oldFile.renameTo(newFile);
            }
        }
    }

    /**
     * 转移文件目录
     *
     * @param fileName 文件名
     * @param oldPath  旧路径
     * @param newPath  新路径
     * @param isCover  是否覆盖
     */
    @MethodInfo(Name = "转移文件目录", paramInfo = {"文件名", "旧路径", "新路径", "是否覆盖"})
    public static void moveFileDir(String fileName, String oldPath, String newPath, boolean isCover) {
        if (!oldPath.equals(newPath)) {
            File oldFile = new File(oldPath + "/" + fileName);
            File newFile = new File(newPath + "/" + fileName);
            File newDir = new File(newPath);
            if (!newDir.exists()) {
                newDir.mkdir();
            }
            // 新路径是否存在同名文件
            if (newFile.exists()) {
                // 是否覆盖
                if (isCover) {
                    oldFile.renameTo(newFile);
                    log.info(FileLogger.FILE_MOVE_SUCCESS, newPath + "/" + fileName);
                } else {
                    log.info(FileLogger.FILE_SAME_NAME_EXIST, newFile.getName());
                }
            } else {
                oldFile.renameTo(newFile);
                log.info(FileLogger.FILE_MOVE_SUCCESS, newPath + "/" + fileName);
            }
        }
    }

    /**
     * 转移文件目录（包含名字）
     *
     * @param fuzzyFileName 文件名
     * @param oldPath       旧路径
     * @param newPath       新路径
     * @param isCover       是否覆盖
     */
    @MethodInfo(Name = "转移文件目录（包含名字）", paramInfo = {"文件名", "旧路径", "新路径", "是否覆盖"})
    public static void moveFuzzyFileDir(String fuzzyFileName, String oldPath, String newPath, boolean isCover) {
        int count = Constant.Number.COMMON_NUMBER_ZERO;
        if (!oldPath.equals(newPath)) {
            File oldDir = new File(oldPath);
            File newDir = new File(newPath);
            for (File file : oldDir.listFiles()) {
                if (!newDir.exists()) {
                    newDir.mkdir();
                }
                // 判断目录下文件名是否包含转移文件名
                if (file.getName().indexOf(fuzzyFileName) != -1) {
                    moveFileDir(file.getName(), oldPath, newPath, isCover);
                    count++;
                }
            }
            log.info(FileLogger.FILE_MOVE_TOTAL_COUNT, count));
        }
    }

    /**
     * 创建文件夹
     *
     * @param path 文件夹路径
     */
    @MethodInfo(Name = "创建文件夹", paramInfo = {"文件夹路径"})
    public static void createDir(String path) {
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
            log.info(FileLogger.FILE_CREATE_SUCCESS, path + "/" + dirFile.getName());
        }
    }

    /**
     * 读取文件内容
     *
     * @param path 文件路径
     * @return 文件内容
     * @throws IOException
     */
    @MethodInfo(Name = "读取文件内容", paramInfo = {"文件路径"}, returnInfo = "文件内容")
    public static String loadingFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            log.warn(FileLogger.FILE_NOT_FOUND);
            throw new FileNotFoundException();
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        temp = br.readLine();
        while (temp != null) {
            sb.append(temp + "\r\n");
            temp = br.readLine();
        }
        br.close();
        return sb.toString();
    }

    /**
     * 根据类型区分生成文件
     *
     * @param path     生成路径
     * @param fileName 文件名
     * @param text     文本内容
     * @param type     文件类型
     */
    private static void judgeFileType(String path, String fileName, List<String> text, FileTypeEnum type) {
        switch (type) {
            case TXT_FILE:
                createTxtFile(path, fileName, text);
                break;
            case MARKDOWN_FILE:
                createMarkdownFile(path, fileName, text);
                break;
            default:
                return;
        }
    }

    /**
     * 创建Txt文件
     *
     * @param path     生成路径
     * @param fileName 文件名
     * @param text     文本内容
     */
    private static void createTxtFile(String path, String fileName, List<String> text) {
        try {
            String filePath = path + "/" + fileName + TXT_FILE_SUFFIX;
            File dirFile = new File(path);
            File file = new File(filePath);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            // 如果文件不存在
            if (!file.exists()) {
                file.createNewFile();
                log.info(FileLogger.FILE_CREATE_SUCCESS, filePath);
                writeFileContent(file, text);
            } else {
                log.info(FileLogger.FILE_SAME_NAME_EXIST, filePath);
            }
        } catch (Exception e) {
            log.info(FileLogger.FILE_CREATE_FAIL, e.getMessage());
        }
    }

    /**
     * 创建Markdown文件
     *
     * @param path     生成路径
     * @param fileName 文件名
     * @param text     文本内容
     */
    private static void createMarkdownFile(String path, String fileName, List<String> text) {
        try {
            String filePath = path + "/" + fileName + MARKDOWN_FILE_SUFFIX;
            File dirFile = new File(path);
            File file = new File(filePath);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            // 如果文件不存在
            if (!file.exists()) {
                file.createNewFile();
                log.info(FileLogger.FILE_CREATE_SUCCESS, filePath);
                writeFileContent(file, text);
            } else {
                log.info(FileLogger.FILE_SAME_NAME_EXIST, filePath);
            }
        } catch (Exception e) {
            log.info(FileLogger.FILE_CREATE_FAIL, e.getMessage());
        }
    }

    /**
     * 写入文件内容
     *
     * @param file 文件
     * @param text 内容
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
            for (; (temp = br.readLine()) != null; ) {
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
            log.error(FileLogger.FILE_WRITE_ERROR, e.getMessage());
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

    /**
     * 获取文件后缀
     */
    private static String getFileSuffix(FileTypeEnum fileSuffix) {
        switch (fileSuffix) {
            case TXT_FILE:
                return TXT_FILE_SUFFIX;
            case MARKDOWN_FILE:
                return MARKDOWN_FILE_SUFFIX;
            case XLS_EXCEL_FILE:
                return XLS_FILE_SUFFIX;
            case XLSX_EXCEL_FILE:
                return XLSX_FILE_SUFFIX;
            default:
                return Constant.NULL_KEY_STR;
        }
    }
}
