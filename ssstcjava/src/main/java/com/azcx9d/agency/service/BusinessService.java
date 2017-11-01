package com.azcx9d.agency.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.azcx9d.agency.entity.Business;
import com.azcx9d.common.util.Page;

/**
 * 
 * @author jesse 2017-3-16
 *
 */
public interface BusinessService {
	
	int addBusiness(Business business,CommonsMultipartFile files[]) throws Exception;
	
	int updateBusiness(Business business,CommonsMultipartFile files[]) throws Exception;
	
	List<HashMap<String, Object>> getBusinessType(); 
	
	List<Business> getBusinessList(HashMap<String, String> params);
	
	/**
	 * 根据id更新商家状态
	 * @param state 状态
	 * @param id	商家id
	 * @return 受影响行数
	 */
	int updateBusinessState(long state,long id);
	
	/**
	 * 根据id查询商家相信信息
	 * @param params 商家id，必填
	 * @return Business
	 */
	Business queryDetail(HashMap<String, String> params);
	
	/**
	 * 查询省市区对应中文名称
	 * @param params
	 * @return List<String, Object>
	 */
	List<String> queryAreaName(HashMap<String, String> params);
	
	/**
	 * 分页查询商家列表
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return Page 分页对象
	 */
	Page getBusinessByPage(Page page,HashMap<String, Object> params,Integer pageIndex,Integer pageSize);
}
