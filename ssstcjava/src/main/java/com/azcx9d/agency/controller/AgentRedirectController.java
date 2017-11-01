package com.azcx9d.agency.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.azcx9d.agency.base.HQBaseController;

@RequestMapping("/agApi/redirect")
@RestController
@ApiIgnore
public class AgentRedirectController extends HQBaseController{
	
	@RequestMapping("/noHeader")
    public Object redrectNoHeader(){
        return super.errorMsg(1,"账号身份已过期，请重新登录");
    }

    @RequestMapping("/tokenErr")
    public Object redrectTokenErr(){
        return super.errorMsg(1,"账号身份已过期，请重新登录");
    }

}
