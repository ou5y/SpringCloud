package com.azcx9d.pay.util.tfb.union;

public class TFBConfig {

    // 接口域名，测试访问域名（apitest.tfb8.com）（正式环境改为：api.tfb8.com）
    public static String hostName = "http://api.tfb8.com";

    // MD5密钥，国采分配给商户的用于签名和验签的key
    public static String MD5_KEY = "12345";

    // 商户发起支付请求的IP
    public static String spbill_create_ip = "192.168.11.96";

    // 商户/平台在国采注册的账号。国采维度唯一，固定长度10位
    public static String spid = "1800776625";

    // 商户/平台在国采注册的账号。国采维度唯一，固定长度10位（代付的SPID）
    public static String spid_of_agent = "1800046681";

    // 用户号，持卡人在商户/平台注册的账号。商户/平台维度唯一，必须为纯数字
    public static String sp_userid = "1";

    // 订单有效时长，以国采服务器时间为准的订单有效时间长度。单位:秒，如果不填则采用默认值
    public static String expire_time = "";

    // 订单金额的类型。1 – 人民币(单位: 分)
    //public static String cur_type = "1";

    // 订单金额的类型。1 – 人民币(单位: 分)
    public static String cur_type = "CNY";

    // 商户的用户使用的终端类型。1 – PC端，2 – 手机端
    public static String channel = "2";

    // 签名的方法。目前支持: MD5，RSA
    public static String encode_type = "MD5";

    // 服务器数据编码类型（GBK）
    public static String serverEncodeType = "GBK";

    // 服务器数据编码类型（UTF-8）
    public static String serverEncodeTypeUTF8 = "UTF-8";

    // 服务器异步通知页面路径 需http://格式notify_url的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 天付宝银联手续费
    public static final Double UNION_POUNDAGE = 0.01;

    // 天付宝微信/支付宝手续费
    public static final Double AX_POUNDAGE = 0.01;

    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // v1/bApi/mobilePay/callbackPay
    // http://baipan.tunnel.qydev.com
    // http://120.27.9.170:9999/business
    public static String notify_url = "https://ssstc.cn/business/v1/bApi/mobilePay/callbackPay";

    // 商户的私钥,需要PKCS8格式
    public static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK+LzCZnUWIsRSxKyGZrZI+BU+Y+wnTXPpVbKcm5LT1fg/+o7aQR6B7pheWSEH5xLiFmtUkWSgZ7tYJhjovJkwgIJ91BQBg3rVT3xPCjeVu88mrdvzQOe6sS5WNPu3Wxbht9uACO16zupdDrruhjRUaCX5tkLukccU3bqp9FpkkNAgMBAAECgYBx8mB1nSLqgqnz8ibatGL185CuJ5a5mO36rM4XLqf66oEX9mMq2KS/S/2p4oHqUTUMYUrTQjCSvMI4+3I3soRI4k4J5VsyP9zHyHzafvNUTUyp2ybaVgmh3oxU4sx015fd+3Qc219l+Jdod+rIi68NJqhhMUU+q7yxmesCUCkZAQJBAOWH5bu9FmFIiSjWHVj6XE0904KOWSoHsenymzMZfM0s1kck1hUvwntUcmUhkiuz4BBmiKOy65MtNyJ6ChE3UP0CQQDDyi/gX/xOhCOpWoDMnYyKGyQH7GMJBIwK/X80Yha3Qtl/WrdqrpNV/ZHyQJgcIQFoMNLbNotoUOMAjthkrR1RAkAU5RAmzQnShVXnH8bAKNpqNayhf+/iAZ1SnMFAH5va2bAP/ex3NUfRDljzl+DElbVaCNt7e3gyh7UzMETmWFDJAkAwFtw1jz3ohxo/QYR7PYNEdLAf5hbZIy3GkUcKNcGAl8HWPxDn+iMkLtkHGIiD+DNhRQS1ZStOnvdyrqNF7yNRAkEAxm2MZmPHl+7jbDjHG6c+3SE6e0s7iZyatgh2gosKXdpqUWe3zVXPN04kLarZ7tasl1IBqHr1LpzdHEUReiNRBQ==";

    // 天付宝公钥
    public static String TFB_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjDrkoVbyv4jTxeKtKEiK2mZiezQvfJV3sGhiwOnB+By5sa5Sa6Ls4dt5AGVqKHxyQVKRpu/utwtEt2MijWx45P1y2xGe7oDz2hUXP0j8sSa1NP26TmWHwO7czgJxxrdJ6RNqskSfjwsa5YMsqmcrumxUIxeCg5EOkgU26bnPoZQIDAQAB";

    // 商户的私钥,需要PKCS8格式
    public static String AGENT_PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAL80WRy2i3JLrTHp8f0gYVGd+lu9k6ZSixMCEoRVsNbpt3NIdXI/aWW/G/GfW0c4TbacyhTV7Yn+I+eMrXjIFoi1CoYi0eIIQrZC5cU+KBA+reYDbAX30o8K4d90QTlPTzueW6TP/c2f/E7Ub0rHXj7d6Als1jO1oE0uiErQKTO9AgMBAAECgYEAkKuYHVyVaBaQZirf2SmN2QZSLuXi+L6N0gUIY66+je3qy0Rw8M+//Kc8CncLxnh4fIpncJppD7cGDaXof3HNcbMjIpBvwDJhpqLVH7JNx16HeQ73uWcqCHMLecNFQR8XAEiQJ0JbGYuGOxUqFYG87YEKXFIBGL50+ZlBHARRGoECQQD7JPdZhMp6dBfTp93/6Oa+OvBl6wMDAvUUIT1RAF4UVeQ+1vg0BBtJE0TPGnacO3DcCobA+KKpDBz88BxRmvIhAkEAwua15+io6vUciObBUPAuNrJEEdW9KxK/rocI5B/cIc4zSIgrl5nLvy5uqMuXiL94E0Oa4dwDkbbtaeufwA8GHQJBAKOehMu8mNHImtFZN2gHi3T6Hy63OsIWhib0NOd17tUe1FIgaZox5rjoJdcr7YSBsViaPwqvsgGik6wynrCH2yECQDAKYigppwlTJZdxGZFzwlBlHHYw8xHc6zZ/vmdMmxwSEX39YpFZrWkQbuJYXJ+uYlCNR24IpzCRoG+NTrEugtkCQQCCanl2vQTbK7gW4BXVuwcc6CxvPCEo7BHESTVCgV0hwSIwusE6UfQKVRGM/yjok8U2Y0F2C5yhJD/WdHvYkWKv";
//
//    // 天付宝公钥
//    public static String TFB_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjDrkoVbyv4jTxeKtKEiK2mZiezQvfJV3sGhiwOnB+By5sa5Sa6Ls4dt5AGVqKHxyQVKRpu/utwtEt2MijWx45P1y2xGe7oDz2hUXP0j8sSa1NP26TmWHwO7czgJxxrdJ6RNqskSfjwsa5YMsqmcrumxUIxeCg5EOkgU26bnPoZQIDAQAB";


    //-----------------------------------------------------------------------------银联快捷开始-------------------------------------------------------------------------
    // 银行卡快捷签约支付申请调用的接口名
    public static String qsignPayApplyApi = hostName + "/cgi-bin/v2.0/api_qsignpay_apply.cgi";

    // 银行卡快捷签约支付确认调用的接口名
    public static String qsignPayComfirmApi = hostName + "/cgi-bin/v2.0/api_qsignpay_confirm.cgi";

    // 银行卡快捷支付申请调用的接口名
    public static String bankCardPayApi = hostName + "/cgi-bin/v2.0/api_qpay_apply.cgi";

    // 银行卡快捷支付确认调用的接口名
    public static String bankCardPayComfirmApi = hostName + "/cgi-bin/v2.0/api_qpay_confirm.cgi";

    // 银行卡快捷解约调用的接口名
    public static String qsignCancelApi = hostName + "/cgi-bin/v2.0/api_qsign_cancel.cgi";

    // 支付结果单笔查询调用的接口名
    public static String orderQueryApi = hostName + "/cgi-bin/v2.0/api_single_qry_order.cgi";

    // 快捷签约协议单笔查询调用的接口名
    public static String qsignQueryApi = hostName + "/cgi-bin/v2.0/api_single_qry_qsign.cgi";

    // 银行卡信息单笔查询调用的接口名
    public static String cardbinQueryApi = hostName + "/cgi-bin/v2.0/api_single_qry_cardbin.cgi";

    // 银行限额单笔查询调用的接口名
    public static String quotaQueryApi = hostName + "/cgi-bin/v2.0/api_single_qry_quota.cgi";

    // 快捷验证码短信重发调用的接口名
    public static String smsResendApi = hostName + "/cgi-bin/v2.0/api_sms_resend.cgi";

    // 单笔代付的接口名
    public static String PAY_SINGLE = hostName + "/cgi-bin/v2.0/api_pay_single.cgi";

    // 单笔代付结果查询
    public static String PAY_SINGLE_QUERY = hostName + "/cgi-bin/v2.0/api_pay_single_query.cgi";

    // 银行卡快捷签约支付申请回调地址
    public static String UNION_NOTIFY = "https://www.baidu.com/";

    //--------------------------------------------------------支付宝开始---------------------------------------------------
    // 支付申请接口名
    public static String ALIPAY_PAY_APPLY_API = hostName + "/cgi-bin/v2.0/api_ali_pay_apply.cgi";

    // 订单关闭接口名
    public static String ALIPAY_PAY_CANCEL_API = hostName + "/cgi-bin/v2.0/api_ali_pay_cancel.cgi";

    // 单笔支付单查询接口名
    public static String ALIPAY_paySingleQueryApi = hostName + "/cgi-bin/v2.0/api_ali_pay_qry.cgi";

    // 退款接口名
    public static String ALIPAY_refundApi = hostName + "/cgi-bin/v2.0/api_ali_refund.cgi";

    // 单笔退款单查询接口名
    public static String ALIPAY_refundSingleQueryApi = hostName + "/cgi-bin/v2.0/api_ali_refund_qry.cgi";

    //---------------------------------------------------------微信开始-------------------------------------------------------------
    // 支付申请接口名
    public static String WECHART_PAY_APPLY_API = hostName + "/cgi-bin/v2.0/api_wx_pay_apply.cgi";

    // 公众号支付申请接口
    public static String WECHART_SUB_PAY_APPLY_API = hostName + "/cgi-bin/v2.0/api_wx_subpay_apply.cgi";

    // 订单关闭接口名
    public static String WECHART_PAY_CANCEL_API = hostName + "/cgi-bin/v2.0/api_wx_pay_cancel.cgi";

    // 单笔支付单查询接口名
    public static String WECHART_SINGLE_QUERY_API = hostName + "/cgi-bin/v2.0/api_wx_pay_single_qry.cgi";

    // 批量支付单查询接口名
    public static String WECHART_BATCH_QUERY_API = hostName + "/cgi-bin/v2.0/api_wx_pay_batch_qry.cgi";

    // 退款接口名
    public static String WECHART_REFUND_API = hostName + "/cgi-bin/v2.0/api_wx_refund.cgi";

    // 单笔退款单查询接口名
    public static String WECHART_REFUND_SINGLE_QUERY_API = hostName + "/cgi-bin/v2.0/api_wx_refund_single_qry.cgi";

    // 批量退款单查询接口名
    public static String WECHART_REFUND_BATCH_QUERY_API = hostName + "/cgi-bin/v2.0/api_wx_refund_batch_qry.cgi";

    // 下载账单接口名
    public static String WECHART_DOWNLOAD_BILL_API = hostName + "/cgi-bin/v2.0/api_wx_pay_downloadbill.cgi";


    //---------------------------------------------------------代付开始-------------------------------------------------------------
    //代付申请接口名
    public static String PAY_FOR_ANOTHER_APPLY_API = hostName + "/cgi-bin/v2.0/api_pay_single.cgi";
}