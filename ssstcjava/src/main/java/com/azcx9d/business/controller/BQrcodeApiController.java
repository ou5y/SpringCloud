package com.azcx9d.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.common.util.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletOutputStream;

/**
 * Created by chenxl on 2017/4/5 0005.
 */
@Api(value = "商户端二维码",description="商户端二维码")
@RestController
@RequestMapping("/v1/bApi/QRCode")
@ApiIgnore
public class BQrcodeApiController extends HQBaseController {
    @ApiOperation(value = "获取收款码",notes = "获取收款码<br/>返回的是图片流")
    @RequestMapping(value = "/getPayCode",method = RequestMethod.GET)
    public void getPayCode(@RequestHeader("token") String token) {
        ServletOutputStream stream = null;
        try {
            JSONObject json = new JSONObject();
            json.put("userId", request.getAttribute("userId"));
            String content = json.toJSONString();
            int height = 256;//二维码的高度
            int width = 256;//二维码的宽度
            stream = response.getOutputStream();
            QRCodeUtil.getQRCode(content, stream, height, width);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (stream != null) {
                try {
                    stream.flush();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                try {
                    stream.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
