package com.azcx9d.user.dao;

import java.util.List;
import java.util.Map;

import com.azcx9d.user.entity.Region;



public interface RegionDao {
	
	/**
	 * 根据编码查找区域
	 * @param code
	 * @return Region
	 */
	Region findByCode(int code);
	
	/**
	 * 根据用户id查找全部代理区域
	 * @param userId
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> findAreaByUserId(long userId);
	
	/**
	 * 根据行业id查找行业名称
	 * @param tradeId
	 * @return String
	 */
	String findTradeName(int tradeId);

}
