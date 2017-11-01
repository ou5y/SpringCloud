package com.customer.util;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
public class JSONStringUtil {

    private static final SerializerFeature[] features = {SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullStringAsEmpty};

    public static String toJSONString(Object object) {
        SerializeWriter out = new SerializeWriter();
        String s;
        JSONSerializer serializer = new JSONSerializer(out);
        SerializerFeature arr$[] = features;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            SerializerFeature feature = arr$[i$];
            serializer.config(feature, true);
        }

        serializer.getValueFilters().add(new ValueFilter() {
            public Object process(Object obj, String s, Object value) {
                if(null!=value) {
//                    if(value instanceof java.util.Date) {
//                        return String.format("%1$tF %1tT", value);
//                    }
                    return value;
                }else {
                    return "";
                }
            }
        });
        serializer.write(object);
        s = out.toString();
        out.close();
        return s;
    }

}
