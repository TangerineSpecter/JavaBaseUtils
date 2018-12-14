package common.util;

import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import common.annotation.ClassInfo;
import common.annotation.MethodInfo;

/**
 * Http工具类
 * 
 * @author TangerineSpecter
 *
 */
@SuppressWarnings("deprecation")
@ClassInfo(Name = "Http工具类")
public class HttpUtils {

	private static Logger logger = Logger.getLogger(HttpUtils.class);
	/** 编码格式 */
	public static final String CHARSET = "UTF-8";

	private static CloseableHttpClient httpClient = null;
	
	/** 允许管理器限制最大连接数 ，还允许每个路由器针对某个主机限制最大连接数。 */
	//public static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	public static PoolingHttpClientConnectionManager cm ;
	/**
	 * 最大连接数
	 */
	public final static int MAX_TOTAL_CONNECTIONS = 500;
	/**
	 * 每个路由最大连接数 访问每个目标机器 算一个路由 默认 2个
	 */
	public final static int MAX_ROUTE_CONNECTIONS = 80;
	
	static {
		SSLContext sslContext = null;
		try {
			sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// 信任所有
					return true;
				}
			}).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		//忽略hostname的比较
		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
				new AllowAllHostnameVerifier());
		//设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> registy = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)  
				.register("https", sslConnectionSocketFactory).build();
		
		cm = new PoolingHttpClientConnectionManager(registy);
		cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);// 设置最大路由数
		cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);// 最大连接数

		/**
		 * 大量的构造器设计模式，很多的配置都不建议直接new出来，而且相关的API也有所改动，例如连接参数，
		 * 以前是直接new出HttpConnectionParams对象后通过set方法逐一设置属性， 现在有了构造器，可以通过如下方式进行构造：
		 * SocketConfig.custom().setSoTimeout(100000).build();
		 */
		SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
		cm.setDefaultSocketConfig(socketConfig);
		RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH)
				.setExpectContinueEnabled(true).setStaleConnectionCheckEnabled(true).setRedirectsEnabled(true).build();
		// CodingErrorAction.IGNORE指示通过删除错误输入、向输出缓冲区追加 coder
		// 的替换值和恢复编码操作来处理编码错误的操作。
		ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Consts.UTF_8)
				.setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE)
				.build();
		httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(defaultRequestConfig)
				.setDefaultConnectionConfig(connectionConfig).build();
	}

	@MethodInfo(Name = "post请求", paramInfo = { "请求地址", "请求参数", "请求头" }, returnInfo = "请求结果")
	public static String sendPost(String url, Map<String, String> params, Map<String, String> header) throws Exception {
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		if (header != null) {
			Set<Entry<String, String>> entrySet = header.entrySet();
			for (Entry<String, String> entry : entrySet) {
				post.setHeader(entry.getKey(), entry.getValue());
			}
		}
		if (params != null) {
			logger.info("请求接口参数:" + JSON.toJSONString(params));
			List<NameValuePair> nameValuePairs = new ArrayList<>();
			for (Entry<String, String> entry : params.entrySet()) {
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, CHARSET));
		}

		HttpResponse response = httpClient.execute(post);
		HttpEntity entity = null;
		try {
			entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, CHARSET);
				logger.info("result = " + result);
				return result;
			}
		} finally {
			if (entity != null) {
				entity.getContent().close();
			}
		}
		return null;
	}
}
