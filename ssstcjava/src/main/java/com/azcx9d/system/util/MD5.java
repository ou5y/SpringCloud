package com.azcx9d.system.util;

import java.security.MessageDigest;

/**
 * Created by HuangQing on 2017/4/7 0007 19:44
 */
public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	public static void main(String[] args) {
//		System.out.println(md5("zaq12wsx"));
//		System.out.println(md5("zhangxu120"));
//		System.out.println(md5("lx1991121070642"));
//		System.out.println(md5("lxl717lxl"));
		System.out.println(md5("123456"));
	}
}
