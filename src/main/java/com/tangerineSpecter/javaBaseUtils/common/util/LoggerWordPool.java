package com.tangerinespecter.javabaseutils.common.util;

/**
 * 日志信息信息池
 *
 * @author TangerineSpecter
 */
public class LoggerWordPool {

    /**
     * 文件工具类相关日志信息
     */
    static class FileLogger {
        /**
         * 文件写入错误
         */
        public static final String FILE_WRITE_ERROR = "【文件写入出错】：{}";
        /**
         * 文件删除成功
         */
        public static final String FILE_DELETE_SUCCESS = "【删除文件成功】文件名：{}";
        /**
         * 文件夹删除成功
         */
        public static final String DIRFILE_DELETE_SUCCESS = "【删除文件夹成功】文件夹名：{}";
        /**
         * 删除文件总数
         */
        public static final String FILE_DELETE_TOTAL_COUNT = "【删除文件总数】：{}个文件";
        /**
         * 文件转移成功
         */
        public static final String FILE_MOVE_SUCCESS = "【文件转移成功】转移路径：{}";
        /**
         * 存在同名文件
         */
        public static final String FILE_SAME_NAME_EXIST = "【存在同名文件】：{}";
        /**
         * 总共转移文件数
         */
        public static final String FILE_MOVE_TOTAL_COUNT = "【总共转移文件数】：{}个文件";
        /**
         * 文件夹创建成功
         */
        public static final String FILE_CREATE_SUCCESS = "【文件夹创建成功】文件路径：{}";
        /**
         * 文件夹创建失败
         */
        public static final String FILE_CREATE_FAIL = "【文件夹创建失败】：{}";
        /**
         * 文件没有找到
         */
        public static final String FILE_NOT_FOUND = "【文件没有找到】";
        /**
         * 文件夹没有找到
         */
        public static final String DIRFILE_NOT_FOUND = "【文件夹没有找到】";
        /**
         * 文件类型不明
         */
        public static final String FILE_TYPE_UNKNOWN = "【文件类型不明】";
    }

    /**
     * 解压工具类日志信息
     */
    class ZipLogger {
        /**
         * 压缩数据异常
         */
        public static final String ZIP_DATA_ERROR = "【压缩数据异常】：{}";
        /**
         * 流关闭异常
         */
        public static final String STREAM_CLOSE_ERROR = "【流关闭异常】：{}";
        /**
         * 解压数据异常
         */
        public static final String UNZIP_DATA_ERROR = "【解压数据异常】:{}";
        /**
         * 源文件路径
         */
        public static final String SOURCE_FILE_PATH = "【源文件路径】：{}";
    }
}
