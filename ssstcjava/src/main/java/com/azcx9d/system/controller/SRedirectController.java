package com.azcx9d.system.controller;

import com.azcx9d.business.base.HQBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by HuangQing on 2017/3/29 0029.
 */
@RestController
@RequestMapping("/sApi/redirect")
@ApiIgnore
public class SRedirectController extends HQBaseController {

    @RequestMapping("/noHeader")
    public Object redrectNoHeader(){
        return super.errorMsg(-1,"缺少token参数");
    }

    @RequestMapping("/tokenErr")
    public Object redrectTokenErr(){
        return super.errorMsg(-2,"token验证失败或已经失效重新登录");
    }
}
