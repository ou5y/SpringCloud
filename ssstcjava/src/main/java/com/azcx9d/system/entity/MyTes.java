package com.azcx9d.system.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by HuangQing on 2017/4/6 0006 17:47.
 */
public class MyTes {

    public static void main(String[] args) {
        double d1 = 0.05;
        double d2 = 0.41;
        BigDecimal d3 = new BigDecimal("1000000.5");
        BigDecimal d4 = new BigDecimal(2);
        System.out.println(d1+d2);

        BigDecimal divide = d3.add(d4);
        System.out.println(divide);
    }
}
