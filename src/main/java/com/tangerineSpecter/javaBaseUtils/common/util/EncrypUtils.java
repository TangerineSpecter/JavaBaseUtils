package com.tangerinespecter.javabaseutils.common.util;

import com.tangerinespecter.javabaseutils.common.annotation.ClassInfo;
import com.tangerinespecter.javabaseutils.common.annotation.MethodInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 加密工具类
 *
 * @author TangerineSpecter
 */
@Slf4j
@ClassInfo(Name = "加密工具类")
public class EncrypUtils {

    /**
     *
     */
    private static Map<String, MessageDigest> digests = new ConcurrentHashMap<String, MessageDigest>();

    /**
     * 哈希加密算法
     *
     * @param data      需要加密的数据
     * @param algorithm 请求算法的名称，如：MD5、SHA等
     * @return 加密后的数据
     */
    @MethodInfo(Name = "哈希加密算法", paramInfo = {"需要加密的数据", "加密算法名称"}, returnInfo = "加密数据")
    public static String hash(String data, String algorithm) {
        try {
            return hash(data.getBytes("UTF-8"), algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return data;
    }

    /**
     * 哈希加密算法
     *
     * @param bytes     需要加密的字节数组
     * @param algorithm 请求算法的名称，如：MD5、SHA等
     * @return 加密后的数据
     */
    @MethodInfo(Name = "哈希加密算法", paramInfo = {"加密字节数组", "加密算法名称"}, returnInfo = "加密数据")
    public static String hash(byte[] bytes, String algorithm) {
        synchronized (algorithm.intern()) {
            MessageDigest digest = digests.get(algorithm);
            if (digest == null) {
                try {
                    digest = MessageDigest.getInstance(algorithm);
                    digests.put(algorithm, digest);
                } catch (NoSuchAlgorithmException nsae) {
                    log.error("加载MessageDigest类失败：" + algorithm, nsae);
                    return null;
                }
            }
            // 计算哈希值
            digest.update(bytes);
            return encodeHex(digest.digest());
        }
    }

    /**
     * 将字节数组转换成十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    @MethodInfo(Name = "将字节数组转换成十六进制字符串", paramInfo = {"字节数组"}, returnInfo = "十六进制字符串")
    public static String encodeHex(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        int i;

        for (i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }
}
