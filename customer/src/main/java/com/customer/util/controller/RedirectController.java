package com.customer.util.controller;


import com.customer.util.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by HuangQing on 2017/3/29 0029.
 */
@RestController
@RequestMapping("/api/redirect")
@ApiIgnore
public class RedirectController  {

    @RequestMapping("/nullToken")
    public JsonResult redrectNoHeader(){
         return new JsonResult<>(-1,"token过期");
         //new ResponseEntity<JsonResult>(result,HttpStatus.FORBIDDEN);
    }

    @RequestMapping("/unmatchedToken")
    public JsonResult redrectTokenErr(){
        return new JsonResult<>(-1,"token过期");
        //return new ResponseEntity<JsonResult>(result,HttpStatus.FORBIDDEN);
    }
}
