package com.tangerinespecter.javabaseutils.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.tangerinespecter.javabaseutils.common.annotation.ClassInfo;
import com.tangerinespecter.javabaseutils.common.annotation.MethodInfo;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成工具类
 *
 * @author TangerineSpecter
 */
@Slf4j
@ClassInfo(Name = "二维码生成工具类")
public class QrCodeUtils {

    /**
     * 生成不带logo的二维码
     *
     * @param data
     * @param charset
     * @param hint
     * @param width
     * @param height
     * @return
     */
    @MethodInfo(Name = "生成不带logo的二维码", paramInfo = {"数据", "编码类型", "二维码属性", "宽度", "高度"}, returnInfo = "二维码图片")
    public static BufferedImage createQrCode(String data, String charset, Map<EncodeHintType, ?> hint, int width,
                                             int height) {
        BitMatrix matrix;
        try {
            matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE,
                    width, height, hint);
            return MatrixToImageWriter.toBufferedImage(matrix);
        } catch (Exception e) {
            log.error("【生成不带logo的二维码生成出错】");
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 以默认参数生成不带logo的二维码
     *
     * @param data
     * @param width
     * @param height
     * @return
     */
    @MethodInfo(Name = "生成不带logo的默认参数二维码", paramInfo = {"数据", "宽度", "高度"}, returnInfo = "二维码图片")
    public static BufferedImage createQrCode(String data, int width, int height) {
        String charset = "utf-8";
        Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>(16);
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.CHARACTER_SET, charset);
        hint.put(EncodeHintType.MARGIN, 0);
        return createQrCode(data, charset, hint, width, height);
    }

    /**
     * 生成带logo的二维码
     *
     * @param data
     * @param charset
     * @param width
     * @param height
     * @param logoFile
     * @return
     */
    @MethodInfo(Name = "生成带logo的二维码", paramInfo = {"数据", "编码类型", "二维码属性", "宽度", "高度",
            "logo文件路径"}, returnInfo = "二维码图片")
    public static BufferedImage createQrCodeWithLogo(String data, String charset, Map<EncodeHintType, ?> hint,
                                                     int width, int height, File logoFile) {
        try {
            BufferedImage qrcode = createQrCode(data, charset, hint, width, height);
            BufferedImage logo = ImageIO.read(logoFile);
            int deltaHeight = height - logo.getHeight();
            int deltaWidth = width - logo.getWidth();

            BufferedImage combined = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) combined.getGraphics();
            g.drawImage(qrcode, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g.drawImage(logo, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
            return combined;
        } catch (Exception e) {
            log.error("【生成带logo的二维码生成出错】");
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 以默认参数生成带logo的二维码
     *
     * @param data
     * @param width
     * @param height
     * @return
     */
    @MethodInfo(Name = "生成带logo的默认参数二维码", paramInfo = {"数据", "宽度", "高度", "logo文件路径"}, returnInfo = "二维码图片")
    public static BufferedImage createQrCodeWithLogo(String data, int width, int height, File logo) {
        String charset = "utf-8";
        Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>(16);
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.CHARACTER_SET, charset);
        hint.put(EncodeHintType.MARGIN, 0);
        return createQrCodeWithLogo(data, charset, hint, width, height, logo);
    }
}
