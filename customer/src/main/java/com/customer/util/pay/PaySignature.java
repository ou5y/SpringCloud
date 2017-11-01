package com.customer.util.pay;

import com.customer.exception.ApiException;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class PaySignature {
    /**
     * 排序
     */
    public static String getSignContent(Map<String, String> params) {
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        // 遍历排序后的字典，将所有参数按"key=value&"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        int i=0;
        for (Map.Entry<String, String> param : entrys) {
            if(!StringUtils.isEmpty(param.getValue())){
                if(i!=0){basestring.append("&");}
                basestring.append(param.getKey()).append("=").append(param.getValue());
                i++;
            }
        }
        return basestring.toString();
    }

    /**
     *  rsa内容签名
     * 
     * @param content
     * @param privateKey
     * @param charset
     * @return
     * @throws ApiException
     */
    public static String rsaSign(String content, String privateKey, String charset,
                                 String signType) throws ApiException {

        if (PayConstants.SIGN_TYPE_RSA.equals(signType)) {

            return rsaSign(content, privateKey, charset);
        } else if (PayConstants.SIGN_TYPE_RSA2.equals(signType)) {

            return rsa256Sign(content, privateKey, charset);
        } else {

            throw new ApiException("Sign Type is Not Support : signType=" + signType);
        }

    }

    /**
     * sha256WithRsa 加签
     * 
     * @param content
     * @param privateKey
     * @param charset
     * @return
     * @throws ApiException
     */
    public static String rsa256Sign(String content, String privateKey,
                                    String charset) throws ApiException {

        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(PayConstants.SIGN_TYPE_RSA,
                new ByteArrayInputStream(privateKey.getBytes()));

            java.security.Signature signature = java.security.Signature
                .getInstance(PayConstants.SIGN_SHA256RSA_ALGORITHMS);

            signature.initSign(priKey);

            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            byte[] signed = signature.sign();

            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {
            throw new ApiException("RSAcontent = " + content + "; charset = " + charset, e);
        }

    }

    /**
     * sha1WithRsa 加签
     */
    public static String rsaSign(String content, String privateKey,
                                 String charset) throws ApiException {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(PayConstants.SIGN_TYPE_RSA,
                new ByteArrayInputStream(privateKey.getBytes()));

            java.security.Signature signature = java.security.Signature
                .getInstance(PayConstants.SIGN_ALGORITHMS);

            signature.initSign(priKey);

            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            byte[] signed = signature.sign();

            return new String(Base64.encodeBase64(signed));
        } catch (InvalidKeySpecException ie) {
            throw new ApiException("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥", ie);
        } catch (Exception e) {
            throw new ApiException("RSAcontent = " + content + "; charset = " + charset, e);
        }
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm,InputStream ins) throws Exception {
        if (ins == null || StringUtils.isEmpty(algorithm)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] encodedKey = StreamUtil.readText(ins).getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }
}
