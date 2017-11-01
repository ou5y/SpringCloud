package com.azcx9d.agency.dao;

import java.util.List;
import java.util.Map;

import com.azcx9d.agency.entity.Exchange;

public interface ExchangeDao {
	
	/**
	 * 查询兑换记录列表
	 * @param params
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryExchangeList(Map<String,Object> params);
	
	/**
	 * 查询善点明细
	 * @param params
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryBalance(Map<String,Object> params);
	
	/**
	 * 查询兑换记录相信信息
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
	int saveExchange(Map<String,Object> params);
	
	/**
	 * 保存消费者对账表
	 * @param params
	 * @return int
	 */
	int saveCustomBill(Map<String,Object> params);
	
	/**
	 * 统计兑换记录总条数
	 * @param params
	 * @return int
	 */
	int countExchange(Map<String,Object> params);
	
	/**
	 * 统计兑换明细总条数 
	 * @param params
	 * @return int
	 */
	int countBalance(Map<String,Object> params);

}
