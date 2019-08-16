package com.tangerinespecter.javabaseutils.common.util;

import com.tangerineSpecter.javaBaseUtils.common.annotation.ClassInfo;
import com.tangerinespecter.javabaseutils.common.annotation.MethodInfo;

import java.io.File;
import java.util.Calendar;

/**
 * 路径处理工具类
 *
 * @author TangerineSpecter
 */
@ClassInfo(Name = "路径处理工具类")
public class DirUtils {

    /**
     * 图片
     */
    private static final String IMG_DIR = "img";
    /**
     * 音频
     */
    private static final String AUDIO_DIR = "audio";
    /**
     * 视频
     */
    private static final String VIDEO_DIR = "video";

    /**
     * getImgDir 获取系统图片的存放路径
     *
     * @param uuid
     * @return 图片路径
     */
    @MethodInfo(Name = "获取系统图片的存放路径", paramInfo = {"UUID"}, returnInfo = "图片路径")
    public static String getImgDir(String uuid) {
        return getDir(IMG_DIR, uuid);
    }

    /**
     * getAudioDir 获取系统音频的存放路径
     *
     * @param uuid
     * @return 音频路径
     */
    @MethodInfo(Name = "获取系统音频的存放路径", paramInfo = {"UUID"}, returnInfo = "音频路径")
    public static String getAudioDir(String uuid) {
        return getDir(AUDIO_DIR, uuid);
    }

    /**
     * getVideoDir 获取系统视频的存放路径
     *
     * @param uuid
     * @return 视频路径
     */
    @MethodInfo(Name = "获取系统视频的存放路径", paramInfo = {"UUID"}, returnInfo = "视频路径")
    public static String getVideoDir(String uuid) {
        return getDir(VIDEO_DIR, uuid);
    }

    /**
     * 获取系统各类子目录
     *
     * @param subDir 路径头
     * @param uuid
     * @return
     */
    @MethodInfo(Name = "获取系统各类子目录", paramInfo = {"路径头", "UUID"}, returnInfo = "文件路径")
    private static String getDir(String subDir, String uuid) {
        StringBuffer path = new StringBuffer(Constant.FILE_SAVE_PATH);
        path.append("/").append(subDir).append("/").append(TimeUtils.timeFormatToDay(Calendar.getInstance().getTime()))
                .append("/").append(uuid).append("/");
        File file = new File(path.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return path.toString();
    }
}
