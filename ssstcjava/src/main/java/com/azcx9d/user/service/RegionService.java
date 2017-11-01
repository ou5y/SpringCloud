package com.azcx9d.user.service;

import java.util.List;
import java.util.Map;

import com.azcx9d.user.entity.Region;

public interface RegionService {
	
	/**
	 * 根据code查找区域
	 * @param code
	 * @return Region
	 */
	Region findByCode(int code);
	
	/**
	 * 根据区域编码查找完整地区名称
	 * @param code		编码
	 * @param fullName	全名
	 * @return String
	 */
	StringBuffer findFullName(int code,StringBuffer fullName);
	
	/**
	 * 根据用户id查找全部代理区域
	 * @param userId
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> findAreaByUserId(long userId);
	
	/**
	 * 根据用户id查找全部代理区域全称
	 * @param userId
	 * @return List<String>
	 */
	List<String> findAllNames(long userId);

}
