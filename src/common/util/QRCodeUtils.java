package common.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import common.annotation.MethodInfo;

/**
 * 二维码生成工具类
 * 
 * @author TangerineSpecter
 */
public class QrCodeUtils {

	private static Logger logger = Logger.getLogger(QrCodeUtils.class);

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
	@MethodInfo(Name = "生成不带logo的二维码", paramInfo = { "数据", "编码类型", "二维码属性", "宽度", "高度" }, returnInfo = "二维码图片")
	public static BufferedImage createQRCode(String data, String charset, Map<EncodeHintType, ?> hint, int width,
			int height) {
		BitMatrix matrix;
		try {
			matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE,
					width, height, hint);
			return MatrixToImageWriter.toBufferedImage(matrix);
		} catch (Exception e) {
			logger.error("【生成不带logo的二维码生成出错】");
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 以默认参数生成不带logo的二维码
	 * 
	 * @param data
	 * @param charset
	 * @param width
	 * @param height
	 * @return
	 */
	@MethodInfo(Name = "生成不带logo的默认参数二维码", paramInfo = { "数据", "编码类型", "宽度", "高度" }, returnInfo = "二维码图片")
	public static BufferedImage createQRCode(String data, int width, int height) {
		String charset = "utf-8";
		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hint.put(EncodeHintType.CHARACTER_SET, charset);
		hint.put(EncodeHintType.MARGIN, 0);
		return createQRCode(data, charset, hint, width, height);
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
	@MethodInfo(Name = "生成带logo的二维码", paramInfo = { "数据", "编码类型", "二维码属性", "宽度", "高度",
			"logo文件路径" }, returnInfo = "二维码图片")
	public static BufferedImage createQRCodeWithLogo(String data, String charset, Map<EncodeHintType, ?> hint,
			int width, int height, File logoFile) {
		try {
			BufferedImage qrcode = createQRCode(data, charset, hint, width, height);
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
			logger.error("【生成带logo的二维码生成出错】");
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
	@MethodInfo(Name = "生成带logo的默认参数二维码", paramInfo = { "数据", "宽度", "高度", "logo文件路径" }, returnInfo = "二维码图片")
	public static BufferedImage createQRCodeWithLogo(String data, int width, int height, File logo) {
		String charset = "utf-8";
		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hint.put(EncodeHintType.CHARACTER_SET, charset);
		hint.put(EncodeHintType.MARGIN, 0);
		return createQRCodeWithLogo(data, charset, hint, width, height, logo);
	}
}
