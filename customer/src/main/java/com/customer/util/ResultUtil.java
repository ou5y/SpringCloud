package com.customer.util;

import com.customer.util.JsonResult;

/**
 * Created by fangbaoyan on 2017/5/12.
 */
public class ResultUtil {
    public static JsonResult success(Object object){
        JsonResult result= new JsonResult(object);
        return result;
    }

    public static JsonResult success(){

        return success(null);
    }

    public static JsonResult error(Integer code,String message){
        JsonResult result= new JsonResult(code,message);
        return result;
    }
}
