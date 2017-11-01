package com.customer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.customer.dao.CBusinessDao;
import com.customer.dao.CCollectDao;
import com.customer.dao.ShoppingCartDao;
import com.customer.dto.NearbyBusinessDto;
import com.customer.entity.CBusiness;
import com.customer.entity.CCollect;
import com.customer.entity.ShoppingCart;
import com.customer.exception.CustomerException;
import com.customer.service.CBusinessService;
import com.customer.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.*;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
@Service("cBusinessService")

public class CBusinessServiceImpl implements CBusinessService {
    private Logger logger = LoggerFactory.getLogger(CBusinessServiceImpl.class);
    @Autowired
    private CBusinessDao cBusinessDao;

    // 分页查询附近的商家
    @Override
    public PageInfo queryNearbyBusiness(PageInfo page, Map<String,Object> params, Integer pageIndex, Integer pageSize){
        Map<String, Double> positions = PositionUtil.getRectangle4Point(Double.valueOf(params.get("latitude").toString()),
                Double.valueOf(params.get("longitude").toString()),Double.valueOf(params.get("distance").toString()));

        PageInfo pageInfo = new PageInfo();
        int total = 0;
        String orderByStr = "";
        params.put("latitude1",positions.get("latitude1")+"");
        params.put("latitude2",positions.get("latitude2")+"");
        params.put("longitude1",positions.get("longitude1")+"");
        params.put("longitude2",positions.get("longitude2")+"");

//        PageHelper.startPage(pageIndex, pageSize);
        PageHelper.startPage(pageIndex, pageSize,false);
        if(params.get("isSortAscending")==null || "".equals(params.get("isSortAscending").toString())){
//            total = cBusinessDao.countTotal(params);
            orderByStr = "distance is null, distance";
        }else{
            if(params.get("isSortAscending").toString().equals("0")){
//                total = cBusinessDao.countTotal(params);
                orderByStr = "upload_date desc";
            }else{
//                total = cBusinessDao.countTotal(params);
                orderByStr = "upload_date asc";
            }
        }
        if((params.get("areaId")!=null && !"".equals(params.get("areaId").toString()))||
                (params.get("name")!=null && !"".equals(params.get("name").toString()))||
                (params.get("tradeId")!=null && !"".equals(params.get("tradeId").toString()))){
            params.remove("latitude1");
            params.remove("latitude2");
            params.remove("longitude1");
            params.remove("longitude2");
//            total = cBusinessDao.countTotal(params);
            orderByStr = "distance is null, distance";
        }

        PageHelper.orderBy(orderByStr);

        List<NearbyBusinessDto> list = cBusinessDao.queryNearbyBusiness(params);
        total = cBusinessDao.countTotal(params);

        int totalPage = total/pageSize;         //总页数

        pageInfo.setList(list);
        pageInfo.setTotal(total);
        if(totalPage>pageIndex){
            pageInfo.setHasNextPage(true);
        }else{
            pageInfo.setHasNextPage(false);
        }
        if(pageIndex!=1) {
            pageInfo.setIsFirstPage(false);
        }else{
            pageInfo.setIsFirstPage(true);
        }
        if(pageIndex-totalPage==0){
            pageInfo.setIsLastPage(true);
        }else{
            pageInfo.setIsLastPage(false);
        }

        pageInfo.setPageNum(pageIndex);
        pageInfo.setPageSize(pageSize);
        return pageInfo;
    }

    // 分页查询附近的商家
    @Override
    public Page queryNearbyBusiness(Page page, Map<String,Object> params, Integer pageIndex, Integer pageSize){
        Map<String, Double> positions = PositionUtil.getRectangle4Point(Double.valueOf(params.get("latitude").toString()),
                Double.valueOf(params.get("longitude").toString()),Double.valueOf(params.get("distance").toString()));


        String orderByStr = "";
        params.put("latitude1",positions.get("latitude1")+"");
        params.put("latitude2",positions.get("latitude2")+"");
        params.put("longitude1",positions.get("longitude1")+"");
        params.put("longitude2",positions.get("longitude2")+"");

        if(params.get("isSortAscending")==null || "".equals(params.get("isSortAscending").toString())){
            orderByStr = "distance is null, distance";
        }else{
            if(params.get("isSortAscending").toString().equals("0")){
                orderByStr = "upload_date desc";
            }else{
                orderByStr = "upload_date asc";
            }
        }
        if((params.get("areaId")!=null && !"".equals(params.get("areaId").toString()))||
                (params.get("name")!=null && !"".equals(params.get("name").toString()))||
                (params.get("tradeId")!=null && !"".equals(params.get("tradeId").toString()))){
            params.remove("latitude1");
            params.remove("latitude2");
            params.remove("longitude1");
            params.remove("longitude2");
            orderByStr = "distance is null, distance";
        }

        params.put("orderByStr",orderByStr);
        int totalRow = cBusinessDao.countTotal(params);
        page.setTotal(totalRow);
        params.put("offset", page.getOffset());
        double latitude = Double.valueOf(params.get("latitude").toString());
        double longitude = Double.valueOf(params.get("longitude").toString());
        List<NearbyBusinessDto> lists = cBusinessDao.queryNearbyBusiness(params);
        for(NearbyBusinessDto dto : lists){
            if(dto.getLatitude1()!=null&&dto.getLongitude1()!=null&&!"".equals(dto.getLatitude1())&&!"".equals(dto.getLongitude1())
                    &&!dto.getLatitude1().equalsIgnoreCase("null")&&!dto.getLongitude1().equalsIgnoreCase("null")
                    &&!dto.getLatitude1().contains("E")&&!dto.getLongitude1().contains("E")) {
                double latitude1 = Double.valueOf(dto.getLatitude1());
                double longitude1 = Double.valueOf(dto.getLongitude1());
                dto.setDistance((int)PositionUtil.getDistance(latitude,longitude,latitude1,longitude1));
            }else{
                dto.setDistance(2147483647);
            }
        }
        Collections.sort(lists,new NearbyBusinessComparator());
        List<NearbyBusinessDto> list = null;
        if((page.getOffset()+20)<=lists.size()){
            list = lists.subList(page.getOffset(),(page.getOffset()+20));
        }else{
            list = lists.subList(page.getOffset(),lists.size());
        }

        page.setList(list);
        page.init();
        page.setPageNum(pageIndex);
        return page;
    }

    // 查询店铺详细信息
    @Override
    public Map<String,Object> queryBusinessDetail(Map<String,Object> params){
        return cBusinessDao.queryBusinessDetail(params);
    }

    // 根据手机号码查询用户id，用户类型
    public List<Map<String,Object>> selectUserTypeByPhone(Map<String,Object> params){
        return cBusinessDao.selectUserTypeByPhone(params);
    }

    // 保存商家
    @Override
    @Transactional()
    public int addBusiness(CBusiness business){
        int count = cBusinessDao.addBusiness(business);
        if(count==0){
            count=0;
            throw  new CustomerException(2,"添加商家失败,请稍后重试");
        }
        Map<String,String> paraMap = new HashMap<String,String>(0);
        paraMap.put("name",business.getCardHolder());
        paraMap.put("bankCardNo",business.getBankCardNo());
//        paraMap.put("identifyCard",business.getIdentifyCard());
        JSONObject verifyObj = null;
        try {
            verifyObj = AliyunBankUtils.realNameAuthentication(paraMap);
        } catch (Exception e) {
            count=0;
            logger.error(e.getMessage());
            throw  new CustomerException(2,"添加银行卡失败");
        }
        if(verifyObj!=null&&verifyObj.getJSONObject("resp")!=null&&
                verifyObj.getJSONObject("resp").getIntValue("code")==0){
//            count = cBusinessDao.addClearanceAccount(business);
//            if(count>0){
//                count +=1;
                count = cBusinessDao.addBusinessSupportInfo(business);
                if(count>0){
                    count +=1;
                }else {
                    count=0;
                    throw  new CustomerException(2,"上传店铺辅助信息失败");
                }
//            }else{
//                count=0;
//                throw  new CustomerException(2,"添加银行卡失败");
//            }
        }else{
            count=0;
            throw  new CustomerException(2,verifyObj.getJSONObject("resp").getString("desc"));
        }
        return count;
    }

    // 保存商家
    @Override
    @Transactional()
    public int addBusinessSimple(CBusiness business){
        return cBusinessDao.addBusiness(business);
    }

    // 更新商家信息
    @Override
    @Transactional
    public int updateBusiness(CBusiness business){
        int count = 0;
        count = cBusinessDao.updateBusiness(business);
        if(count==0){
            throw  new CustomerException(2,"修改商家失败,请稍后重试");
        }
        Map<String,String> paraMap = new HashMap<String,String>(0);
        paraMap.put("name",business.getCardHolder());
        paraMap.put("bankCardNo",business.getBankCardNo());
        JSONObject verifyObj = null;
        try {
            verifyObj = AliyunBankUtils.realNameAuthentication(paraMap);
        } catch (Exception e) {
            count=0;
            logger.error(e.getMessage());
            throw  new CustomerException(2,"验证银行卡信息失败");
        }
        if(verifyObj!=null&&verifyObj.getJSONObject("resp")!=null&&
                verifyObj.getJSONObject("resp").getIntValue("code")==0){
            count = cBusinessDao.updateBusinessSupportInfo(business);
            if(count>0){
                count +=1;
            }else{
                count=0;
                throw  new CustomerException(2,"修改结算银行卡失败");
            }
        }else{
            count=0;
            throw  new CustomerException(2,verifyObj.getJSONObject("resp").getString("desc"));
        }
        return count;
    }

    // 更新商家信息
    @Override
    @Transactional
    public int updateBusinessSimple(CBusiness business){
        return cBusinessDao.updateBusiness(business);
    }

    // 获取商家列表
    @Override
    public PageInfo getBusinessList(Map<String, Object> params){
        PageHelper.startPage(Integer.valueOf(params.get("currentPage").toString()),Integer.valueOf(params.get("pageSize").toString()));
        PageHelper.orderBy("upload_date desc");
        return new PageInfo(cBusinessDao.getBusinessList(params));
    }

    // 获取商家经营类型
    @Override
    public List<Map<String, Object>> getBusinessType(){
        return cBusinessDao.getBusinessType();
    }

    // 根据id查询商家相信信息
    @Override
    public CBusiness queryDetail(Map<String, String> params){
        return cBusinessDao.queryDetail(params);
    }

    // 查询省市区对应中文名称
    @Override
    public List<String> queryAreaName(Map<String, String> params){
        return cBusinessDao.queryAreaName(params);
    }

    // 更新店铺状态
    @Override
    public int updateBusinessState(Map<String, String> params){
        return cBusinessDao.updateBusinessState(params);
    }

    // 添加结算账户银行信息
    @Transactional(rollbackFor = Throwable.class)
    public int addClearanceAccount(CBusiness business){
        return cBusinessDao.addClearanceAccount(business);
    }

    // 商户管理
    @Override
    public List<Map<String,Object>> queryBusinessStatistics(Map<String, String> params){
//        PageHelper.startPage(Integer.valueOf(params.get("currentPage").toString()),Integer.valueOf(params.get("pageSize").toString()));
//        return new PageInfo(cBusinessDao.queryBusinessStatistics(params));
        return cBusinessDao.queryBusinessStatistics(params);
    }

    // 添加商户角色
    @Override
    public int addUserRoleAttribute(Map<String, Object> params){
        return cBusinessDao.addUserRoleAttribute(params);
    }

    // 店铺收益详情
    @Override
    public PageInfo queryStoreRevenue(Map<String, Object> params){
        PageHelper.startPage(Integer.valueOf(params.get("currentPage").toString()),Integer.valueOf(params.get("pageSize").toString()));
        PageHelper.orderBy("bdsj desc");
        return new PageInfo(cBusinessDao.queryStoreRevenue(params));
    }

    // 查询店铺名是否被注册
    @Override
    public int queryExistBusiness(CBusiness business){
        return cBusinessDao.queryExistBusiness(business);
    }

}
