package common.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * 图片处理工具类
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
				String imageName = Constant.FILE_SAVE_PATH + uuid + ".jpg";
				File image = new File(imageName);
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
