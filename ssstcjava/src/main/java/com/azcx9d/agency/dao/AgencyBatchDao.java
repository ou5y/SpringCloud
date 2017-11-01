package com.azcx9d.agency.dao;

import java.util.List;
import java.util.Map;

import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.Trade;
import com.azcx9d.user.entity.Region;

public interface AgencyBatchDao {
	
	/**
	 * 根据名称查询区域
	 * @param name
	 * @return Region
	 */
	List<Region> findRegionByName(String name);
	
	/**
	 * 根据code查找地区
	 * @param name
	 * @return Region
	 */
	Region findRegionByCode(String code);
	
	/**
	 * 根据电话号码查找
	 * @param params	参数集合
	 * @return Agency
	 */
	Agency findAgencyByPhone(Map<String,Object> params);
	
	/**
	 * 根据名称查找行业
	 * @param name
	 * @return Trade
	 */
	Trade findTradeByName(String name);
	
	/**
	 * 新增代理商
	 * @param params
	 * @return int 受影响行数
	 */
	int addAgency(Map<String,Object> params);
	
	/**
	 * 新增代理商用户关系
	 * @param params
	 * @return int 受影响行数
	 */
	int addUserAgency(Map<String,String> params);
	
	/**
	 * 查询服务商是否已经存在
	 * @param params
	 * @return int
	 */
	int findUserAgencyExist(Map<String,String> params);

	/**
	 *
	 * @param userAgency
	 * @return 结果
	 */
	int updateUserTypeAndLevel(Map<String, String> userAgency);

}
