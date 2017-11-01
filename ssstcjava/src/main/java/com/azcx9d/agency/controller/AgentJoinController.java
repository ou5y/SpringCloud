package com.azcx9d.agency.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.azcx9d.user.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.azcx9d.agency.entity.AgencyApplyRecord;
import com.azcx9d.agency.entity.Area;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.agency.service.AgentJoinService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author fby
 *
 */
@Api(value = "代理加盟",description="代理加盟控制器")
@RestController
@RequestMapping(value="/area")
public class AgentJoinController extends BaseController{
	
	 @Autowired
	 private AgentJoinService agentJoinService;

	@Autowired
	private RegionService regionService;
	 
	 @ApiOperation(value = "区域列表", notes = "区域列表,parentCode默认=100000表示获取省份，state:  0-未被代理,1-已被代理")
	 @RequestMapping(value = "/{parentCode}/list/v1", method = RequestMethod.GET)
	 public JsonResult<List<Area>>  list(@PathVariable("parentCode") String parentCode){
		 String token = request.getHeader("token");
			TokenModel model=tokenDao.getToken(token);
			if(!tokenDao.checkToken(model))
				return new JsonResult(1,"账号身份已过期，请重新登录");
		 List<Area> list =null;
		try {
			Map<String,String> params = new HashMap<String,String>();
			params.put("parentCode", parentCode);
			list = agentJoinService.areaList(params);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return  new JsonResult<>(500,"服务器出错！");
		}
		
		 return new JsonResult<List<Area>>(list);
	 }
	 
	 @ApiOperation(notes = "列表详情", httpMethod = "GET", value = "根据名称模糊查询区域")
	 @RequestMapping(value="/{name}/query/v1")
	 public JsonResult<List<Area>> queryArea(@PathVariable("name") String name)
	 {
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		if(!tokenDao.checkToken(model))
			return new JsonResult(1,"账号身份已过期，请重新登录");
		 List<Area> list =null;
		try {
			list = agentJoinService.selectAreaByName(name);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return  new JsonResult<>(500,"服务器出错！");
		}
		 
		 return new JsonResult<List<Area>>(list);
	 }
	 
	 @ApiOperation(notes = "我要代理", value = "成为区域代理")
	 @RequestMapping(value="{areaId}/add/v1",method=RequestMethod.POST)
	 public JsonResult toBeAreaAgent(@ApiParam(required = true,value="区域编号") @RequestParam long areaId){
		 String token = request.getHeader("token");
		 TokenModel model=tokenDao.getToken(token);
		 if(!tokenDao.checkToken(model))
				return new JsonResult(1,"账号身份已过期，请重新登录");
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", 19);
			params.put("areaId", areaId);
			int result = agentJoinService.toBeAreaAgent(params);
			if (result > 0){
				return new JsonResult(0, "代理成功");
			}else {
				return new JsonResult(1, "代理失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500, "服务器出错！");
		}
	 }

	 @ApiOperation(value ="查询服务商",notes = "服务商,有数据：{\n" +
			 "  \"status\": 0,\n" +
			 "  \"message\": \"查询服务商成功\",\n" +
			 "  \"data\": [\n" +
			 "    \"内蒙古自治区乌兰察布市集宁区\",\n" +
			 "    \"四川省遂宁市酒店\",\n" +
			 "    \"四川省遂宁市建材\",\n" +
			 "    \"四川省南充市西充县\"\n" +
			 "  ]\n" +
			 "}，<br/>无数据：{\n" +
			 "  \"status\": 0,\n" +
			 "  \"message\": \"查询服务商成功\",\n" +
			 "  \"data\": []\n" +
			 "}")
	 @RequestMapping(value = "/queryAgencyList/v1",method = RequestMethod.GET)
	 public JsonResult queryAgencyList(@RequestHeader("token") String token){
		 TokenModel model=tokenDao.getToken(token);
		 if(!tokenDao.checkToken(model))
			 return new JsonResult(1,"账号身份已过期，请重新登录");
		 try {
		 	List<String> fullNames =  regionService.findAllNames(model.getUserId());
		 	return new JsonResult(0,"查询服务商成功",fullNames);
		 } catch (Exception e) {
		 	logger.error(e.getMessage());
		 	return new JsonResult(500, "服务器出错！");
		 }
	 }
	 
//	 @ApiOperation(value = "全部区域", notes = "全部区域")
//	 @RequestMapping(value="/queryAll",method=RequestMethod.POST)
//	 public JsonResult<List<Area>> queryAll(){
//		 List<Area> list =null;
//			try {
//				list = service.areaList(null);
//				JSONArray all = new JSONArray();			// 省
//				for(int i =0;i<list.size();i++){
//					System.out.println("i:"+i);
//					Area area1 = list.get(i);
//					if(area1.getParentCode().equals("100000")){
//						JSONObject province = new JSONObject();
//						province.put("code", area1.getCode());
//						province.put("parentCode", area1.getParentCode());
//						province.put("name", area1.getFullName());
//						JSONArray cityarr = new JSONArray();		//市
//						
//						out1:for(int j=i+1;j<list.size();j++){
//							System.out.println("j:"+j);
//							Area area2 = list.get(j);           // 市
//							System.out.println("area2:"+JSONObject.toJSONString(area2));
//							if(area2.getParentCode().equals(area1.getCode())){
//								JSONObject cityObj = new JSONObject();
//								cityObj.put("code", area2.getCode());
//								cityObj.put("parentCode", area2.getParentCode());
//								cityObj.put("name", area2.getFullName());
//								
//								JSONArray areaArr = new JSONArray();		//市
//								out2:for(int k=j+1;k<list.size();k++){
//									System.out.println("k:"+k);
//									Area area3 = list.get(k);		//区
//									System.out.println("area3:"+JSONObject.toJSONString(area3));
//									if(area3.getParentCode().equals(area2.getCode())){
//										JSONObject arrOb = new JSONObject();
//										arrOb.put("code", area3.getCode());
//										arrOb.put("parentCode", area3.getParentCode());
//										arrOb.put("name", area3.getFullName());
//										
//										areaArr.add(arrOb);
//									}else{
//										cityObj.put("area", areaArr);
//										cityarr.add(cityObj);
//										province.put("city", cityarr);
//										if(area3.getParentCode().equals(area2.getParentCode())){
//											j=k-1;
//											break out2;
//										}else{
//											all.add(province);
//											i=k-1;
//											break out1;
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//				System.out.println(JSONObject.toJSONString(all));
//			} catch (Exception e) {
//				logger.error(e.getMessage());
//				return  new JsonResult<>(500,"服务器出错！");
//			}
//		 return new JsonResult<List<Area>>(list);
//	 }
	 
}
