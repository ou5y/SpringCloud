package com.azcx9d.consumer.controller;

import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by chenxl on 2017/3/30 0030.
 */
@RequestMapping("/cApi/redirect")
@RestController
@ApiIgnore
public class CRedirectController extends BaseController {

    @RequestMapping("/noHeader")
    public Object redrectNoHeader(){
        return new JsonResult(-1,"账号身份已过期，请重新登录");
    }

    @RequestMapping("/tokenErr")
    public Object redrectTokenErr(){
        return new JsonResult(-1,"账号身份已过期，请重新登录");
    }
}
