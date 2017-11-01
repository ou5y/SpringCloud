package com.customer.handle;

import com.customer.exception.CustomerException;
import com.customer.util.JsonResult;
import com.customer.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by fangbaoyan on 2017/5/12.
 */
@RestControllerAdvice
public class CustomerExceptionHandle {
    private Logger logger = LoggerFactory.getLogger(CustomerExceptionHandle.class);

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public JsonResult handle(Exception e) {
        if (e instanceof CustomerException) {
            CustomerException commonException = (CustomerException) e;
            logger.error(commonException.getMessage());
            return ResultUtil.error(commonException.getCode(),commonException.getMessage() );
        } else {
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResultUtil.error(2, "服务器出错了,请稍后再试试~~");
        }
    }
}
