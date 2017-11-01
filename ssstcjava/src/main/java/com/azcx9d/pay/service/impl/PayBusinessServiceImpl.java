package com.azcx9d.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.pay.dao.PayBusinessDao;
import com.azcx9d.pay.entity.BusinessInNet;
import com.azcx9d.pay.service.PayBusinessService;
import com.azcx9d.pay.util.*;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
@Service
public class PayBusinessServiceImpl implements PayBusinessService {

    @Autowired
    private PayBusinessDao payBusinessDao;

    private Logger logger = LoggerFactory.getLogger(PayBusinessServiceImpl.class);

    private final static String T1 = "1";

    private final static String T2 = "3";

    private final static String T3 = "7";

    //开发(SIT)
    private static final String MERCHANT_INPUTNET_URL = "https://merinputnet.nbcip.com/v1.0";

    private static final String MERCHANT_MODIFY_URL = "https://mermodify.nbcip.com/v1.0";

    //图片地址url
    private static final String PIC_URL = "https://ssstc.cn";

    static {
        Configs.init("nbpayinfo.properties");
    }

    @Override
    public JsonResult businessInNet() throws Exception {
        int successNum = 0;
        int failNum = 0;
        StringBuffer failDetail = new StringBuffer("");
        //提交裕顺审核商家列表
        List<BusinessInNet> list = payBusinessDao.getBusinessInNetList();
        for (BusinessInNet businessInNet : list) {
            Map<String, String> params = new HashMap<>();

            params.put("signType", Configs.getSignType());
            params.put("charSet", "UTF-8");
            params.put("partnerId", Configs.getPid());
            params.put("agentNumber", Configs.getAgentNumber());
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            params.put("timestamp", dateString);

            params.put("merchantName", businessInNet.getMerchantName() + businessInNet.getbId());
            params.put("merchDiv", businessInNet.getMerchDiv());
            params.put("mccNum", businessInNet.getOperateType());
            params.put("mcc", businessInNet.getOperateTypeName());
            params.put("companyName", businessInNet.getBankAccountName());//取为公司对公账户名称
            params.put("merchEngName", "noData");//noData
            params.put("licenceNo", businessInNet.getLicenceNo());

            String licenceLink = ftpUpload(PIC_URL + businessInNet.getBusinessLicense());
            params.put("licenceLink", licenceLink);
            params.put("agencyNum", Configs.getAgentNumber());
            params.put("bankCode", businessInNet.getBankCode());
            params.put("bankName", businessInNet.getBankName());
            params.put("bankBranch", businessInNet.getBankBranch());
            params.put("bankAccountNo", businessInNet.getBankAccountNo());
            params.put("bankAccountName", businessInNet.getBankAccountName());
            params.put("bankProvince", "noData");//noData
            params.put("bankCity", "noData");//noData
            params.put("location", businessInNet.getLocation());
            params.put("certType", businessInNet.getCertType());
            params.put("realName", businessInNet.getCertName());
            params.put("certId", businessInNet.getCertId());

            String certLink1 = ftpUpload(PIC_URL + businessInNet.getIdentityCardUp());
            params.put("certLink1", certLink1);
            params.put("phoneNoCipher", businessInNet.getCertPhone());
            params.put("bizLinkman", businessInNet.getBizLinkMan());
            params.put("bizPhone", businessInNet.getBizPhone());
            params.put("bizEmail", "noData@abc.com");//noData
            params.put("startVal", businessInNet.getStartVal());
            params.put("endVal", businessInNet.getEndVal());
            params.put("feeType", businessInNet.getFeeType());
            params.put("feeValue", businessInNet.getFeeValue());
            params.put("feeMinValue", businessInNet.getMinValue());
            params.put("feeMaxValue", businessInNet.getMaxValue());
            params.put("refundFeeRuleFlag", businessInNet.getRefundFeeRuleFlag());
            params.put("settleTdFlag", businessInNet.getSettleTdflag());
            params.put("settleCycle", businessInNet.getSettleCycle());
            params.put("minTransferAmt", businessInNet.getMinTransferAmt());
            params.put("payType", businessInNet.getPayType());
            params.put("creatTime", dateString);//申请时间暂时为当前时间
            params.put("companySettle", T1);//先默认为T1
            //TODO 异步通知地址,要换成正式环境地址
            params.put("notifyUrl", "https://ssstc.cn/business/v1/bApi/payBusiness/businessInNetNotify");

            String content = PaySignature.getSignContent(params);
            String signstr = null;
            try {
                signstr = PaySignature.rsaSign(content, Configs.getPrivateKey(), "UTF-8", Configs.getSignType());
            } catch (ApiException e) {
                logger.error(e.getErrMsg());
            }
            try {
                content = content + "&sign=" + URLEncoder.encode(signstr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage());
            }

            HttpClient httpClient = new HttpClient();
            String result = httpClient.sendHttp(MERCHANT_INPUTNET_URL, content);

            JSONObject jsonObject = (JSONObject) JSONObject.parse(result);

            //提交成功后修改状态修改状态
            if ("SUCCESS".equals(jsonObject.get("errorCode") + "")) {
                Map map = new HashMap();
                map.put("id", businessInNet.getId());
                map.put("merchantNumber", jsonObject.get("data"));

                successNum += payBusinessDao.updateBusinessInNetState(map);
            } else {
                failNum++;
                failDetail.append("id: ").append(businessInNet.getId()).append(", 失败原因:").append(jsonObject.get("message"));
            }
        }
        String content = "提交成功: " + successNum + "条, 失败: " + failNum + "条; 详情: " + failDetail.toString();
        return new JsonResult(0, "ok", content);
    }

    @Override
    public JsonResult businessInNetNotify(Map params) throws Exception {
        JsonResult jsonResult = null;
        if (payBusinessDao.businessInNetNotify(params) > 0) {
            jsonResult = new JsonResult("0", "更新商户状态成功");
        } else {
            jsonResult = new JsonResult("2", "更新商户状态失败");
        }
        return jsonResult;
    }

    @Override
    public JsonResult businessModify(Map map) throws Exception {
        Map<String, String> params = new HashMap<>();

        params.put("signType", Configs.getSignType());
        params.put("charSet", "UTF-8");
        params.put("partnerId", Configs.getPid());
        params.put("agentNumber", Configs.getAgentNumber());
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        params.put("timestamp", dateString);

//        params.put("companyName", params.get("companyName"));
//        params.put("licenceNo", params.get("licenceNo"));
//        params.put("merchantNumber", params.get("merchantNumber"));
//        params.put("bankCode", params.get("bankCode"));
//        params.put("bankName", params.get("bankName"));
//        params.put("bankBranch", params.get("bankBranch"));
//        params.put("bankCertLink", params.get("bankCertLink"));
//        params.put("bankAccountNo", params.get("bankAccountNo"));
//        params.put("bankAccountName", params.get("bankAccountName"));
//        params.put("lineNumber", params.get("lineNumber"));
//        params.put("bankProvince", params.get("bankProvince"));
//        params.put("bankCity", params.get("bankCity"));
//        params.put("startVal", params.get("startVal"));
//        params.put("endVal", params.get("endVal"));
//        params.put("feeType", params.get("feeType"));
//        params.put("feeValue", params.get("feeValue"));
//        params.put("feeMinValue", params.get("feeMinValue"));
//        params.put("feeMaxValue", params.get("feeMaxValue"));
//        params.put("refundFeeRuleFlag", params.get("refundFeeRuleFlag"));
//        params.put("settleTdFlag", params.get("settleTdFlag"));
//        params.put("settleCycle", params.get("settleCycle"));
//        params.put("minTransferAmt", params.get("minTransferAmt"));
//        params.put("payType", params.get("payType"));
        params.putAll(map);

        String content = PaySignature.getSignContent(params);
        String signstr = null;
        try {
            signstr = PaySignature.rsaSign(content, Configs.getPrivateKey(), "UTF-8", Configs.getSignType());
        } catch (ApiException e) {
            logger.error(e.getErrMsg());
        }
        try {
            content = content + "&sign=" + URLEncoder.encode(signstr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }

        HttpClient httpClient = new HttpClient();
        String result = httpClient.sendHttp(MERCHANT_MODIFY_URL, content);

        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);

        if ("SUCCESS".equals(jsonObject.get("errorCode") + "")) {
            return new JsonResult(0, "修改成功!");
        } else {
            return new JsonResult(2, "修改失败!"+jsonObject.get("message"));
        }
    }

    @Override
    public JsonResult modifyFailBusiness() throws Exception {
        int successNum = 0;
        int failNum = 0;
        StringBuffer failDetail = new StringBuffer("");
        //提交裕顺审核商家列表
        List<BusinessInNet> list = payBusinessDao.getFailBusinessList();
        for (BusinessInNet businessInNet : list) {
            Map<String, String> params = new HashMap<>();

            params.put("signType", Configs.getSignType());
            params.put("charSet", "UTF-8");
            params.put("partnerId", Configs.getPid());
            params.put("agentNumber", Configs.getAgentNumber());
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            params.put("timestamp", dateString);

            params.put("merchantName", businessInNet.getMerchantName() + businessInNet.getbId());
            params.put("merchDiv", businessInNet.getMerchDiv());
            params.put("mccNum", businessInNet.getOperateType());
            params.put("mcc", businessInNet.getOperateTypeName());
            params.put("companyName", businessInNet.getBankAccountName());//取为公司对公账户名称
            params.put("merchEngName", "noData");//noData
            params.put("licenceNo", businessInNet.getLicenceNo());

            String licenceLink = ftpUpload(PIC_URL + businessInNet.getBusinessLicense());
            params.put("licenceLink", licenceLink);
            params.put("agencyNum", Configs.getAgentNumber());
            params.put("bankCode", businessInNet.getBankCode());
            params.put("bankName", businessInNet.getBankName());
            params.put("bankBranch", businessInNet.getBankBranch());
            params.put("bankAccountNo", businessInNet.getBankAccountNo());
            params.put("bankAccountName", businessInNet.getBankAccountName());
            params.put("bankProvince", "noData");//noData
            params.put("bankCity", "noData");//noData
            params.put("location", businessInNet.getLocation());
            params.put("certType", businessInNet.getCertType());
            params.put("realName", businessInNet.getCertName());
            params.put("certId", businessInNet.getCertId());

            String certLink1 = ftpUpload(PIC_URL + businessInNet.getIdentityCardUp());
            params.put("certLink1", certLink1);
            params.put("phoneNoCipher", businessInNet.getCertPhone());
            params.put("bizLinkman", businessInNet.getBizLinkMan());
            params.put("bizPhone", businessInNet.getBizPhone());
            params.put("bizEmail", "noData@abc.com");//noData
            params.put("startVal", businessInNet.getStartVal());
            params.put("endVal", businessInNet.getEndVal());
            params.put("feeType", businessInNet.getFeeType());
            params.put("feeValue", businessInNet.getFeeValue());
            params.put("feeMinValue", businessInNet.getMinValue());
            params.put("feeMaxValue", businessInNet.getMaxValue());
            params.put("refundFeeRuleFlag", businessInNet.getRefundFeeRuleFlag());
            params.put("settleTdFlag", businessInNet.getSettleTdflag());
            params.put("settleCycle", businessInNet.getSettleCycle());
            params.put("minTransferAmt", businessInNet.getMinTransferAmt());
            params.put("payType", businessInNet.getPayType());
            params.put("creatTime", dateString);//申请时间暂时为当前时间
            params.put("companySettle", T1);//先默认为T1
            params.put("merchantNumber", businessInNet.getMerchantNumber());
            //TODO 异步通知地址,要换成正式环境地址
            params.put("notifyUrl", "https://ssstc.cn/business/v1/bApi/payBusiness/businessInNetNotify");

            String content = PaySignature.getSignContent(params);
            String signstr = null;
            try {
                signstr = PaySignature.rsaSign(content, Configs.getPrivateKey(), "UTF-8", Configs.getSignType());
            } catch (ApiException e) {
                logger.error(e.getErrMsg());
            }
            try {
                content = content + "&sign=" + URLEncoder.encode(signstr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage());
            }

            HttpClient httpClient = new HttpClient();
            String result = httpClient.sendHttp(MERCHANT_INPUTNET_URL, content);

            JSONObject jsonObject = (JSONObject) JSONObject.parse(result);

            //提交成功后修改状态修改状态
            if ("SUCCESS".equals(jsonObject.get("errorCode") + "")) {
                successNum ++;
            } else {
                failNum++;
                failDetail.append("id: ").append(businessInNet.getId()).append(", 失败原因:").append(jsonObject.get("message"));
            }
        }
        String content = "提交成功: " + successNum + "条, 失败: " + failNum + "条; 详情: " + failDetail.toString();
        return new JsonResult(0, "ok", content);
    }

    public static String ftpUpload(String filePath) {
        String[] strs = filePath.split("/");
        String fileName = strs[strs.length - 1];
        SFTPSer sftpSer = new SFTPSer();
        try {
            ChannelSftp sftpConn = sftpSer.connectSFTP();
            sftpSer.upload("/merchant/", filePath, fileName, sftpConn);// merchant为上传时的固定目录
            //sftpSer.upload("/merchant/", filePath, sftpConn);// 打开一次链接，可以上传多个文件
            sftpSer.disconnected(sftpConn);
        } catch (JSchException e) {

        }
        //最后上送的 路径为 "/merchant/123.jpg"
        return "/merchant/" + fileName;
    }
}
