package com.azcx9d.agency.dao;


import java.util.List;
import java.util.Map;

import com.azcx9d.agency.entity.AgencyApplyRecord;
import com.azcx9d.agency.entity.Area;

/**
 * 代理加盟
 * @author fby
 *
 */
public interface AgentJoinDao {
	/**
	 * 区域代理列表
	 * @return
	 */
	List<Area> areaList(Map<String,String> params) throws Exception;
	
	/**
	 * 
	 * @return
	 */
	List<Area> selectAreaByName(String name) throws Exception;
	
	/**
	 * 代理
	 * @param area
	 * @return
	 */
	int insertAgentArea(AgencyApplyRecord record) throws Exception;
	
	/**
	 * 插入代理关系表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertAgentArea(Map<String,Object> params) throws Exception;
	
	/**
	 * 更新用户表区域编号及level
	 * @param params
	 * @return int 受影响行数
	 */
	int updateAgent(Map<String,Object> params);
}
