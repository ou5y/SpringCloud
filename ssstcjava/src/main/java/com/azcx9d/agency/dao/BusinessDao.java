package com.azcx9d.agency.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.azcx9d.agency.entity.Business;
import com.azcx9d.common.util.Page;

/** create by jesse 2017/3/16
 * */
public interface BusinessDao {
	int addBusiness(Business business);
	
	int updateBusiness(Business business);
	
	List<HashMap<String, Object>> getBusinessType(); 
	
	List<Business> getBusinessList(HashMap<String, String> params);
	
	/**
	 * 根据id更新商家状态
	 * @param params
	 * @return 受影响行数
	 */
	int updateBusinessState(long state,long id);
	
	/**
	 * 根据id查询商家相信信息
	 * @param id 商家id，必填
	 * @return Business
	 */
	Business queryDetail(HashMap<String, String> params);
	
	/**
	 * 查询省市区对应中文名称
	 * @param params
	 * @return List<String>
	 */
	List<String> queryAreaName(HashMap<String, String> params);
	
	/**
	 * 统计总条数
	 * @param params
	 * @return int
	 */
	int countTotal(Map<String, Object> params);
	
	/**
	 * 商家分页
	 * @param params
	 * @return List<Business>
	 */
	List<Business> getBusinessByPage(Map<String, Object> params);
}
