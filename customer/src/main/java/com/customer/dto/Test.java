package com.customer.dto;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class Test {
    public static void main(String[] args) {
        List<Map<String,Object>> trades = new ArrayList<Map<String,Object>>(0);
        Map<String,Object> m1 = new HashMap<String,Object>(0);
        m1.put("id",1);
        m1.put("name","装修装饰");

        Map<String,Object> m2 = new HashMap<String,Object>(0);
        m2.put("id",2);
        m2.put("name","建材");

        Map<String,Object> m3 = new HashMap<String,Object>(0);
        m3.put("id",3);
        m3.put("name","婚庆服务");

        Map<String,Object> m4 = new HashMap<String,Object>(0);
        m4.put("id",4);
        m4.put("name","珠宝首饰");

        Map<String,Object> m5 = new HashMap<String,Object>(0);
        m5.put("id",5);
        m5.put("name","社会保障服务");

        Map<String,Object> m6 = new HashMap<String,Object>(0);
        m6.put("id",6);
        m6.put("name","汽车机车销售行业");

        Map<String,Object> m7 = new HashMap<String,Object>(0);
        m7.put("id",7);
        m7.put("name","汽车服务");

        Map<String,Object> m8 = new HashMap<String,Object>(0);
        m8.put("id",8);
        m8.put("name","美容美发");

        Map<String,Object> m9 = new HashMap<String,Object>(0);
        m9.put("id",9);
        m9.put("name","化妆洗护");

        Map<String,Object> m10 = new HashMap<String,Object>(0);
        m10.put("id",10);
        m10.put("name","房产");

        Map<String,Object> m11 = new HashMap<String,Object>(0);
        m11.put("id",11);
        m11.put("name","经纪代理");

        Map<String,Object> m12 = new HashMap<String,Object>(0);
        m12.put("id",12);
        m12.put("name","休闲娱乐");

        Map<String,Object> m13 = new HashMap<String,Object>(0);
        m13.put("id",13);
        m13.put("name","旅游机票");

        Map<String,Object> m14 = new HashMap<String,Object>(0);
        m14.put("id",14);
        m14.put("name","酒店");

        Map<String,Object> m15 = new HashMap<String,Object>(0);
        m1.put("id",15);
        m1.put("name","餐饮");

        Map<String,Object> m16 = new HashMap<String,Object>(0);
        m16.put("id",16);
        m16.put("name","糖酒茶");

        Map<String,Object> m17 = new HashMap<String,Object>(0);
        m17.put("id",17);
        m17.put("name","数码电子");

        Map<String,Object> m18 = new HashMap<String,Object>(0);
        m18.put("id",18);
        m18.put("name","家具");

        Map<String,Object> m19 = new HashMap<String,Object>(0);
        m19.put("id",19);
        m19.put("name","家电");

        Map<String,Object> m20 = new HashMap<String,Object>(0);
        m20.put("id",20);
        m20.put("name","培训行业");

        Map<String,Object> m21 = new HashMap<String,Object>(0);
        m21.put("id",21);
        m21.put("name","服装服饰");

        Map<String,Object> m22 = new HashMap<String,Object>(0);
        m22.put("id",22);
        m22.put("name","鞋帽箱包");

        Map<String,Object> m23 = new HashMap<String,Object>(0);
        m23.put("id",23);
        m23.put("name","养生保健品");

        Map<String,Object> m24 = new HashMap<String,Object>(0);
        m24.put("id",24);
        m24.put("name","生鲜");

        Map<String,Object> m25 = new HashMap<String,Object>(0);
        m25.put("id",25);
        m25.put("name","水果");

        Map<String,Object> m26 = new HashMap<String,Object>(0);
        m26.put("id",26);
        m26.put("name","蔬菜");

        Map<String,Object> m27 = new HashMap<String,Object>(0);
        m27.put("id",27);
        m27.put("name","农副用品");

        Map<String,Object> m28 = new HashMap<String,Object>(0);
        m28.put("id",28);
        m28.put("name","广告媒体");

        trades.add(m1);
        trades.add(m2);
        trades.add(m3);
        trades.add(m4);
        trades.add(m5);
        trades.add(m6);
        trades.add(m7);
        trades.add(m8);
        trades.add(m9);
        trades.add(m10);
        trades.add(m11);
        trades.add(m12);
        trades.add(m13);
        trades.add(m14);
        trades.add(m15);
        trades.add(m16);
        trades.add(m17);
        trades.add(m18);
        trades.add(m19);
        trades.add(m20);
        trades.add(m21);
        trades.add(m22);
        trades.add(m23);
        trades.add(m24);
        trades.add(m25);
        trades.add(m26);
        trades.add(m27);
        trades.add(m28);

        System.out.println(JSON.toJSONString(trades));
    }
}
