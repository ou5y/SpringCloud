package com.azcx9d.agency.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.azcx9d.agency.dao.BusinessDao;
import com.azcx9d.agency.entity.Business;
import com.azcx9d.agency.service.BusinessService;
import com.azcx9d.business.util.DateUtil;
import com.azcx9d.business.util.FileUpload;
import com.azcx9d.common.util.Page;
import com.azcx9d.common.util.RandomUtil;
import com.azcx9d.common.util.SystemConfig;

/** create by jesse 2017/3/16
 * */
@Service("businessService")
@Transactional
public class BusinessServiceImpl implements BusinessService{
	
    @Autowired
    private BusinessDao dao;
    
    // 保存商家
    @Override
    public int addBusiness(Business business,CommonsMultipartFile files[]) throws Exception{
    	for (int i = 0; i < files.length; i++) {
			CommonsMultipartFile file = files[i];
			if (file != null) {
				
				String  ffile = DateUtil.getDays(), fileName = "";
				
				String filePath = SystemConfig.getProperty("picture","business","phycial") + ffile;
				fileName = FileUpload.fileUp(file, filePath, RandomUtil.get32UUID());				//执行上传
				
				String relativePath = SystemConfig.getProperty("picture","business","url") + ffile + "/" + fileName;	//文件读取路径

				switch (i) {
				case 0:
					business.setBusinessPhoto(relativePath);
					break;
				case 1:
					business.setBusinessLicense(relativePath);
					break;
				case 2:
					business.setHoleIdCardPhoto(relativePath);
					break;
				case 3:
					business.setBusinessWord(relativePath);
					break;
				case 4:
					business.setPromoterWord(relativePath);
					break;
				default:
					break;
				}
			}
		}
    	return dao.addBusiness(business);
    }
    
    // 更新商家信息
    @Override
    public int updateBusiness(Business business,CommonsMultipartFile files[])throws Exception{
    	for (int i = 0; i < files.length; i++) {
			CommonsMultipartFile file = files[i];
			if (file != null) {
				
				String  ffile = DateUtil.getDays(), fileName = "";
				
				String filePath = SystemConfig.getProperty("picture","business","phycial") + ffile;
				fileName = FileUpload.fileUp(file, filePath, RandomUtil.get32UUID());				//执行上传
				
				String relativePath = SystemConfig.getProperty("picture","business","url") + ffile + "/" + fileName;	//文件读取路径

				switch (i) {
				case 0:
					business.setBusinessPhoto(relativePath);
					break;
				case 1:
					business.setBusinessLicense(relativePath);
					break;
				case 2:
					business.setHoleIdCardPhoto(relativePath);
					break;
				case 3:
					business.setBusinessWord(relativePath);
					break;
				case 4:
					business.setPromoterWord(relativePath);
					break;
				default:
					break;
				}
			}
		}
    	return dao.updateBusiness(business);
    }
    
    public List<HashMap<String, Object>> getBusinessType(){
    	return dao.getBusinessType();
    } 
    
    public List<Business> getBusinessList(HashMap<String, String> params){
    	return dao.getBusinessList(params);
    }
    
	public int updateBusinessState(long state,long id){
		return dao.updateBusinessState(state,id);
	}
	
	public Business queryDetail(HashMap<String, String> params){
		return dao.queryDetail(params);
	}
	
	public List<String> queryAreaName(HashMap<String, String> params){
		return dao.queryAreaName(params);
	}
	
	// 分页查询商家列表
	public Page getBusinessByPage(Page page,HashMap<String, Object> params,Integer pageIndex,Integer pageSize){
		page.setPageSize(pageSize);
		int totalRow = dao.countTotal(params);
		page.setTotalRow(totalRow);
		params.put("offset", page.getOffset());
		params.put("pageSize", pageSize);
		List<Business> list = dao.getBusinessByPage(params);
		page.setPageList(list);
		return page;
	}
}
