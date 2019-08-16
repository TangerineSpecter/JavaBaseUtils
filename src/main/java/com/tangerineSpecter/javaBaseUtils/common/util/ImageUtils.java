package com.tangerinespecter.javabaseutils.common.util;

import com.tangerinespecter.javabaseutils.common.annotation.ClassInfo;
import com.tangerinespecter.javabaseutils.common.annotation.MethodInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

/**
 * 图片处理工具类
 *
 * @author TangerineSpecter
 */
@Slf4j
@ClassInfo(Name = "图片处理工具类")
public class ImageUtils {

    /**
     * jpg文件
     */
    private static final String JPG_FILE = "jpg";
    /**
     * png文件
     */
    private static final String PNG_FILE = "png";
    /**
     * jpeg文件
     */
    private static final String JPEG_FILE = "jpeg";

    /**
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地
     *
     * @param urlList url列表
     * @return
     * @throws Exception
     */
    @MethodInfo(Name = "将Url图片下载到本地", paramInfo = {"url列表"})
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
                log.info("【图片生成成功...】");
            } catch (MalformedURLException e) {
                log.error(String.format("【下载图片url地址错误】：%s", e));
                e.printStackTrace();
            } catch (IOException e) {
                log.error(String.format("【下载图片IO异常】：%s", e));
                e.printStackTrace();
            }
        }
    }

    /**
     * 传入要下载的图片的url以及保存地址，将图片下载到本地
     *
     * @param imageUrl  图片地址
     * @param imagePath 图片保存路径
     */
    @MethodInfo(Name = "将Url图片下载到本地", paramInfo = {"url地址", "保存路径"})
    public static void downloadPicture(String imageUrl, String imagePath) {
        String fileName = imageUrl;

        try {
            // 创建文件目录
            File files = new File(imagePath);
            // 判断是否存在文件夹
            if (!files.exists()) {
                files.mkdirs();
            }
            // 获取下载地址
            URL url = new URL(imageUrl);
            // 连接网络地址
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            // 获取连接的输出流
            InputStream is = huc.getInputStream();
            // 创建文件
            File file = new File(imagePath + fileName);
            // 创建输入流，写入文件
            FileOutputStream out = null;
            if (file.getName().endsWith(JPG_FILE) || file.getName().endsWith(PNG_FILE) || file.getName().endsWith(JPEG_FILE)
                    || file.getName().endsWith(JPG_FILE)) {
                out = new FileOutputStream(file);
                int i = 0;
                while ((i = is.read()) != -1) {
                    out.write(i);
                }
                is.close();
                out.close();
            }

        } catch (Exception e) {
            log.error(String.format("【下载图片异常】：%s", e));
            e.printStackTrace();
        }
    }

    /**
     * 给图片加水印
     *
     * @param srcImgPath 需要处理图片路径
     * @param outImgPath 图片保存路径
     * @param locationX  水印x坐标
     * @param locationY  水印y坐标
     * @param content    水印内容
     * @param font       水印字体
     * @param color      水印字体颜色
     */
    @MethodInfo(Name = "给图片加水印", paramInfo = {"需要处理的图片路径", "图片保存路径", "水印x坐标", "水印y坐标", "水印内容", "水印字体", "水印字体颜色"})
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
            ImageIO.write(bufImg, JPG_FILE, outImgStream);
            newImage.createNewFile();
            outImgStream.flush();
            outImgStream.close();
            log.info("【图片添加水印成功...】");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(String.format("【图片添加水印失败】：%s", e));
        }
    }

    /**
     * 获取水印文字总长度
     *
     * @param waterMarkContent 水印的文字
     * @param g
     * @return 水印文字总长度
     */
    @MethodInfo(Name = "获取水印文字总长度", paramInfo = {"水印文字", "Graphics2D类"}, returnInfo = "水印文字总长度")
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    /**
     * @param imagePath 图片的绝对路径地址
     * @return
     * @description 获取图片的二进制数据
     */
    @MethodInfo(Name = "获取图片的二进制数据", paramInfo = {"图片的绝对路径地址"}, returnInfo = "二进制数据")
    public static byte[] getPicData(String imagePath) {
        byte[] data = null;
        try {
            FileInputStream fi = new FileInputStream(imagePath);
            int length = fi.available();
            data = new byte[length];
            fi.read(data);
            fi.close();
        } catch (Exception e) {
            log.error(String.format("【获取图片数据异常】：%s", e));
            System.out.println(e);
        }
        return data;
    }

    /**
     * 读取文件并压缩数据然后转Base64编码
     *
     * @param imagePath 图片的绝对路径地址
     * @return
     */
    @MethodInfo(Name = "读取文件压缩后转Base64编码", paramInfo = {"图片的绝对路径地址"}, returnInfo = "Base64编码")
    public static String base64(String imagePath) {
        byte[] data = getPicData(imagePath);
        if (data == null) {
            return null;
        }
        byte[] zipData = ZipUtils.gZip(data);
        return Base64.encodeBase64String(zipData);
    }

    /**
     * 获取网页所有图片并下载
     *
     * @param url      网页地址
     * @param encoding 网页编码
     * @param path     存放图片地址
     */
    @MethodInfo(Name = "获取网页所有图片并下载", paramInfo = {"网页地址", "网页编码", "存放路径"})
    public static void getWebImage(String url, String encoding, String path) {
        String htmlResouce = getHtmlResourceByUrl(url, encoding);
        // 解析网页源代码
        Document document = Jsoup.parse(htmlResouce);
        // 获取所以图片的地址<img src="" alt= "" width= "" height=""/>
        Elements elements = document.getElementsByTag("img");
        for (Element element : elements) {
            String imgSrc = element.attr("src");
            if (!"".equals(imgSrc)) {
                if (imgSrc.startsWith("http://")) {
                    downloadPicture(path, imgSrc);
                } else {
                    imgSrc = "http:" + imgSrc;
                    downloadPicture(path, imgSrc);
                }
            }
        }
    }

    /**
     * 根据网站的地址和页面的编码集来获取网页的源代码
     *
     * @param url      网址路径
     * @param encoding 编码集
     * @return String 网页的源代码
     */
    @MethodInfo(Name = "获取网页源代码", paramInfo = {"网页地址", "编码集"}, returnInfo = "源代码")
    public static String getHtmlResourceByUrl(String url, String encoding) {
        // 用于存储网页源代码
        StringBuffer buf = new StringBuffer();
        URL urlObj = null;
        URLConnection uc = null;
        InputStreamReader isr = null;
        BufferedReader buffer = null;
        try {
            // 建立网络连接
            urlObj = new URL(url);
            // 打开网络连接
            uc = urlObj.openConnection();
            // 将连接网络的输入流转换
            isr = new InputStreamReader(uc.getInputStream(), encoding);
            // 建立缓冲写入流
            buffer = new BufferedReader(isr);
            String line = null;
            while ((line = buffer.readLine()) != null) {
                buf.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (Exception e) {
                log.error(String.format("【获取网页源代码异常】：%s", e));
                e.printStackTrace();
            }
        }
        return buf.toString();
    }
}
