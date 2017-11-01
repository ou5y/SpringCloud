package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by HuangQing on 2017/3/29 0029.
 */
@RestController
@RequestMapping("/bApi/redirect")
@ApiIgnore
public class RedirectController extends HQBaseController {

    @RequestMapping("/noHeader")
    public Object redrectNoHeader(){
        return super.errorMsg(-1,"账号身份已过期，请重新登录");
    }

    @RequestMapping("/tokenErr")
    public Object redrectTokenErr(){
        return super.errorMsg(-1,"账号身份已过期，请重新登录");
    }
}
