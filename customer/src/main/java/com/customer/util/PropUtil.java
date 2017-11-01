package com.customer.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropUtil {
    public static Properties prop = new Properties();

    public PropUtil(String path) {
        try {
            InputStream inputStream = PropUtil.class.getClassLoader().getResourceAsStream(path);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            prop.load(bf);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public String get(String key) {
        return prop.getProperty(key, "");
    }
}
