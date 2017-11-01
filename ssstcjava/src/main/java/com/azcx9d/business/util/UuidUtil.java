package com.azcx9d.business.util;

import java.util.UUID;

/**
 * Created by HuangQing on 2017/4/3 0003 14:27.
 */
public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}

