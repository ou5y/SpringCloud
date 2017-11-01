package com.azcx9d.pay.service.impl;

import com.azcx9d.business.dao.AccountSecurityDao;
import com.azcx9d.business.dao.BAgentPayDao;
import com.azcx9d.business.dao.BOrderFormDAO;
import com.azcx9d.business.dao.CPayInfoMapper;
import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Arith;
import com.azcx9d.pay.service.PaySerivce;
import com.azcx9d.pay.service.TfbMobilePayService;
import com.azcx9d.pay.util.StringUtils;
import com.azcx9d.pay.util.tfb.agentPay.AgentRSAUtils;
import com.azcx9d.pay.util.tfb.agentPay.AgentRequestUtils;
import com.azcx9d.pay.util.tfb.union.MD5Utils;
import com.azcx9d.pay.util.tfb.union.RequestUtils;
import com.azcx9d.pay.util.tfb.union.TFBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/8/21 0021.
 */
@Service("tfbMobilePayService")
public class TfbMobilePayServiceImpl implements TfbMobilePayService {

    @Autowired
    private CPayInfoMapper cpayInfoMapper;
    @Autowired
    private BOrderFormDAO borderFormDAO;
    @Autowired
    private AccountSecurityDao accountSecurityDao;
    @Autowired
    private BAgentPayDao bAgentPayDao;
    @Autowired
    private PaySerivce paySerivce;



    // 生成支付宝支付申请
    @Override
    @Transactional
    public JsonResult createPayApply(TreeMap<String, String> paramsMap) throws Exception{
        System.out.println("----------------进入支付方法");

        Map<String, String> param = new HashMap<>();String authCode = paramsMap.get("auth_code");
        boolean isWechart = (!StringUtils.isEmpty(authCode)) && authCode.startsWith("13");
        BOrderForm order = borderFormDAO.findByOrderNo(paramsMap.get("sp_billno"));
        //商家应得金额（代付金额）
        int tranAmt = calcTranAmount(isWechart ? 13 : 12, order);
        System.out.println("----------------代付金额为："+tranAmt);
        if (Integer.valueOf(paramsMap.get("tran_amt")) < tranAmt || tranAmt<=0){
            return new JsonResult(2,"支付金额不足代付与手续费");
        }

        //拼接签名原串
        String paramSrc = RequestUtils.getParamSrc(paramsMap);

        //生成签名
        String sign = MD5Utils.sign(paramSrc, TFBConfig.serverEncodeType);

        paramSrc = paramSrc + "&sign=" + sign;
        //发起请求
        String responseData = RequestUtils.doPost(isWechart ? TFBConfig.WECHART_PAY_APPLY_API : TFBConfig.ALIPAY_PAY_APPLY_API, paramSrc,TFBConfig.serverEncodeType);
        //String responseData = "<?xml version=\"1.0\" encoding=\"GB2312\" ?><root><cur_type>CNY</cur_type><listid>1021800776625170815000073375</listid><pay_type>800201</pay_type><qrcode>https://qr.alipay.com/bax058437fnrgpluamva00d1</qrcode><retcode>00</retcode><retmsg>操作成功</retmsg><sign>4a52204b18135cef5a55e1eb8fd6dfbb</sign><sp_billno>141</sp_billno><spid>1800776625</spid><sysd_time>20170815111957</sysd_time><tran_amt>1</tran_amt></root>";

        //解析xml
        TreeMap<String, String> map = RequestUtils.Dom2Map(responseData);

        if (map.get("retcode").equals("00")) {
            //获得签名串
            paramSrc = RequestUtils.createLinkString(map);
            System.out.println("签名原串：" + paramSrc);

            //获得签名字段
            sign = map.get("sign");
            System.out.println("服务端签名值:" + sign);

            if (MD5Utils.verify(paramSrc, sign, TFBConfig.serverEncodeType)) {
                System.out.println("验签结果：成功");
                //请在这里加上商户的业务逻辑程序代码

                //保存支付记录
                param.clear();
                param.put("userId", order.getUserId()+"");//
                param.put("orderId", order.getId()+"");
                param.put("payPlatform", isWechart ? "2" : "3");//支付平台:1-裕顺,2-微信,3-支付宝
                param.put("sellerId", order.getSellerId()+"");//商家id
                double money = Arith.div(Double.parseDouble(map.get("tran_amt")),100,2);
                param.put("orderFee", money+"");//订单金额
                cpayInfoMapper.addPayInfo(param);

                //更新订单信息（订单状态、支付方式等）
                param.clear();
                param.put("orderId", order.getId()+"");//订单号
                param.put("state", "2");//订单状态(-2:审核失败,-1:商家关闭,0:待商家确认收款,1:商家确认收款,2:财务确认收款,3:奖励已分配)
                param.put("payType", isWechart ? "13" : "12");//1-裕顺  11-天付宝银联快捷  12-天付宝支付宝  13-天付宝微信',
                param.put("sellerTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));//商家确认时间
                param.put("caiwuTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));//商家确认时间
                param.put("rangliMoney", Arith.div(Arith.mul(money, order.getRangli()),1,2)+"");//让利金额
                borderFormDAO.updateOrder(param);

                //更新支付信息
                param.clear();
                param.put("platformNumber", map.get("merch_listid"));//裕顺支付流水号
                param.put("platformStatus", "SUCCESS");//裕顺支付状态
                param.put("totalFee", money+"");
                param.put("orderId", order.getId()+"");//订单号
                cpayInfoMapper.updatePayInfo(param);
                System.out.println("----------------支付成功，状态为2财务确认收款");
                return new JsonResult(0, "支付成功");
            } else {
                System.out.println("验签结果：失败");
                return new JsonResult(2,"验签结果：失败");
            }
        } else {
            System.out.println(StringUtils.isEmpty(map.get("retmsg")) ? "请求异常" : map.get("retmsg"));
            return new JsonResult(Integer.valueOf(map.get("retcode")), StringUtils.isEmpty(map.get("retmsg")) ? "请求异常" : map.get("retmsg"));
        }
    }

    // 关闭订单
    @Override
    @Transactional
    public JsonResult colseOrder(TreeMap<String, String> paramsMap) throws Exception{
        //拼接签名原串
        String paramSrc = RequestUtils.getParamSrc(paramsMap);

        //生成签名
        String sign = MD5Utils.sign(paramSrc, TFBConfig.serverEncodeType);

        paramSrc = paramSrc + "&sign=" + sign;

        //发起请求
        String responseData = RequestUtils.doPost(TFBConfig.ALIPAY_PAY_CANCEL_API, paramSrc,TFBConfig.serverEncodeType);

        //判断返回码
        String retcode = RequestUtils.getXmlElement(responseData, "retcode");
        //String retmsg = RequestUtils.getXmlElement(responseData, "retmsg");
        if (retcode.equals("00")) {
            //xml转存map
            TreeMap<String, String> map = RequestUtils.Dom2Map(responseData);
            //获得签名串
            paramSrc = RequestUtils.createLinkString(map);
            System.out.println("签名原串：" + paramSrc);
            //获得签名字段
            sign = map.get("sign");
            System.out.println("服务端签名值:" + sign);
            if (MD5Utils.verify(paramSrc, sign, TFBConfig.serverEncodeType)) {
                System.out.println("验签结果：成功");

                //请在这里加上商户的业务逻辑程序代码

                return new JsonResult(0,"关闭订单成功");
            } else {
                System.out.println("验签结果：失败");
                return new JsonResult(2,"验签结果：失败");
            }
        } else {
            String retmsg = RequestUtils.getXmlElement(responseData, "retmsg");
            System.out.println(StringUtils.isEmpty(retmsg) ? retmsg = "请求异常" : retmsg);
            return new JsonResult(Integer.valueOf(retcode), retmsg);
        }
    }


    /**
     * 单笔代付
     * @return JsonResult
     */
    private JsonResult createPaySingle(int tranAmt, BOrderForm order) throws Exception{
        System.out.println("----------------进入代付");
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put("version","1.0");    //固定填1.0
        paramsMap.put("spid", TFBConfig.spid_of_agent);   //填写国采分配的商户号
        //String sp_serialno = format.format(date);
        paramsMap.put("sp_serialno",order.getId()+""); //商户交易单号，商户保证其在本系统唯一,每次交易入库需要修改订单号
        paramsMap.put("sp_reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));   //系统发送时间，14位固定长度
        paramsMap.put("tran_amt", tranAmt+"");    //交易金额，单位为分，不带小数点
        paramsMap.put("cur_type", "1");      //
        //paramsMap.put("pay_type", "1");      //普通余额支付填 1；垫资代付填3

        Map<String,Object> para1 = new HashMap<>(0);
        para1.put("userId",order.getSellerId());
        List<Map<String,Object>> bankCards = accountSecurityDao.queryMyBankCard(para1);
        if(bankCards==null || bankCards.size()<1){
            return new JsonResult(2,"商户收款失败");
        }

        paramsMap.put("acct_name", bankCards.get(0).get("cardHolder").toString());  //收款人姓名
        paramsMap.put("acct_id", bankCards.get(0).get("bankCardNo").toString());   //收款人账号
        //paramsMap.put("acct_type", "2");   //0 借记卡， 1 贷记卡， 2 对公账户
        //paramsMap.put("mobile", "17358691255");
        paramsMap.put("business_type", "20101");        //固定填写
        paramsMap.put("memo", "代付");

//        paramsMap.put("acct_name", "白攀");  //收款人姓名
//        paramsMap.put("acct_id", "6236683810004450607");   //收款人账号
//        paramsMap.put("business_type", "20101");        //固定填写
//        paramsMap.put("memo", "代付");

        String responseData = AgentRequestUtils.sendRequst(TFBConfig.PAY_SINGLE, paramsMap, TFBConfig.serverEncodeTypeUTF8);

        //如果返回码不为00，返回结果不会加密，因此不往下处理，
        if (!RequestUtils.getXmlElement(responseData, "retcode").equals("00")) {
            return new JsonResult(2,RequestUtils.getXmlElement(responseData, "retmsg"));
        }
        //获得服务器返回的加密数据
        String cipherResponseData = RequestUtils.getXmlElement(responseData, "cipher_data");

        //对服务器返回的加密数据进行rsa解密
        responseData = AgentRSAUtils.decrypt(cipherResponseData,"UTF-8");
        String sign = responseData.substring(responseData.indexOf("sign=") + 5, responseData.length());
        String source = responseData.substring(0, responseData.lastIndexOf("&sign"));
        //rsa验签
        if (AgentRequestUtils.verify(source, sign)) {
            //Map<String, String> map = new HashMap<>();
            //map.put("orderId", order.getId()+"");//订单号
            //map.put("state", "2");//订单状态(-2:审核失败,-1:商家关闭,0:待商家确认收款,1:商家确认收款,2:财务确认收款,3:奖励已分配)
            //map.put("caiwuTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));//财务确认时间
            //borderFormDAO.updateOrder(map);

            //新增代付记录
            Map<String,Object> agentPay = new HashMap<>(0);
            agentPay.put("orderId",order.getId());
            agentPay.put("sellerId",order.getSellerId());
            agentPay.put("royaltyMoney",Arith.div(tranAmt,100,2));
            agentPay.put("orderMoney",order.getMoney());
            agentPay.put("rangli",order.getRangli());
            agentPay.put("bankCardNo",bankCards.get(0).get("bankCardNo").toString());
            agentPay.put("cardHolder",bankCards.get(0).get("cardHolder").toString());
            bAgentPayDao.saveAgentPay(agentPay);
            return new JsonResult(0,"支付成功");
        } else {
            return new JsonResult(2,"非法数据");
        }
    }

    /**
     * @Description: 商家应得金额（代付金额）
     * @param payType 1-裕顺  11-天付宝银联快捷  12-天付宝支付宝  13-天付宝微信
     * @date 2017/8/22 0022
     */
    private int calcTranAmount(int payType, BOrderForm order)throws Exception{
        Double money = order.getMoney();
        Double sellerLi = Arith.sub(1.0, order.getRangli());
        Double sellerMoney = Arith.mul(money, sellerLi);//商家所得
        Double poundage = 0.0;
        //11-天付宝银联快捷,12-天付宝微信,13-天付宝支付宝
        if(payType == 11){
            poundage = Arith.mul(money,TFBConfig.UNION_POUNDAGE,2);
        }else{
            poundage = Arith.mul(money,TFBConfig.AX_POUNDAGE,2);
        }
        sellerMoney = Arith.sub(sellerMoney,poundage);//减去手续费
        sellerMoney = Arith.mul(sellerMoney,100,2);//转换成分为单位
        return sellerMoney.intValue();//获取整数值
    }

    // 查询交易状态
    @Override
    @Transactional
    public JsonResult queryTrade(TreeMap<String, String> paramsMap)throws Exception{
        return null;
    }

    // 退款查询
    @Override
    @Transactional
    public JsonResult queryRefund(TreeMap<String, String> paramsMap)throws Exception{
        return null;
    }

    // 退款
    @Override
    @Transactional
    public JsonResult addRefund(TreeMap<String, String> paramsMap)throws Exception{
        return null;
    }

    /**
     * 异步通知
     * @return JsonResult
     */
    @Override
    @Transactional
    public JsonResult notifyUrl(Map<String, String> params)throws Exception{
        System.out.println("----------------进入回调");
        Map<String, String> map = new HashMap<>();
        map.put("orderId", params.get("sp_billno"));//订单号
        if ("1".equals(params.get("notify_type"))) {//通知类型：1-支付2-退款3-关闭
            if ("0".equals(params.get("tran_state"))) {//支付状态：0-失败；1-成功
                map.put("state", "4");//订单状态(-2:审核失败,-1:商家关闭,0:待商家确认收款,1:商家确认收款,2:财务确认收款,3:奖励已分配,4:用户取消支付,5:等待支付)
                borderFormDAO.updateOrder(map);//更新订单状态
                return new JsonResult(0, "更新订单状态(：4)成功");
            } else {
                BOrderForm order = borderFormDAO.findByOrderNo(params.get("sp_billno"));

                TreeMap<String, String> paramsMap = new TreeMap<>();
                paramsMap.put("version", "1.0");    //固定填1.0
                paramsMap.put("spid", TFBConfig.spid_of_agent);   //填写国采分配的商户号
                paramsMap.put("sp_serialno", order.getId()+""); //商户交易单号，商户保证其在本系统唯一,每次交易入库需要修改订单号
                paramsMap.put("sp_reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));   //系统发送时间，14位固定长度
                JsonResult result = queryOrderInfo(paramsMap);
                if (result.getstatus() != 0) {//未代付、去代付
                    //商家应得金额（代付金额）
                    int tranAmt = calcTranAmount(order.getPayType(), order);
                    if (tranAmt <= 0) {
                        return new JsonResult(2, "代付金额小于等于0");
                    }
                    //分账（代付）
                    order.setMoney(tranAmt);
                    result = createPaySingle(tranAmt, order);
                    if (result.getstatus() != 0) {
                        return result;
                    }
                }
                if (order.getState() != 3) {//已结代付，查看是否上分
                    System.out.println("----------------执行上分");
                    map.put("state", "3");//订单状态(-2:审核失败,-1:商家关闭,0:待商家确认收款,1:商家确认收款,2:财务确认收款,3:奖励已分配,4:用户取消支付,5:等待支付)
                    borderFormDAO.updateOrder(map);//更新订单状态
                    paySerivce.updateOrderSettlement(order.getId()); //调用上分
                    System.out.println("----------------上分成功，完结");
                    return new JsonResult(0, "更新订单状态、上分成功");
                }else{
                    return new JsonResult(0, "已处理完结");
                }
            }
        }else if ("2".equals(params.get("notify_type"))){//通知类型：1-支付2-退款3-关闭
            return new JsonResult(0, "SUCCESS");
        }else if ("3".equals(params.get("notify_type"))){//通知类型：1-支付2-退款3-关闭
            map.put("state", "-1");//订单状态(-2:审核失败,-1:商家关闭,0:待商家确认收款,1:商家确认收款,2:财务确认收款,3:奖励已分配)
            map.put("closeTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));//关闭时间
            borderFormDAO.updateOrder(map);
            return new JsonResult(0,"订单关闭成功");
        }else{
            return new JsonResult(2, "未知的通知类型："+params.get("notify_type"));
        }
    }


    /**
     * 单笔代付结果查询接口
     * @return JsonResult
     */
    public JsonResult queryOrderInfo(TreeMap<String, String> paramsMap) throws Exception{
        String responseData = AgentRequestUtils.sendRequst(TFBConfig.PAY_SINGLE_QUERY, paramsMap, TFBConfig.serverEncodeTypeUTF8);

        String retcode = RequestUtils.getXmlElement(responseData, "retcode");
        String retmsg = RequestUtils.getXmlElement(responseData, "retmsg");

        if (!"00".equals(retcode)) {//如果返回码不为00，返回结果不会加密，因此不往下处理，
            return new JsonResult(Integer.parseInt(retcode), retmsg);
        }

        //获得服务器返回的加密数据
        String cipherResponseData = RequestUtils.getXmlElement(responseData, "cipher_data");


        //对服务器返回的加密数据进行rsa解密
        responseData = AgentRSAUtils.decrypt(cipherResponseData,"UTF-8");
        String sign = responseData.substring(responseData.indexOf("sign=") + 5, responseData.length());
        String source = responseData.substring(0, responseData.lastIndexOf("&sign"));

        //rsa验签
        if (AgentRequestUtils.verify(source, sign)) {
            String serialnoState = responseData.substring(responseData.indexOf("serialno_state=") + 15, responseData.indexOf("serialno_state=")+16);
            if ("1".equals(serialnoState)){
                return new JsonResult(0,"处理成功");
            }else if ("2".equals(serialnoState)){
                return new JsonResult(2,"处理中");
            }else if ("3".equals(serialnoState)){
                return new JsonResult(2,"处理失败");
            }else if ("4".equals(serialnoState)){
                return new JsonResult(2,"已退汇");
            }else{
                return new JsonResult(2,"未知的通知类型");
            }
        } else {
            return new JsonResult(2,"验签失败，非法数据");
        }
    }

}
