package com.tangerinespecter.javabaseutils.common.util;

import com.tangerineSpecter.javaBaseUtils.common.annotation.ClassInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Http工具类
 *
 * @author TangerineSpecter
 */
@Slf4j
@ClassInfo(Name = "Http工具类")
public class HttpUtils {

    private static final String DEF_CHATSET = "UTF-8";
    private static final int DEF_CONN_TIMEOUT = 30000;
    private static final int DEF_READ_TIMEOUT = 30000;
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    /**
     * 调用对方接口方法
     *
     * @param strUrl 对方或第三方提供的路径
     * @param params 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     * @param method 网络请求字符串
     */
    public static String interfaceInvoke(String strUrl, Map<String, Object> params, String method) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String result = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            // 打开和url之间的连接
            conn = (HttpURLConnection) url.openConnection();
            // 请求方式
            if (method == null || method.toUpperCase().equals("GET")) {
                conn.setRequestMethod("GET");
            } else if (method == null || method.toUpperCase().equals("POST")) {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            } else {
                log.error(String.format("[接口请求方法错误]:{}", method));
                return result;
            }
            // //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", userAgent);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            // 设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            // 最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            // post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    // 获取URLConnection对象对应的输出流
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    // 发送请求参数即数据
                    out.writeBytes(urlencode(params));
                    // 缓冲数据
                    out.flush();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            // 获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            // 构造一个字符流缓存
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            result = sb.toString();
            // 关闭流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[接口调用失败]:", e);
            throw new RuntimeException();
        } finally {
            if (conn != null) {
                // 断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
                // 固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
                conn.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // 将map型转为请求参数型
    @SuppressWarnings("rawtypes")
    private static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
