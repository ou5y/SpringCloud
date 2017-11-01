package com.azcx9d.pay.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
public class ExternalIpAddressFetcher {

    // 外网IP提供者的网址
    private static final String externalIpProviderUrl = "http://www.nirsoft.net/show_my_ip_address.php";

    /**
     * 从外网提供者处获得包含本机外网地址的字符串
     * 从http://checkip.dyndns.org返回的字符串如下
     * <html><head><title>Current IP Check</title></head><body>Current IP Address: 123.147.226.222</body></html>
     * @param externalIpProviderUrl
     * @return String
     */
    private static String fetchExternalIpProviderHTML(String externalIpProviderUrl) {
        // 输入流
        InputStream in = null;

        // 到外网提供者的Http连接
        HttpURLConnection httpConn = null;

        try {
            // 打开连接
            URL url = new URL(externalIpProviderUrl);
            httpConn = (HttpURLConnection) url.openConnection();

            // 连接设置
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");

            // 获取连接的输入流
            in = httpConn.getInputStream();
            byte[] bytes=new byte[1024];// 此大小可根据实际情况调整

            // 读取到数组中
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead=in.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }

            // 将字节转化为为UTF-8的字符串
            String receivedString=new String(bytes,"UTF-8");

            // 返回
            return receivedString;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                httpConn.disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // 出现异常则返回空
        return null;
    }

    /**
     * 使用正则表达式解析返回的HTML文本,得到本机外网地址
     * @param html
     * @return ip
     */
    private static String parse(String html){
        String myExternalIpAddress = null;              //本机外网地址
        Pattern pattern=Pattern.compile("(\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})", Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(html);
        while(matcher.find()){
            myExternalIpAddress = matcher.group(0);
        }
        return myExternalIpAddress;
    }

    /**
     * 得到本机外网地址,得不到则为空
     * @return String 成功返回外网地址否则返回null
     */
    public static String myExternalIpAddress() {
        String returnedhtml = fetchExternalIpProviderHTML(externalIpProviderUrl);
        return parse(returnedhtml);
    }

}
