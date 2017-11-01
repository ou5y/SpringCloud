package com.customer.web;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.customer.dto.BusinessDetailDto;
import com.customer.dto.BusinessListDto;
import com.customer.dto.BusinessStatisticsDto;
import com.customer.dto.TradeDto;
import com.customer.entity.CBusiness;
import com.customer.entity.ParaMap;
import com.customer.enums.QuataEnum;
import com.customer.exception.CustomerException;
import com.customer.security.CheckPermission;
import com.customer.service.CBusinessService;
import com.customer.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4 0004.
 */


@Api(value = "商家信息", description = "用户端商家")
@RestController
@RequestMapping(value = "/v1/cApi/business")
public class CBusinessController extends BaseController {

    @Autowired
    private CBusinessService cBusinessService;

//    @ApiOperation(value = "查询店铺详情",notes = "根据商家id查询店铺详细信息")
//    @RequestMapping(value = "/businessDetail",method = RequestMethod.GET)
//    public JsonResult queryBusinessDetail(@RequestHeader("token") String token,
//                                          @ApiParam(required = true,value = "店铺id") @RequestParam long businessId){
//        try {
//            ParaMap paraMap = super.getParaMap();
//            paraMap.put("businessId",businessId);
//            Map<String,Object> business = cBusinessService.queryBusinessDetail(paraMap);
//            return new JsonResult(0,"查询商家详细信息成功",business);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new JsonResult(1,"查询商家失败");
//        }
//    }
    @CheckPermission
    @ApiOperation(value = "上传商家", notes = "上传商家",response = CBusiness.class)
    @RequestMapping(value = "/addBusiness",method = RequestMethod.POST)
    public synchronized JsonResult addBusiness(@RequestHeader("token") String token,
            CBusiness business) throws Exception {
//        try{
            TokenModel model = getTokenModel();
            if(StringUtil.isEmpty(business.getBusinessPhone())) return new JsonResult(2, "请填写商户手机号码");
            if(StringUtil.isEmpty(business.getLatitude()) ||StringUtil.isEmpty(business.getLongitude())) return new JsonResult(2, "经纬度不能为空");
            if(StringUtil.isEmpty(business.getAreaId())|| (business.getAreaId().substring(4,6).equals("00"))){
                return new JsonResult(2, "区域不能为空或者区域不正确请重新选择");
            }
//            if(((business.getQuotaType()==2)&&(!business.getOperateType().equals("6")))){
//                return new JsonResult(2, "对不起，中额暂时仅支持汽车机车销售行业！");
//            }

            if(business.getQuotaType()==3){
                return new JsonResult(2, "对不起，暂未开通大额商家！");
            }

            if(business.getQuotaType()==2){
                business.setMaxAmount("500000");        //中额设置最大限额为50万
            }else{
                business.setMaxAmount("20000");        //默认2万
            }

            business.setPhone(business.getBusinessPhone());
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("businessPhone", business.getLegalPersonNum());
//			business.setMaxAmount(business.getMaxAmount()!=null?business.getMaxAmount():new BigDecimal(20000));		//设置默认封顶金额为20000

            if(Integer.valueOf(business.getRangli())>0){
                business.setRangli(Arith.div(Double.valueOf(business.getRangli()),100,2)+"");
            }

            int count = 0;
            Map<String,Object> addParams = new HashMap<String,Object>(0);
            List<Map<String,Object>> info = cBusinessService.selectUserTypeByPhone(params);			//查询用户id
            business.setUploadUser(model.getId()+"");

            if(info==null || info.size()<=0){
                return new JsonResult<Object>(2, "该法人手机号未注册，请先注册");
            }else{
//                if(info.get(0).get("userType").toString().equals("2")){
//                    //  代理商不可上传店铺，用户可在审核时变更为商家
//                    return new JsonResult<Object>(2, "该商家手机号已经是代理商");
//                }
                boolean isExistBusiness = false;

                for(int i=0;i<info.size();i++){
                    Map<String,Object> obj = info.get(i);
                    if(obj.get("roleId").toString().equals("2")){
                        isExistBusiness = true;
                        business.setUserId(Integer.valueOf(obj.get("id")+""));
                    }
                    addParams.put("uid",obj.get("uid"));
                }
                if(!isExistBusiness){
                    int addCount = cBusinessService.addUserRoleAttribute(addParams);
                    if(addCount>0){
                        business.setUserId(Integer.valueOf(addParams.get("userId").toString()));
                    }else{
                        return new JsonResult<Object>(2, "添加商户角色失败,请稍后重试");
                    }
                }
            }

            if(business.getId() ==0) {
//                int isExistBName = cBusinessService.queryExistBusiness(business);
//                if(isExistBName>0){
//                    return new JsonResult(2,"该店铺名称已被上传");
//                }
                // 新增
                business.setUploadDate(new Date());
                count = cBusinessService.addBusiness(business);

                if (count > 0) {
                    return new JsonResult(0, "添加成功");
                }else{
                    return new JsonResult(2, "上传失败");
                }
            }else{
                // 修改
                business.setState("0");
                count = cBusinessService.updateBusiness(business);
                if (count > 0) {
                    return new JsonResult<Object>(0, "修改商家信息成功", business);
                } else {
                    return new JsonResult<Object>(2, "修改商家信息失败");
                }
            }
//        }catch(CustomerException ex){
//            ex.printStackTrace();
//            return new JsonResult<Object>(2,"上传商户失败，请稍后重试");
//        }catch(Exception ex){
//            ex.printStackTrace();
//            return new JsonResult<Object>(2,"上传商户失败，请稍后重试");
//        }
    }

    @CheckPermission
    @ApiOperation(value = "上传商家", notes = "上传商家简化版",response = CBusiness.class)
    @RequestMapping(value = "/addBusinessSimple",method = RequestMethod.POST)
    public synchronized JsonResult addBusinessSimple(@RequestHeader("token") String token,
                                  CBusiness business) throws Exception {
        try{
            TokenModel model = getTokenModel();
            if(StringUtil.isEmpty(business.getBusinessPhone())) return new JsonResult(2, "请填写商家登陆手机号码");
            if(StringUtil.isEmpty(business.getLatitude()) ||StringUtil.isEmpty(business.getLongitude())) return new JsonResult(2, "经纬度不能为空");
            if(StringUtil.isEmpty(business.getAreaId())|| (business.getAreaId().substring(4,6).equals("00"))){
                return new JsonResult(2, "区域不能为空或者区域不正确请重新选择");
            }
//            if(((business.getQuotaType()==2)&&(!business.getOperateType().equals("6")))){
//                return new JsonResult(2, "对不起，中额暂时仅支持汽车机车销售行业！");
//            }

            if(business.getQuotaType()==3){
                return new JsonResult(2, "对不起，暂未开通大额商家！");
            }

            if(business.getQuotaType()==2){
                business.setMaxAmount("500000");        //中额设置最大限额为50万
            }else{
                business.setMaxAmount("20000");        //默认2万
            }

            business.setPhone(business.getBusinessPhone());
            if(StringUtil.isEmpty(business.getBusinessWord())){
                business.setBusinessWord(business.getHoleIdCardPhoto());                //临时处理businessWord为空的
            }
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("businessPhone", business.getLegalPersonNum());


            if(Integer.valueOf(business.getRangli())>0){
                business.setRangli(Arith.div(Double.valueOf(business.getRangli()),100,2)+"");
            }

            int count = 0;
            Map<String,Object> addParams = new HashMap<String,Object>(0);
            List<Map<String,Object>> info = cBusinessService.selectUserTypeByPhone(params);			//查询用户id
            business.setUploadUser(model.getId()+"");

            if(info==null || info.size()<=0){
                return new JsonResult<Object>(2, "该法人手机号未注册，请先注册");
            }else{
                boolean isExistBusiness = false;

                for(int i=0;i<info.size();i++){
                    Map<String,Object> obj = info.get(i);
                    if(obj.get("roleId").toString().equals("2")){
                        isExistBusiness = true;
                        business.setUserId(Integer.valueOf(obj.get("id")+""));
                    }
                    addParams.put("uid",obj.get("uid"));
                }
                if(!isExistBusiness){
                    int addCount = cBusinessService.addUserRoleAttribute(addParams);
                    if(addCount>0){
                        business.setUserId(Integer.valueOf(addParams.get("userId").toString()));
                    }else{
                        return new JsonResult<Object>(2, "添加商户角色失败,请稍后重试");
                    }
                }
            }

            if(business.getId() ==0) {
//                int isExistBName = cBusinessService.queryExistBusiness(business);
//                if(isExistBName>0){
//                    return new JsonResult(2,"该店铺名称已被上传");
//                }
                // 新增
                business.setUploadDate(new Date());
                count = cBusinessService.addBusinessSimple(business);

                if (count > 0) {
                    return new JsonResult(0, "添加成功");
                }else{
                    return new JsonResult(2, "上传失败");
                }
            }else{
                // 修改
                business.setState("0");
                count = cBusinessService.updateBusinessSimple(business);
                if (count > 0) {
                    return new JsonResult<Object>(0, "修改商家信息成功", business);
                } else {
                    return new JsonResult<Object>(2, "修改商家信息失败");
                }
            }
        }catch(CustomerException ex){
            ex.printStackTrace();
            return new JsonResult<Object>(2,ex.getMessage());
        }catch(Exception ex){
            ex.printStackTrace();
            return new JsonResult<Object>(2,"上传商户失败，请稍后重试");
        }
    }

    @CheckPermission
    @ApiOperation(value = "获取商家集合", notes="可根据状态筛选结果,商家状态(-1:全部,0:待审核,1:已审核,2:审核失败)",response=BusinessListDto.class)
    @RequestMapping(value = "/getBusinessList", method = RequestMethod.GET)
    public JsonResult getBusinessList(@RequestHeader("token") String token,
                                      @ApiParam(required=true,value="商家状态")@RequestParam String state,
                                      @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                                      @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize){
        JsonResult<Object> result = null;
        try{
            ParaMap paraMap = super.getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("uploadUser", model.getId());
            PageInfo page = new PageInfo();
            page = cBusinessService.getBusinessList(paraMap);
            if(page.getList()!=null && page.getList().size()>0){
                result = new JsonResult(0,"查询商家列表成功",page);
                String jsonStr = JSONStringUtil.toJSONString(result);
                return  JSONObject.parseObject(jsonStr,JsonResult.class);
            }else{
                result = new JsonResult(4,"暂无数据",page);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            result = new JsonResult(2,"查询数据出错。");
        }
        return result;
    }

    @CheckPermission
    @ApiOperation(value = "获取商家经营类型", notes = "获取商家经营类型",response=TradeDto.class)
    @RequestMapping(value = "/getBusinessType",method = RequestMethod.GET)
    public JsonResult<Object> getBusinessType(@RequestHeader("token") String token){
        try{
            List<Map<String, Object>> list = cBusinessService.getBusinessType();

            return new JsonResult<Object>(0,"查询商家经营类型成功",list);
        }catch(Exception ex){
            return new JsonResult<Object>(2,"查询数据出错。");
        }
    }

    @CheckPermission
    @ApiOperation(value = "商家审核", notes = "根据id更新商家状态,可做通过、拒绝操作")
    @RequestMapping(value = "/auditBusiness", method = RequestMethod.POST)
    public JsonResult auditBusiness(@RequestHeader("token") String token,
                                    @ApiParam(value = "id") @RequestParam Integer id,
                                    @ApiParam(value = "状态,0:待审核   1:审核通过  2:拒绝") @RequestParam int state){
        try{
            ParaMap paraMap = super.getParaMap();
            int count = cBusinessService.updateBusinessState(paraMap);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("state", state);
            if(count>0){
                return new JsonResult(0,"商家状态修改成功");
            }else{
                return new JsonResult<Object>(2,"商家状态修改失败");
            }
        }catch(Exception ex){
            return new JsonResult(2,"查询数据出错。");
        }
    }

    @ApiOperation(value = "审核详情", notes = "查看商家详细信息",response = BusinessDetailDto.class)
    @RequestMapping(value = "/queryDetail", method= RequestMethod.GET)
    public JsonResult queryDetail(@RequestHeader("token") String token,
                                            @ApiParam(value = "id") @RequestParam String id){
        CBusiness business = null;
        try{
            Map<String, String> params = new HashMap<String, String>(1);
            params.put("id", id);
            business = cBusinessService.queryDetail(params);
            if(business!=null){
                Double rangli = Double.valueOf(business.getRangli())*100;
                business.setRangli(rangli.intValue()+"");
                HashMap<String, String> qparam = new HashMap<String, String>();
                if(!StringUtils.isEmpty(business.getProvinceCode())){
                    qparam.put("provinceCode", business.getProvinceCode());
                }
                if(!StringUtils.isEmpty(business.getCityCode())){
                    qparam.put("cityCode", business.getCityCode());
                }
                if(!StringUtils.isEmpty(business.getAreaId())){
                    qparam.put("areaId", business.getAreaId());
                }

                if(qparam!=null && qparam.size()>0){
                    List<String> areaList = cBusinessService.queryAreaName(qparam);
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
                return new JsonResult(2,"商家不存在");
            }
            switch (business.getQuotaType()){
                case 0:
                    business.setQuotaDesc(QuataEnum.DEFAULT_QUOTA.getQuataDesc());
                    break;
                case 1:
                    business.setQuotaDesc(QuataEnum.MIN.getQuataDesc());
                    break;
                case 2:
                    business.setQuotaDesc(QuataEnum.MID.getQuataDesc());
                    break;
                case 3:
                    business.setQuotaDesc(QuataEnum.BIG.getQuataDesc());
                    break;
            }

            JsonResult result = new JsonResult(0,"查询成功",business);
            String jsonStr = JSONStringUtil.toJSONString(result);
            return  JSONObject.parseObject(jsonStr,JsonResult.class);
        }catch(Exception ex){
            return new JsonResult(2,"查询数据出错。");
        }
    }

    @ApiOperation(value = "商户管理",notes = "商户管理",response = BusinessStatisticsDto.class)
    @RequestMapping(value = "/queryBusinessStatistics",method = RequestMethod.GET)
    public JsonResult queryBusinessStatistics(@RequestHeader("token") String token){
        ParaMap paraMap = super.getParaMap();
        try {
            TokenModel model = getTokenModel();
            paraMap.put("userId", model.getId());
            paraMap.put("uid", model.getUserId());
            List<Map<String,Object>> result = cBusinessService.queryBusinessStatistics(paraMap);
            if(result!=null && result.size()>0){
                return new JsonResult(0,"查询成功",result);
            }else {
                return new JsonResult(4,"暂无数据",result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败，请稍后重试");
        }
    }

    @ApiOperation(value = "店铺收益详情",notes = "店铺收益详情")
    @RequestMapping(value = "/queryStoreRevenue",method = RequestMethod.GET)
    public JsonResult queryStoreRevenue(@RequestHeader("token") String token,
                                        @ApiParam(value = "商户id") @RequestParam Integer businessId,
                                        @ApiParam(value = "页码",defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                        @ApiParam(value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize){
        ParaMap paraMap = super.getParaMap();
        try {
            TokenModel model = getTokenModel();
            paraMap.put("uid", model.getUserId());
            PageInfo page = cBusinessService.queryStoreRevenue(paraMap);
            if(page.getList()!=null && page.getList().size()>0){
                return new JsonResult(0,"查询成功",page);
            }else {
                return new JsonResult(4,"暂无数据",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败，请稍后重试");
        }
    }

}
