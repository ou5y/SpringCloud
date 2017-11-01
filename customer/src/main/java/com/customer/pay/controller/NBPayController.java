package com.customer.pay.controller;


import com.alibaba.fastjson.JSONObject;
import com.customer.pay.service.NbpayService;
import com.customer.web.BaseController;
import com.customer.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * NB钱包支付接口
 * Created by HuangQing on 2017/5/20 0020 09:59.
 */
@RestController
@RequestMapping("/v1/pay")
@Api(value = "钱包", description = "支付接口")
public class NBPayController extends BaseController {


    @Autowired
    private NbpayService nbpayService;

    /**
     * 请求进入nb钱包
     *
     * @return 钱包页面html字符串
     * @throws Exception
     */
    @RequestMapping(value = "/getIntoWallet", method = RequestMethod.GET)
    @ApiOperation(notes = "进入钱包", value = "进入钱包")
    public JsonResult getIntoWallet(@RequestHeader String token) {

        String userId = request.getAttribute("userId") + "";

        String result = nbpayService.getIntoWallet(userId);

        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);

        return new JsonResult(jsonObject.getString("data"));

    }

}
