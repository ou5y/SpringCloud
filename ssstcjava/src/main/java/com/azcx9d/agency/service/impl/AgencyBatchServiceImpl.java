package com.azcx9d.agency.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.azcx9d.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azcx9d.agency.dao.AgencyBatchDao;
import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.Trade;
import com.azcx9d.agency.service.AgencyBatchService;
import com.azcx9d.common.util.Arith;
import com.azcx9d.system.util.ReadExcelUtils;
import com.azcx9d.user.entity.Region;
import com.mysql.jdbc.StringUtils;
import org.springframework.transaction.annotation.Transactional;

@Service("AgencyBatchService")
@Transactional
public class AgencyBatchServiceImpl implements AgencyBatchService {
	private Logger logger = LoggerFactory.getLogger(AgencyBatchServiceImpl.class);
	
	@Autowired
	private AgencyBatchDao agencyBatchDao;
	
	// 批量添加代理商
	@Override
	public Map<String, List<String>> saveAgencyBatch(InputStream inputStream,String originalFilename) throws Exception {
		Map<String, List<String>> result = new HashMap<String, List<String>>(0);
		List<String> successList = new ArrayList<String>(0);		//成功导入
		List<String> failedList = new ArrayList<String>(0);		//导入失败
		List<User> businessList = new ArrayList<User>();
		ReadExcelUtils excelReader = new ReadExcelUtils(inputStream,originalFilename);
		int total = 0;
		// 对读取Excel表格内容测试
		Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();

		for (int i = 1; i < map.size(); i++) {
			Map<Integer,Object> rowContent = map.get(i+1);
			if(i>0){
				StringBuffer desc = new StringBuffer();
				Map<String,String> userAgency = new HashMap<String, String>(0);
				logger.error(rowContent.toString());
				String phone = ((String) rowContent.get(6)).trim();						//手机号码
				
				Map<String,Object> agenParam = new HashMap<String, Object>(0);
				agenParam.put("phone", phone.trim());

				
				/*****************************查询代理商开始********************************/
				Agency agency = agencyBatchDao.findAgencyByPhone(agenParam);
				if(agency!=null){
					if (agency.getUserType()==0)
					{
						userAgency.put("userId", agency.getId()+"");
						userAgency.put("userType", 2+"");
						agencyBatchDao.updateUserTypeAndLevel(userAgency);//身份变更
					}
					else if(agency.getUserType()==2)
					{
						userAgency.put("userId", agency.getId()+"");
					}
					else if(agency.getUserType()==1)
					{
						logger.error("商家:"+agency.getId()+"不能成为服务商");
						businessList.add(agency);
						continue;
					}
					else
					{
						logger.error("用户类型错误！不存在这类型的用户");
					}
				}else{
//					String userName = (String) rowContent.get(5);						//用户名称
//					agenParam.put("name", userName.trim());
//					int count = agencyBatchDao.addAgency(agenParam);
//					if(count>0){
//						desc.append("新增用户"+phone+"成功");
//						agency = agencyBatchDao.findAgencyByPhone(agenParam);
//						if(agency!=null) userAgency.put("userId", agency.getId()+"");
//						else desc.append("查询"+phone+"失败");
//					}else{
//						desc.append("新增用户"+phone+"失败");
//						failedList.add(i+desc.toString());
//						continue;
//					}
					desc.append("手机号码:"+phone+"不存在");
					failedList.add(i+desc.toString());
					continue;
				}
				/*****************************查询代理商结束********************************/
				
				
				/*****************************查询所属区域开始********************************/
				String regionName = (String) rowContent.get(3);		//区域名称
				List<Region> regionLists = agencyBatchDao.findRegionByName(regionName);
				if(regionLists!=null &&regionLists.size()>0){
					   if(regionLists.size()==1){
						   Region region = regionLists.get(0);
						   userAgency.put("areaId", region.getCode()+"");
					   }else{
						   String cityName = (String) rowContent.get(2);	//市
						   for(int j=0;j<regionLists.size();j++){
							   Region region = regionLists.get(j);
							   Region city = agencyBatchDao.findRegionByCode(region.getParentCode()+"");
							   if(city.getFullName().equals(cityName)){
								   userAgency.put("areaId", region.getCode()+"");
								   break;
							   }
						   }
					   }
				}else{
					desc.append("未找到匹配的地区："+regionName);
					failedList.add(i+desc.toString());
					continue;
				}
				/*****************************查询所属区域结束********************************/
				
				
				/*****************************查询所属行业开始********************************/
				String tradeName = (String) rowContent.get(4);		//行业名称
				if(!StringUtils.isNullOrEmpty(tradeName)){
					Trade trade = agencyBatchDao.findTradeByName(tradeName);
					if(trade!=null){
						userAgency.put("tradeId", trade.getId()+"");
					}else{
						desc.append("未找到匹配的行业："+tradeName);
						failedList.add(i+desc.toString());
						continue;
					}
				}else{
					userAgency.put("tradeId", "0");
				}
				/*****************************查询所属行业结束********************************/

				int isExist = agencyBatchDao.findUserAgencyExist(userAgency);
				if(isExist<=0){
					userAgency.put("percent", (String)rowContent.get(7));
					int count = agencyBatchDao.addUserAgency(userAgency);
					if(count>0){
						desc.append("手机号码："+phone+",区域："+regionName+",行业："+tradeName+",添加用户代理关系成功");
						successList.add(i+desc.toString());
						total=total+1;
					}else{
						desc.append("手机号码："+phone+",区域："+regionName+",行业："+tradeName+",添加用户代理关系失败");
						failedList.add(i+desc.toString());
					}
				}else{
					desc.append("服务商已经存在,手机号码："+phone+",区域："+regionName+",行业："+tradeName);
					failedList.add(i+desc.toString());
				}
			}
		}
		result.put("success",successList);
		result.put("failed",failedList);
		System.out.println("----------------------"+businessList.size());
		for (User user:businessList
			 ) {
			System.out.println("商家:"+user.getPhone());
		}
		return result;
	}

}
