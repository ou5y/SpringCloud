package com.azcx9d.business.controller;


import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.BaseController;
import com.azcx9d.pay.service.PayBusinessService;
import com.azcx9d.pay.util.ApiException;
import com.azcx9d.pay.util.Configs;
import com.azcx9d.pay.util.PaySignature;
import com.azcx9d.pay.util.RequestParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by fangbaoyan on 2017/4/18.
 */

@RestController
@RequestMapping("/v1/bApi/payBusiness")
@Api(value = "商户支付", description = "商户支付")
public class PayBusinessContorller extends BaseController {

    static {
        Configs.init("nbpayinfo.properties");
    }

    @Autowired
    private PayBusinessService payBusinessService;

    @ApiOperation(value = "商户入网")
    @RequestMapping(value = "/businessInNet", method = RequestMethod.POST)
    public JsonResult businessInNet() {

        JsonResult jsonResult = null;
        try {
            jsonResult = payBusinessService.businessInNet();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "商户入网异步通知", notes = "商户入网异步通知")
    @RequestMapping(value = "/businessInNetNotify", method = RequestMethod.POST)
    public void businessInNetNotify(
//                                          @ApiParam(value = "商户工商注册名称") @RequestParam String companyName,
//                                          @ApiParam(value = "营业执照编号") @RequestParam String licenceNo,
//                                          @ApiParam(value = "商户编号") @RequestParam String merchantNumber,
//                                          @ApiParam(value = "商户审核状态") @RequestParam String merchantCheckStatus,
//                                          @ApiParam(value = "商户启用状态") @RequestParam String merchantStatus,
//                                          @ApiParam(value = "审核说明") @RequestParam(required = false) String body,
            HttpServletRequest request, HttpServletResponse response) {

        PrintWriter out = null;
        String html = "";
        Map<String, String> params = RequestParamUtil.getAllRequestParam(request);//取出所有参数
        logger.info("收到参数如下：");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            logger.info(entry.getKey() + "： " + entry.getValue());
        }
        //收到异步通知后  首先把收到消息的报文写出。
        try {
            response.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            out = response.getWriter();

            //根据业务参数 要做防止通知的 重复送达
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sendDate = null;
            try {
                sendDate = df.parse(params.get("noticeTime"));
            } catch (ParseException e) {
            }

            long l = System.currentTimeMillis() - sendDate.getTime();
            if (l > 300000) {// 5分钟过期 5*60*1000
                logger.info("消息发送时间与 接收时间 ，间隔过大为异常状态，不做处理");
            }

            String signstr = params.get("sign");
            params.remove("sign");
            String content = PaySignature.getSignContent(params);

            boolean res = false;
            try {
                res = PaySignature.rsaCheck(content, signstr, Configs.getNbpayPublicKey(), "UTF-8", "RSA");
            } catch (ApiException e) {
                logger.error("验证签名异常");
            }
            if (!res) {
                logger.error("验证签名不通过");
                html = "FAIL";
            } else {
                if(null == params.get("body")){
                    params.put("body", "");
                }
                switch (params.get("merchantCheckStatus").toString()) {
                    case "1":
                        params.put("state", "3"); //1：审核中
                        break;
                    case "2":
                        params.put("state", "5"); //2：审核通过
                        break;
                    case "3":
                        params.put("state", "4"); //3：审核拒绝
                        break;
                    default:
                        params.put("state", "3");
                }
                JsonResult result = payBusinessService.businessInNetNotify(params);
                if (result.getstatus() == 0) {
                    html = "SUCCESS";//写出，成功
                } else {
                    html = "FAIL";//写出，失败
                }
                logger.info("裕顺商户入网异步通知响应结果：" + result + ",html:" + html);
            }
        } catch (Exception e) {
            e.printStackTrace();
            html = "FAIL";
        } finally {
            out.write(html);
            out.flush();
            if (out != null){
                out.close();
            }
        }
    }

    @ApiOperation(value = "商户信息修改")
    @RequestMapping(value = "/businessModify", method = RequestMethod.POST)
    public JsonResult businessModify(@ApiParam(value = "商户工商注册名称") @RequestParam String companyName,
                                     @ApiParam(value = "营业执照编号") @RequestParam String licenceNo,
                                     @ApiParam(value = "商户编号") @RequestParam String merchantNumber,
                                     @ApiParam(value = "银行代码") @RequestParam(required = false) String bankCode,
                                     @ApiParam(value = "开户银行") @RequestParam(required = false) String bankName,
                                     @ApiParam(value = "开户银行支行名称") @RequestParam(required = false) String bankBranch,
                                     @ApiParam(value = "开户许可证照片地址") @RequestParam(required = false) String bankCertLink,
                                     @ApiParam(value = "公司对公账户") @RequestParam(required = false) String bankAccountNo,
                                     @ApiParam(value = "公司对公账户名称") @RequestParam(required = false) String bankAccountName,
                                     @ApiParam(value = "联行号") @RequestParam(required = false) String lineNumber,
                                     @ApiParam(value = "开户银行所在城市，省") @RequestParam(required = false) String bankProvince,
                                     @ApiParam(value = "开户银行所在城市，市") @RequestParam(required = false) String bankCity,
                                     @ApiParam(value = "最小交易金额(元)") @RequestParam(required = false) String startVal,
                                     @ApiParam(value = "最大交易金额（元）") @RequestParam(required = false) String endVal,
                                     @ApiParam(value = "收费方式") @RequestParam(required = false) String feeType,
                                     @ApiParam(value = "手续费费率值") @RequestParam(required = false) String feeValue,
                                     @ApiParam(value = "起收手续费") @RequestParam(required = false) String feeMinValue,
                                     @ApiParam(value = "封顶手续费") @RequestParam(required = false) String feeMaxValue,
                                     @ApiParam(value = "退款手续费处理规则") @RequestParam(required = false) String refundFeeRuleFlag,
                                     @ApiParam(value = "结算账期") @RequestParam(required = false) String settleTdFlag,
                                     @ApiParam(value = "结算周期(天)") @RequestParam(required = false) String settleCycle,
                                     @ApiParam(value = "最低打款额（元）") @RequestParam(required = false) String minTransferAmt,
                                     @ApiParam(value = "付款方式") @RequestParam(required = false) String payType,
                                     HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> params = RequestParamUtil.getAllRequestParam(request);//取出所有参数
        JsonResult jsonResult = null;
        try {
            jsonResult = payBusinessService.businessModify(params);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "修改审核失败商户信息")
    @RequestMapping(value = "/modifyFailBusiness", method = RequestMethod.POST)
    public JsonResult modifyFailBusiness() {

        JsonResult jsonResult = null;
        try {
            jsonResult = payBusinessService.modifyFailBusiness();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return jsonResult;
    }

}
