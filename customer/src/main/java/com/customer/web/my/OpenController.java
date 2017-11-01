package com.customer.web.my;

import com.customer.dao.OpenCountDto;
import com.customer.dto.OpenLogDto;
import com.customer.dto.my.OpenPushAgentDto;
import com.customer.dto.my.PushAgentListDto;
import com.customer.dto.my.RecommendLogDto;
import com.customer.entity.ParaMap;
import com.customer.exception.CustomerException;
import com.customer.service.my.OpenService;
import com.customer.util.JsonResult;
import com.customer.util.Page;
import com.customer.util.TokenModel;
import com.customer.web.BaseController;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@Api(value = "开通",description = "开通")
@RestController
@RequestMapping(value = "/v1/cApi/open")
public class OpenController extends BaseController{

    @Autowired
    private OpenService openService;

    @ApiOperation(value = "跳转到开通页面",notes = "跳转到开通页面<br/>" +
            "status,  <br/>/**" +
            "     * 请求返回状态码<br/>" +
            "     * -1 ————>  token 过期<br/>" +
            "     *0 ————>  一切正常<br/>" +
            "     * 2 ————> 通用异常，不需要做处理 <br/>" +
            "     * 4 ————>  请求成功，但数据为空" +
            "     */ ",response = OpenCountDto.class)
    @RequestMapping(value = "/toOpen",method = RequestMethod.GET)
    public JsonResult toOpen(@RequestHeader(value = "token") String token){
        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = getParaMap();
            paraMap.put("userId",model.getId());
            Map<String,Object> result = openService.queryOpenLimit(paraMap);
            if(result!=null && result.size()>0){
                int count = Integer.valueOf(result.get("remainingQuota").toString());
                int total = Integer.valueOf(result.get("openLimit").toString());
                int remaining = (total-count);                      //剩余可用的
                result.put("remainingQuota",remaining);
                if(remaining>0){
                    result.put("isOpenable",true);
                }else{
                    result.put("isOpenable",false);
                }

                return new JsonResult(0,"查询成功", result);
            }else{
                return new JsonResult(4,"暂无数据");
            }
        }catch (CustomerException ce) {
            return new JsonResult(2, ce.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败");
        }
    }

    @ApiOperation(value = "开通",notes = "开通服务商/代理商/推广员<br/>" +
            "status,  <br/>/**" +
            "     * 请求返回状态码<br/>" +
            "     * -1 ————>  token 过期<br/>" +
            "     *0 ————>  一切正常<br/>" +
            "     * 2 ————> 通用异常，不需要做处理 <br/>" +
            "     * 4 ————>  请求成功，但数据为空" +
            "     */ ")
    @RequestMapping(value = "/open",method = RequestMethod.POST)
    public JsonResult open(@RequestHeader(value = "token") String token,
                           @ApiParam(value = "推荐人手机号码") @RequestParam String recommendPhone,
                           @ApiParam(value = "被推荐人手机号码") @RequestParam String recommendedPhone,
                           @ApiParam(value = "角色  1:高级推广员  2:推广员") @RequestParam Integer roleId){
        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = getParaMap();
            paraMap.put("userId",model.getId());
            if(recommendPhone.trim().equals(recommendedPhone.trim())){
                return new JsonResult(2,"推荐人手机号码、被推荐人手机号码不能相同");
            }
            String roleName = null;
            if(roleId==1){
                roleName = "高级推广员";
            }else{
                roleName = "推广员";
            }
            paraMap.put("roleName",roleName);
            int count1 = openService.queryRecommendMan(paraMap);
            if(count1==0){
                return new JsonResult(2,"推荐人不存在");
            }
            int count2 = openService.queryRecommendedMan(paraMap);
            if(count2==0){
                return new JsonResult(2,"被推荐人不存在");
            }
            return openService.addOpen(paraMap);
        }catch (CustomerException ce) {
            return new JsonResult(2, ce.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"开通失败，请稍后重试");
        }

    }

    @ApiOperation(value = "开通记录",notes = "开通记录<br/>" +
            "status,  <br/>/**" +
            "     * 请求返回状态码<br/>" +
            "     * -1 ————>  token 过期<br/>" +
            "     *0 ————>  一切正常<br/>" +
            "     * 2 ————> 通用异常，不需要做处理 <br/>" +
            "     * 4 ————>  请求成功，但数据为空" +
            "     */ ",response = OpenLogDto.class)
    @RequestMapping(value = "openLog",method = RequestMethod.GET)
    public JsonResult openLog(@RequestHeader(value = "token") String token,
                              @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                              @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize){

        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getId());
            PageInfo page = openService.openLog(paraMap,currentPage,pageSize);
            if(page.getList()!=null && page.getList().size()>0) {
                return new JsonResult(0, "查询开通记录成功", page);
            }else {
                return new JsonResult(4,"暂无数据",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询开通记录失败，请稍后重试");
        }
    }

    @ApiOperation(value = "跳转到开通配送银牌",notes = "跳转到开通配送银牌，查询服务商代理的区域<br/>" +
            "status,  <br/>/**" +
            "     * 请求返回状态码<br/>" +
            "     * -1 ————>  token 过期<br/>" +
            "     *0 ————>  一切正常<br/>" +
            "     * 2 ————> 通用异常，不需要做处理 <br/>" +
            "     * 4 ————>  请求成功，但数据为空" +
            "     */ ",response = OpenPushAgentDto.class)
    @RequestMapping(value = "/toOpenPushAgent",method = RequestMethod.GET)
    public JsonResult toOpenPushAgent(@RequestHeader(value = "token") String token){
        ParaMap paraMap = getParaMap();
        TokenModel model = null;
        try {
            model = getTokenModel();
            paraMap.put("fromId",model.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "token过期，请重新登录");
        }

        List<Map<String,Object>> allAreas = openService.queryAllAreas(paraMap);
        if(allAreas!=null && allAreas.size()>0){
            return new JsonResult(0,"查询服务商代理区域成功",allAreas);
        }else{
            return new JsonResult(4,"暂无数据",allAreas);
        }
    }

    @ApiOperation(value = "查询待开通号码是否可用",notes = "查询待开通号码是否可用<br/>" +
            "status,  <br/>/**" +
            "                 * 请求返回状态码<br/>" +
            "                 * -1 ————>  token 过期<br/>" +
            "                 *0 ————>  一切正常<br/>" +
            "                 * 2 ————> 通用异常，不需要做处理 <br/>" +
            "                 * 3 ————> 需要做处理，取返回结果中的message，弹出确认选择框，让用户选择确认或者取消， " +
            " 用户点击确认时，立即开通按钮可用，用户点击取消时，立即开通按钮不可用<br/>" +
            "                 * 4 ————>  请求成功，但数据为空" +
            "                 */ ")
    @RequestMapping(value = "/queryBeOpenedUser",method = RequestMethod.GET)
    public JsonResult queryBeOpenedUser(@RequestHeader(value = "token") String token,
                                        @ApiParam(value = "待开通") @RequestParam String beOpenedUserPhone){
        ParaMap paraMap = getParaMap();
        return  openService.queryBeOpenedUser(paraMap);
    }

    @ApiOperation(value = "开通配送银牌",notes = "开通配送银牌，使开通关系生效<br/>" +
            "status,  <br/>/**" +
            "     * 请求返回状态码<br/>" +
            "     * -1 ————>  token 过期<br/>" +
            "     *0 ————>  一切正常<br/>" +
            "     * 2 ————> 通用异常，不需要做处理 <br/>" +
            "     * 4 ————>  请求成功，但数据为空" +
            "     */ ")
    @RequestMapping(value = "/openPushAgent",method = RequestMethod.POST)
    public JsonResult openPushAgent(@RequestHeader(value = "token") String token,
                                    @ApiParam(value = "配送到") @RequestParam String agencyUserPhone,
                                    @ApiParam(value = "待开通") @RequestParam String beOpenedUserPhone,
                                    @ApiParam(value = "上传人") @RequestParam String uploadUserPhone,
                                    @ApiParam(value = "区域id") @RequestParam Integer areaId,
                                    @ApiParam(value = "行业id") @RequestParam Integer tradeId){
        ParaMap paraMap = getParaMap();
        TokenModel model = null;
        try {
            model = getTokenModel();
            paraMap.put("cuserId",model.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "token过期，请重新登录");
        }
        if(agencyUserPhone.equals(beOpenedUserPhone)){
            return new JsonResult(2,"配送到与待开通号码不能一样");
        }
        if(uploadUserPhone.equals(beOpenedUserPhone)){
            return new JsonResult(2,"上传人与待开通号码不能一样");
        }
        return openService.savePushAgent(paraMap);
    }

    @ApiOperation(value = "配送记录",notes = "配送银牌开通记录<br/>" +
            "status,  <br/>/**" +
            "     * 请求返回状态码<br/>" +
            "     * -1 ————>  token 过期<br/>" +
            "     *0 ————>  一切正常<br/>" +
            "     * 2 ————> 通用异常，不需要做处理 <br/>" +
            "     * 4 ————>  请求成功，但数据为空" +
            "     */ ",response = PushAgentListDto.class)
    @RequestMapping(value = "/pushAgentList",method = RequestMethod.GET)
    public JsonResult pushAgentList(@RequestHeader(value = "token") String token,
                                    @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                                    @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize){
        ParaMap paraMap = getParaMap();
        TokenModel model = null;
        try {
            model = getTokenModel();
            paraMap.put("userId",model.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "token过期，请重新登录");
        }
        Page page  = openService.queryPushAgentList(paraMap,currentPage,pageSize);
        if(page!=null && page.getList()!=null && page.getList().size()>0){
            return new JsonResult(0,"查询配送记录成功",page);
        }else{
            return new JsonResult(4,"暂无数据",page);
        }
    }

    @ApiOperation(value = "开通名单",notes = "开通名单<br/>" +
            "status,  <br/>/**" +
            "     * 请求返回状态码<br/>" +
            "     * -1 ————>  token 过期<br/>" +
            "     *0 ————>  一切正常<br/>" +
            "     * 2 ————> 通用异常，不需要做处理 <br/>" +
            "     * 4 ————>  请求成功，但数据为空" +
            "     */ ",response = RecommendLogDto.class)
    @RequestMapping(value = "/recommendLog",method = RequestMethod.GET)
    public JsonResult recommendLog(@RequestHeader(value = "token") String token,
                                            @ApiParam(value = "被推荐人手机号码") @RequestParam String recommendedPhone){
        ParaMap paraMap = getParaMap();
        paraMap.put("state","0");
        RecommendLogDto log = openService.queryRecommendLog(paraMap);
        if(log != null && log.getState()==0){
            return new JsonResult(0,"查询成功",log);
        }else if(log != null && log.getState()==1){
            return new JsonResult(2,"该号码已审核通过");
        }else if(log != null && log.getState()==2){
            return new JsonResult(2,"该号码审核失败");
        }else{
            return new JsonResult(4,"暂无记录",log);
        }
    }

    @ApiOperation(value = "关系生效",notes = "关系生效<br/>" +
            "status,  <br/>/**" +
            "     * 请求返回状态码<br/>" +
            "     * -1 ————>  token 过期<br/>" +
            "     *0 ————>  一切正常<br/>" +
            "     * 2 ————> 通用异常，不需要做处理 <br/>" +
            "     * 4 ————>  请求成功，但数据为空" +
            "     */  ")
    @RequestMapping(value = "/effectiveRelationship",method = RequestMethod.POST)
    public JsonResult effectiveRelationship(@RequestHeader(value = "token") String token,
                                            @ApiParam(value = "被推荐人号码,格式如13212569875,18369856987,15523658968 最多10个号码") @RequestParam String phones){
        TokenModel model = null;
        int operatorId=0;
        try {
            model = getTokenModel();
            operatorId = model.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "token过期，请重新登录");
        }
        return openService.updateRelationship(phones,operatorId);
    }

    @ApiOperation(value = "开通记录",notes = "开通记录<br/>" +
            "status,  <br/>/**" +
            "     * 请求返回状态码<br/>" +
            "     * -1 ————>  token 过期<br/>" +
            "     *0 ————>  一切正常<br/>" +
            "     * 2 ————> 通用异常，不需要做处理 <br/>" +
            "     * 4 ————>  请求成功，但数据为空" +
            "     */ ",response = OpenLogDto.class)
    @RequestMapping(value = "/operatorLog",method = RequestMethod.GET)
    public JsonResult operatorLog(@RequestHeader(value = "token") String token,
                                  @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                                  @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize){
        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getId());
            Page page = openService.queryOperatorLog(paraMap,currentPage,pageSize);
            if(page.getList()!=null && page.getList().size()>0) {
                return new JsonResult(0, "查询开通记录成功", page);
            }else {
                return new JsonResult(4,"暂无数据",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询开通记录失败，请稍后重试");
        }
    }

}
