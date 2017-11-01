package com.azcx9d.agency.service;

import java.util.Map;

import com.azcx9d.agency.entity.Exchange;
import com.azcx9d.common.util.Page;

public interface ExchangeService {
	
	/**
	 * 查询善点明细
	 * @param params
	 * @return List<Map<String,Object>>
	 */
	Page queryBalanceDetail(Page page,Map<String,Object> params);
	
	/**
	 * 查询兑换详情列表
	 * @param params
	 * @return List<Map<String,Object>>
	 */
	Page queryExchangeList(Page page,Map<String,Object> params);
	
	/**
	 * 查询兑换记录详情
	 * @param params
	 * @return Exchange
	 */
	Exchange queryDetail(Map<String,Object> params);
	
	/**
	 * 统计总兑换金额
	 * @param params
	 * @return double
	 */
	double countTotalConversion(Map<String,Object> params);
	
	/**
	 * 查询登录用户的善点
	 * @param params
	 * @return Map<String,Object>
	 */
	Map<String,Object> queryMyShandian(Map<String,Object> params);
	
	/**
	 * 查询大盘善心比例
	 * @return
	 */
	Map<String,Object> queryLovePercent();
	
	/**
	 * 保存兑换记录
	 * @param params
	 * @return int
	 */
	int saveExchange(Map<String,Object> params) throws Exception;
	
	/**
	 * 计算兑换金额
	 * @param params
	 * @return Map<String,Object>
	 */
	Map<String,Object> calcShandian(Map<String,Object> params,int type) throws Exception;
	
}
