package com.azcx9d.agency.service;

import java.util.List;
import java.util.Map;

import com.azcx9d.agency.entity.AgencyApplyRecord;
import com.azcx9d.agency.entity.Area;

/**
 * 
 * @author fby
 *
 */
public interface AgentJoinService {
	
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
	
	int toBeAreaAgent(Map<String,Object> params) throws Exception;
	
}
