package com.customer.util;


import java.util.Random;

/**
 * 项目名称： ssstc
 * 类名称：RandomUtil.java
 * 类描述：获取随机数
 * 创建时间：2017-4-9 下午11:02:26
 * @version V1.0
 */
public class RandomUtil {
	
	/**
	 * 获取指定长度的纯数字字符串
	 * @param length  字符串长度
	 * @return String
	 */
	public static String getRandomNum(int length){
		String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
	}
	
	/**
	 * 在一个字符串中截取指定长度字符串
	 * @param base  	指定字符串
	 * @param length	字符串长度
	 * @return	String
	 */
	public static String getRandomString(String base,int length){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
	}
	
	/**
	 * 生成指定长度随机字符串
	 * @param length
	 * @return String
	 */
	public static String getRandomString(int length) {
		String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
	}
	
	// 获取指定长度日期格式的流水号
	public static String uniqeSerialNumber(int length){
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}
	
	/**
	 * 获取32位唯一字符串
	 * @return
	 */
//	public static String get32UUID(){
//        return UuidUtil.get32UUID();
//    }

}
