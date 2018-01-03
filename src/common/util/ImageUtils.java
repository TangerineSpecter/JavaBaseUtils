package common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

/**
 * 图片处理工具类
 * 
 * @author TangerineSpecter
 */
public class ImageUtils {

	/**
	 * 传入要下载的图片的url列表，将url所对应的图片下载到本地
	 * 
	 * @param urlList
	 *            url列表
	 * @return
	 * @throws Exception
	 */
	public static void downloadPicture(List<String> urlList) {
		URL url = null;
		String uuid = UUID.randomUUID().toString();

		for (String urlString : urlList) {
			try {
				url = new URL(urlString);
				DataInputStream dataInputStream = new DataInputStream(url.openStream());
				String imagePath = Constant.FILE_SAVE_PATH + uuid + ".jpg";
				File image = new File(imagePath);
				FileOutputStream fileOutputStream = new FileOutputStream(image);

				byte[] buffer = new byte[1024];
				int length;

				while ((length = dataInputStream.read(buffer)) > 0) {
					fileOutputStream.write(buffer, 0, length);
				}

				image.createNewFile();
				dataInputStream.close();
				fileOutputStream.close();
				System.out.println("图片生成成功...");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.out.println("url地址错误...");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("IO异常...");
			}
		}
	}

	/**
	 * 给图片加水印
	 * 
	 * @param srcImgPath
	 *            需要处理图片路径
	 * @param outImgPath
	 *            图片保存路径
	 * @param locationX
	 *            水印x坐标
	 * @param locationY
	 *            水印y坐标
	 * @param content
	 *            水印内容
	 * @param font
	 *            水印字体
	 * @param color
	 *            水印字体颜色
	 */
	public static void addWaterMark(String srcImgPath, String outImgPath, int locationX, int locationY, String content,
			Font font, Color color) {
		try {
			// 读取原图片信息
			File srcImgFile = new File(srcImgPath);
			Image srcImg = ImageIO.read(srcImgFile);
			int srcImgWidth = srcImg.getWidth(null);
			int srcImgHeight = srcImg.getHeight(null);
			String uuid = UUID.randomUUID().toString();
			// 加水印
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
			// 根据图片的背景设置水印颜色
			g.setColor(color);
			g.setFont(font);
			g.drawString(content, locationX, locationY);
			g.dispose();
			// 输出图片
			File newImage = new File(outImgPath + uuid + ".jpg");
			FileOutputStream outImgStream = new FileOutputStream(newImage);
			ImageIO.write(bufImg, "jpg", outImgStream);
			newImage.createNewFile();
			outImgStream.flush();
			outImgStream.close();
			System.out.println("添加水印成功...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("添加水印失败...");
		}
	}

	/**
	 * 获取水印文字总长度
	 * 
	 * @param waterMarkContent
	 *            水印的文字
	 * @param g
	 * @return 水印文字总长度
	 */
	public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
		return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
	}
}
