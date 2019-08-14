package com.tangerineSpecter.javaBaseUtils.common.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 初始化
 * 
 * @author TangerineSpecter
 *
 */
public class InitProperties {

	public static void init() {
		try {
			Properties properties  = new Properties();
			// 使用ClassLoader加载properties配置文件生成对应的输入流
			InputStream in = InitProperties.class.getClassLoader().getResourceAsStream("common.properties");
			// 使用properties对象加载输入流
			properties.load(in);
			//获取key对应的value值
			String value = properties.getProperty("version");
			System.out.println(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
