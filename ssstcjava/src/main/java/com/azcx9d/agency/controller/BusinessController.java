package com.azcx9d.agency.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.azcx9d.agency.entity.Business;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.agency.entity.Trade;
import com.azcx9d.agency.service.BusinessService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.BaseController;
import com.azcx9d.user.service.UserApiService;
import com.mysql.jdbc.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

/**create by jesse 2017/3/16
 * 上传商家
 * */


@Api(value = "商家管理",description="商家管理")
@RestController
@RequestMapping(value="/business")
public class BusinessController extends BaseController{
	
    @Autowired
    private BusinessService businessService;
    
    @Autowired
    private UserApiService userApiService;
    
	
    @ApiOperation(value = "上传商家", notes = "上传商家")
	@RequestMapping(value = "/addBusiness/v1",method = RequestMethod.POST)
    public JsonResult addBusiness(@RequestParam("files") CommonsMultipartFile files[], Business business ){
    	JsonResult<Object> result = null;
    	String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		
		if(!tokenDao.checkToken(model))
			return new JsonResult(1,"账号身份已过期，请重新登录");
		
		try{
			if(StringUtils.isNullOrEmpty(business.getPhone())) return new JsonResult(1, "请填写商户手机号码");
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("phone", business.getPhone());
//			business.setMaxAmount(business.getMaxAmount()!=null?business.getMaxAmount():new BigDecimal(20000));		//设置默认封顶金额为20000

			int count = 0;
			Map<String,Object> info = userApiService.selectByPhone(params);			//查询用户id
			business.setUploadUser(model.getUserId()+"");
			
			if(info==null || info.size()<=0){
				// 用户不存在
//				int saveResult = userApiService.saveUser(params);		//保存用户
//				if(saveResult>0){
//					info = userApiService.selectByPhone(params);
//					business.setUserId(info.get("id").toString());
//				}else{
//					if(business.getId() ==0){
//						return new JsonResult<Object>(1, "上传商户失败");
//					}else{
//						return new JsonResult<Object>(1, "修改商户信息失败");
//					}
//				}
				return new JsonResult<Object>(1, "该商家手机号未注册，请先注册");
			}else if(info.get("userType").toString().equals("2")){
				//  代理商不可上传店铺，用户可在审核时变更为商家
				return new JsonResult<Object>(1, "该商家手机号已经是代理商");
			}else{
				business.setUserId(info.get("id").toString());
			}
			
			if(business.getId() ==0){
				// 新增
				count = businessService.addBusiness(business,files);
				if (count > 0) {
					return new JsonResult<Object>(0, "上传商家成功", business);
				} else {
					return new JsonResult<Object>(1, "上传商家失败");
				}
			}else{
				// 修改
				business.setState("0");
				count = businessService.updateBusiness(business,files);
				if (count > 0) {
					return new JsonResult<Object>(0, "修改商家信息成功", business);
				} else {
					return new JsonResult<Object>(1, "修改商家信息失败");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return new JsonResult<Object>(500,"上传商户失败，请稍后重试");
		}
	}
	
//    @ApiOperation(value = "获取商家集合", notes="可根据状态筛选结果,商家状态(-1:全部,0:待审核,1:已审核,2:审核失败)<br/>totalRow:总数据记录数<br/>"
//    		+ "pageSize:每页记录数<br/>currentPage:当前页索引<br/>totalPage:总页数<br/>pageList:每页的对象数据列表<br/>totalRow:总数据记录数<br/>"
//    		+ "firstPage:是否为第一页<br/>lastPage:是否为最后一页<br/>hasPreviousPage:是否有前一页<br/>"
//    		+ "hasNextPage:是否有后一页",response=Business.class)
//	@RequestMapping(value = "/{state}/{pageIndex}/{pageSize}/getBusinessList", method = RequestMethod.GET)
//	public JsonResult<Object> getBusinessList(@PathVariable String state,
//			@ApiParam(required=true,value="页码")@PathVariable Integer pageIndex,
//			@ApiParam(required=true,value="记录数")@PathVariable Integer pageSize){
    @ApiOperation(value = "获取商家集合", notes="可根据状态筛选结果,商家状态(-1:全部,0:待审核,1:已审核,2:审核失败)",response=Business.class)
	@RequestMapping(value = "/{state}/getBusinessList/v1", method = RequestMethod.GET)
	public JsonResult<Object> getBusinessList(@ApiParam(required=true,value="商家状态")@PathVariable String state){
		JsonResult<Object> result = null;
		try{
			String token = request.getHeader("token");
			TokenModel model=tokenDao.getToken(token);
			
			if(!tokenDao.checkToken(model))
				return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
			
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("state", state);
			params.put("uploadUser", model.getUserId()+"");
			List<Business> list = businessService.getBusinessList(params);
			result = new JsonResult<Object>(0,"查询商家列表成功",list);
			
//			if(null==page){
//				page=new Page();
//			}
//			page.setPageSize(10);
//			if(null!=pageIndex)
//				page.setCurrentPage(pageIndex);
//			HashMap<String, Object> params = new HashMap<String, Object>();
//			params.put("state", state);
//			page  = businessService.getBusinessByPage(page,params,pageIndex,pageSize);
//			page.init();
//			if(page.getPageList()!=null && page.getPageList().size()>0){
//				result = new JsonResult<Object>(0,"查询商家列表成功",page);
//			}else{
//				result = new JsonResult<Object>(1,"暂无数据");
//			}
		}catch(Exception ex){
			ex.printStackTrace(); 
			result = new JsonResult<Object>(500,"查询数据出错。");
		}
        return result;
	}
    
    @ApiOperation(value = "获取商家经营类型", notes = "获取商家经营类型",response=Trade.class)
	@RequestMapping(value = "/getBusinessType/v1",method = RequestMethod.GET)
	public JsonResult<Object> getBusiness(){
		JsonResult<Object> result = null;
		try{
			String token = request.getHeader("token");
			TokenModel model=tokenDao.getToken(token);
			
			if(!tokenDao.checkToken(model))
				return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
			List<HashMap<String, Object>> list = businessService.getBusinessType();

			result = new JsonResult<Object>(0,"查询商家经营类型成功",list);
		}catch(Exception ex){
			result = new JsonResult<Object>(500,"查询数据出错。");
		}

        return result;
	}
    
    @ApiOperation(value = "商家审核", notes = "根据id更新商家状态,可做通过、拒绝操作")
	@RequestMapping(value = "/{id}/{state}/auditBusiness/v1", method = RequestMethod.POST)
	public JsonResult<Object> auditBusiness(@PathVariable long id,@PathVariable long state){
		JsonResult<Object> result = null;
		try{					
			String token = request.getHeader("token");
			TokenModel model=tokenDao.getToken(token);
			
			if(!tokenDao.checkToken(model))
				return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
			int count = businessService.updateBusinessState(state,id);
			Map<String,Long> map = new HashMap<String,Long>();
			map.put("state", state);
			if(count>0){
				result = new JsonResult<Object>(0,"商家状态修改成功",map);
			}else{
				result = new JsonResult<Object>(1,"商家状态修改失败");
			}
		}catch(Exception ex){
			result = new JsonResult<Object>(500,"查询数据出错。");
		}
        return result;
	}
    
    @ApiOperation(value = "审核详情", notes = "查看商家详细信息")
    @RequestMapping(value = "/{id}/queryDetail/v1", method= RequestMethod.GET)
    public JsonResult<Business> queryDetail(@PathVariable String id){
    	Business business = null;
    	try{					
    		String token = request.getHeader("token");
    		TokenModel model=tokenDao.getToken(token);
    		
    		if(!tokenDao.checkToken(model))
    			return new JsonResult(1,"账号身份已过期，请重新登录");
    		HashMap<String, String> params = new HashMap<String, String>(1);
    		params.put("id", id);
    		business = businessService.queryDetail(params);
    		if(business!=null){
    			HashMap<String, String> qparam = new HashMap<String, String>();
    			if(!StringUtils.isNullOrEmpty(business.getProvinceCode())){
    				qparam.put("provinceCode", business.getProvinceCode());
    			}
    			if(!StringUtils.isNullOrEmpty(business.getCityCode())){
    				qparam.put("cityCode", business.getCityCode());
    			}
    			if(!StringUtils.isNullOrEmpty(business.getAreaId())){
    				qparam.put("areaId", business.getAreaId());
    			}
    			
    			if(qparam!=null && qparam.size()>0){
	    			List<String> areaList = businessService.queryAreaName(qparam);
	    			if(areaList!=null&&areaList.size()>0){
	    				for(int i=0;i<areaList.size();i++){
	    					switch (i) {
								case 0:
									business.setCodeString(areaList.get(i));
									break;
								case 1:
									business.setCodeString((business.getCodeString()+areaList.get(i)));
									break;
								case 2:
									business.setCodeString((business.getCodeString()+areaList.get(i)));
									break;
								default:
									break;
							}
	    				}
	    			}
    			}
    		}else{
    			return new JsonResult<Business>(1,"商家不存在");
    		}
		}catch(Exception ex){
			return new JsonResult<>(500,"查询数据出错。");
		}
    	return new JsonResult<Business>(0,"查询商家详情成功",business);
    }
    
}
