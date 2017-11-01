package com.azcx9d.pay.util.tfb.union;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

public class RequestUtils {

    public static TreeMap<String, String> Dom2Map(String xml) throws DocumentException {
        Document doc = DocumentHelper.parseText(xml);
        TreeMap<String, String> map = new TreeMap<String, String>();
        if (doc == null)
            return map;
        Element root = doc.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
            Element e = (Element) iterator.next();
            List list = e.elements();
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    /**
     * 验签
     *
     * @param source
     *            签名内容
     * @param sign
     *            签名值
     * @return
     */
    public static boolean verify(String source, String sign) {
        //MD5验签，把返回的报文串（去掉sign和空串），作MD5加签，然后跟sign 比对
        return (sign(source).equals(sign)) ? true:false;

    }

    /**
     * 天下付支付宝把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
     */
    public static String getParamSrc(Map<String, String> paramsMap) {
        StringBuffer paramstr = new StringBuffer();
        for (String pkey : paramsMap.keySet()) {
            String pvalue = paramsMap.get(pkey);
            if (null != pvalue && "" != pvalue && !pkey.equals("sign") && !pkey.equals("retcode")
                    && !pkey.equals("retmsg") && !pkey.equals("sign_type")) {// 空值不传递，不签名
                paramstr.append(pkey + "=" + pvalue + "&"); // 签名原串，不url编码
            }
        }
        // 去掉最后一个&
        String result = paramstr.substring(0, paramstr.length() - 1);
        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuffer paramstr = new StringBuffer();

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (null != value && "" != value && !key.equals("sign") && !key.equals("retcode")
                    && !key.equals("retmsg") && !key.equals("sign_type")) {// 空值不传递，不签名
                paramstr.append(key + "=" + value + "&"); // 签名原串，不url编码
            }
        }
        // 去掉最后一个&
        String prestr = paramstr.substring(0, paramstr.length() - 1);
        return prestr;
    }

    /**
     * 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
     */
    public static String getParamSrc(TreeMap<String, String> paramsMap) {
        StringBuffer paramstr = new StringBuffer();
        for (String pkey : paramsMap.keySet()) {
            String pvalue = paramsMap.get(pkey);
            if (null != pvalue && "" != pvalue) {// 空值不传递，不签名
                paramstr.append(pkey + "=" + pvalue + "&"); // 签名原串，不url编码
            }
        }
        // 去掉最后一个&
        String result = paramstr.substring(0, paramstr.length() - 1);
        System.out.println("签名原串：" + result);
        return result;
    }

    /**
     * 分解解密后的字符串，保存为map
     */
    public static HashMap<String, String> parseCipherResponseData(String responseData) {
        HashMap<String, String> map = new HashMap<String, String>();
        String[] s1 = responseData.split("&");
        String[] s2 = new String[2];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s1.length; i++) {
            s2 = s1[i].split("=", 2);
            map.put(s2[0], s2[1]);
            if (!s2[0].equals("sign")) {
                sb.append(s2[0] + "=" + s2[1] + "&");
            }
        }
        String source = sb.substring(0, sb.length() - 1);
        map.put("source", source);
        return map;
    }

    /**
     * xml转换为Map
     * @param xml
     * @return
     */
    public static Map<String,String> resolveXml(String xml) {

        Map<String,String> map = new HashMap<String,String>(0);
        try {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            //获取到document里面的全部结点
            List<Element> elements = root.elements();
            for (Iterator<Element> it = elements.iterator(); it.hasNext();) {
                Element element = it.next();
                map.put(element.getName(), element.getTextTrim());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            map = null;
        }
        return map;
    }

    /**
     * 解析xml
     */
    public static String getXmlElement(String responseData, String element) {
        String result = null;

        try {
            Document dom = DocumentHelper.parseText(responseData);
            Element root = dom.getRootElement();
            result = root.element(element).getText();
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    /**
     * 发送http请求
     * 
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, String param,String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), charset));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("请求地址:" + url);
        System.out.println("请求结果:" + result);
        return result;
    }

    /**
     * 对原串进行签名
     *
     * @param paramSrc
     *            the source to be signed
     * @return
     */
    private static String sign(String paramSrc) {
        StringBuffer strbuff = new StringBuffer();
        strbuff.append(paramSrc + "&key=" + TFBConfig.MD5_KEY);
        String sign = null;
        try {
            sign = getMD5Str(strbuff.toString());
            System.out.println("签名:" + sign);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sign;
    }

    /**
     * MD5签名
     */
    public static String getMD5Str(String str) {
        return getMD5Str(str, "UTF-8");
    }
    /**
     * MD5签名
     */
    public static String getMD5Str(String str, String encode) {
        if (null == str) return null;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes(encode));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    /**
     * 发起请求
     *
     * @param url
     * @param param
     *            参数列表（按首字母进行排序）
     */
    public static String sendRequst(final String url, TreeMap<String, String> param,final String charset) {
        System.out.println("拼接签名原串-----------------------------------------");
        StringBuffer paramstr = new StringBuffer();
        for (String pkey : param.keySet()) {
            String pvalue = param.get(pkey);
            if (null != pvalue && "" != pvalue) {// 空值不传递，不签名
                paramstr.append(pkey + "=" + pvalue + "&"); // 签名原串，不url编码
            }
        }
        String paramSrc = paramstr.substring(0, paramstr.length() - 1);
        System.out.println("签名原串：" + paramSrc);

        System.out.println("生成签名--------------------------------------------");

        String sign = MD5Utils.sign(paramSrc,charset);

        System.out.println("rsa加密--------------------------------------------");
        paramstr.append("sign=" + sign);
        System.out.println("加密原串:" + paramstr);

        String cipherData = RSAUtils.encrypt(paramstr.toString(),charset);
        System.out.println("加密结果:" + cipherData);

        System.out.println("发起请求--------------------------------------------");

        String responseData = null;
        try {
            responseData = RequestUtils.doPost(url,
                    "cipher_data=" + URLEncoder.encode(cipherData, charset),charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            responseData = null;
        }
        return responseData;
    }

}
