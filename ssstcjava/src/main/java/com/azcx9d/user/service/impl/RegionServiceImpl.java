package com.azcx9d.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azcx9d.user.dao.RegionDao;
import com.azcx9d.user.entity.Region;
import com.azcx9d.user.service.RegionService;

@Service("RegionService")
@Transactional
public class RegionServiceImpl implements RegionService {

	/**日志对象 */
	private Logger logger = LoggerFactory.getLogger(RegionService.class);
	
	@Autowired
	private RegionDao regionDao;

	// 根据code查找区域
	@Override
	public Region findByCode(int code) {
		return regionDao.findByCode(code);
	}

	// 根据区域编码查找完整地区名称
	@Override
	public StringBuffer findFullName(int code,StringBuffer fullName) {
		Region region = regionDao.findByCode(code);
		if(region!=null){
			fullName.insert(0, region.getFullName());
			if(region.getType()!=1){
				findFullName(region.getParentCode(),fullName);
			}
		}else{
			fullName = null;
			logger.error("查询服务商未找到匹配地区："+code);
		}
		return fullName;
	}
	
	@Override
	public List<Map<String,Object>> findAreaByUserId(long userId) {
		return regionDao.findAreaByUserId(userId);
	}
	
	// 根据用户id查找全部代理区域
	@Override
	public List<String> findAllNames(long userId){
		List<String> fullNames = new ArrayList<String>(0);
		List<Map<String,Object>> areaList = regionDao.findAreaByUserId(userId);
		for(Map<String,Object> area : areaList){
			StringBuffer fullName = new StringBuffer();
			int code = (int) area.get("areaId");
			int tradeId = (int) area.get("tradeId");
			fullName = findFullName(code,fullName);
			if(fullName==null){
				continue;
			}
			if(tradeId>0){
				String tradeName = regionDao.findTradeName(tradeId);
				fullName.insert(fullName.length(), tradeName);
			}
			fullNames.add(fullName.toString());
		}
		return fullNames;
	}

}
