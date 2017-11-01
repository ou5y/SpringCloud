package com.azcx9d.agency.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import com.azcx9d.agency.dao.AgentJoinDao;
import com.azcx9d.agency.entity.AgencyApplyRecord;
import com.azcx9d.agency.entity.Area;
import com.azcx9d.agency.service.AgentJoinService;

@Service("AgentJoinService")
@Transactional
public class AgentJoinServiceImpl implements AgentJoinService {

	public Logger logger = LoggerFactory.getLogger(AgentJoinServiceImpl.class);
	@Autowired
	private AgentJoinDao dao;
	
	
	@Override
	public int toBeAreaAgent(Map<String,Object> params) throws Exception{
		int count = dao.insertAgentArea(params);
		if(count>0){
			count = dao.updateAgent(params);
		}
		return count;
	}


	@Override
	public List<Area> areaList(Map<String,String> params) throws Exception{
		
		return  dao.areaList(params);
	}


	@Override
	public List<Area> selectAreaByName(String name) throws Exception{
		
		return dao.selectAreaByName(name);
	}

}
