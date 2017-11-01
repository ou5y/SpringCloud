package com.azcx9d.pay.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Configs {
    private static Log log = LogFactory.getLog(Configs.class);
    private static Configuration configs;
    private static String openApiDomain;
    private static String mcloudApiDomain;
    private static String createandpayApiDomain;
    private static String tradeQuery;
    private static String pid;
    private static String appid;
    private static String privateKey;
    private static String publicKey;
    private static String nbpayPublicKey;
    private static String signType;
    private static int maxQueryRetry;
    private static long queryDuration;
    private static int maxCancelRetry;
    private static long cancelDuration;
    private static long heartbeatDelay;
    private static long heartbeatDuration;
    private static String orderSettlementUrl;
    private static String agentNumber;

    private Configs() {
    }

    public static synchronized void init(String filePath) {
        if(configs == null) {
            try {
                configs = new PropertiesConfiguration(filePath);
            } catch (ConfigurationException var2) {
                var2.printStackTrace();
            }

            if(configs == null) {
                throw new IllegalStateException("can`t find file by path:" + filePath);
            } else {
                openApiDomain = configs.getString("open_api_domain");
                mcloudApiDomain = configs.getString("mcloud_api_domain");
                createandpayApiDomain = configs.getString("createandpay_api_domain");
                tradeQuery = configs.getString("trade_query");
                pid = configs.getString("pid");
                appid = configs.getString("appid");
                privateKey = configs.getString("private_key");
                publicKey = configs.getString("public_key");
                nbpayPublicKey = configs.getString("nbpay_public_key");
                signType = configs.getString("sign_type");
                maxQueryRetry = configs.getInt("max_query_retry");
                queryDuration = configs.getLong("query_duration");
                maxCancelRetry = configs.getInt("max_cancel_retry");
                cancelDuration = configs.getLong("cancel_duration");
                heartbeatDelay = configs.getLong("heartbeat_delay");
                heartbeatDuration = configs.getLong("heartbeat_duration");
                orderSettlementUrl = configs.getString("order_settlement_url");
                agentNumber = configs.getString("agentNumber");
                log.info("配置文件名: " + filePath);
                log.info(description());
            }
        }
    }

    public static String description() {
        StringBuilder sb = new StringBuilder("Configs{");
        sb.append("nbpayopenapi网关: ").append(openApiDomain).append("\n");
        if(StringUtils.isNotEmpty(mcloudApiDomain)) {
            sb.append(", nbpaymcloudapi网关域名: ").append(mcloudApiDomain).append("\n");
        }

        if(StringUtils.isNotEmpty(createandpayApiDomain)) {
            sb.append(", createandpayApiDomain网关域名: ").append(createandpayApiDomain).append("\n");
        }

        if(StringUtils.isNotEmpty(tradeQuery)) {
            sb.append(", tradeQuery网关域名: ").append(tradeQuery).append("\n");
        }

        if(StringUtils.isNotEmpty(orderSettlementUrl)) {
            sb.append(", orderSettlementUrl网关域名: ").append(orderSettlementUrl).append("\n");
        }

        if(StringUtils.isNotEmpty(pid)) {
            sb.append(", pid: ").append(pid).append("\n");
        }

        sb.append(", appid: ").append(appid).append("\n");
        sb.append(", 商户RSA私钥: ").append(getKeyDescription(privateKey)).append("\n");
        sb.append(", 商户RSA公钥: ").append(getKeyDescription(publicKey)).append("\n");
        sb.append(", nbpayRSA公钥: ").append(getKeyDescription(nbpayPublicKey)).append("\n");
        sb.append(", 签名类型: ").append(signType).append("\n");
        sb.append(", 查询重试次数: ").append(maxQueryRetry).append("\n");
        sb.append(", 查询间隔(毫秒): ").append(queryDuration).append("\n");
        sb.append(", 撤销尝试次数: ").append(maxCancelRetry).append("\n");
        sb.append(", 撤销重试间隔(毫秒): ").append(cancelDuration).append("\n");
        sb.append(", 交易保障调度延迟(秒): ").append(heartbeatDelay).append("\n");
        sb.append(", 交易保障调度间隔(秒): ").append(heartbeatDuration).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private static String getKeyDescription(String key) {
        int showLength = 6;
        return StringUtils.isNotEmpty(key) && key.length() > showLength?key.substring(0, showLength) + "******" + key.substring(key.length() - showLength):null;
    }

    public static Configuration getConfigs() {
        return configs;
    }

    public static String getOpenApiDomain() {
        return openApiDomain;
    }

    public static String getMcloudApiDomain() {
        return mcloudApiDomain;
    }

    public static void setMcloudApiDomain(String mcloudApiDomain) {
        mcloudApiDomain = mcloudApiDomain;
    }

    public static String getPid() {
        return pid;
    }

    public static String getAppid() {
        return appid;
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static String getNbpayPublicKey() {
        return nbpayPublicKey;
    }

    public static String getSignType() {
        return signType;
    }

    public static int getMaxQueryRetry() {
        return maxQueryRetry;
    }

    public static long getQueryDuration() {
        return queryDuration;
    }

    public static int getMaxCancelRetry() {
        return maxCancelRetry;
    }

    public static long getCancelDuration() {
        return cancelDuration;
    }

    public static void setConfigs(Configuration configs) {
        configs = configs;
    }

    public static void setOpenApiDomain(String openApiDomain) {
        openApiDomain = openApiDomain;
    }

    public static void setPid(String pid) {
        pid = pid;
    }

    public static void setAppid(String appid) {
        appid = appid;
    }

    public static void setPrivateKey(String privateKey) {
        privateKey = privateKey;
    }

    public static void setPublicKey(String publicKey) {
        publicKey = publicKey;
    }

    public static void setAlipayPublicKey(String alipayPublicKey) {
        alipayPublicKey = alipayPublicKey;
    }

    public static void setSignType(String signType) {
        signType = signType;
    }

    public static void setMaxQueryRetry(int maxQueryRetry) {
        maxQueryRetry = maxQueryRetry;
    }

    public static void setQueryDuration(long queryDuration) {
        queryDuration = queryDuration;
    }

    public static void setMaxCancelRetry(int maxCancelRetry) {
        maxCancelRetry = maxCancelRetry;
    }

    public static void setCancelDuration(long cancelDuration) {
        cancelDuration = cancelDuration;
    }

    public static long getHeartbeatDelay() {
        return heartbeatDelay;
    }

    public static void setHeartbeatDelay(long heartbeatDelay) {
        heartbeatDelay = heartbeatDelay;
    }

    public static long getHeartbeatDuration() {
        return heartbeatDuration;
    }

    public static void setHeartbeatDuration(long heartbeatDuration) {
        heartbeatDuration = heartbeatDuration;
    }

    public static String getCreateandpayApiDomain() {
        return createandpayApiDomain;
    }

    public static void setCreateandpayApiDomain(String createandpayApiDomain) {
        Configs.createandpayApiDomain = createandpayApiDomain;
    }

    public static String getTradeQuery() {
        return tradeQuery;
    }

    public static void setTradeQuery(String tradeQuery) {
        Configs.tradeQuery = tradeQuery;
    }

    public static String getOrderSettlementUrl() {
        return orderSettlementUrl;
    }

    public static void setOrderSettlementUrl(String orderSettlementUrl) {
        Configs.orderSettlementUrl = orderSettlementUrl;
    }

    public static String getAgentNumber() {
        return agentNumber;
    }

    public static void setAgentNumber(String agentNumber) {
        Configs.agentNumber = agentNumber;
    }
}

