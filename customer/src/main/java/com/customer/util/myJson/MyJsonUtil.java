package com.customer.util.myJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.customer.util.JSONStringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chenxl on 2017/7/8 0008.
 */
public class MyJsonUtil {

    private static JSONArray jsonContext = null;

    public static JSONArray getJsonContext() {
        if (null == jsonContext) {
            jsonContext = JSON.parseArray(ReadFile("src/main/java/com/customer/util/myJson/city_json.txt"));
        }
        return jsonContext;
    }

    public static String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        String jsonContext = MyJsonUtil.ReadFile("src/main/java/com/customer/util/myJson/city_json.txt");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(getJsonContext());
    }
}
