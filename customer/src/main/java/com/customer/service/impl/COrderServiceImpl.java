package com.customer.service.impl;

import com.customer.dao.COrderMapper;
import com.customer.dto.COrderListDto;
import com.customer.dto.CheckBusinessDto;
import com.customer.dto.OrderTotalDto;
import com.customer.dto.ScanBusinessDto;
import com.customer.entity.CBusiness;
import com.customer.entity.COrder;
import com.customer.entity.ParaMap;
import com.customer.enums.QuataEnum;
import com.customer.exception.CustomerException;
import com.customer.service.COrderService;
import com.customer.util.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/3/31.
 */
@Service
public class COrderServiceImpl implements COrderService {

    @Autowired
    private COrderMapper cOrderMapper;

    /*@Override
    public int createOrder(ParaMap paraMap) throws Exception {
        CBusiness cBusiness = cOrderMapper.checkStoreById(paraMap);
        if(null == cBusiness){
            throw new CustomerException(2, "商户ID不正确,请重新输入!");
        }
        //商家当天订单额度
        double orderSum = Double.parseDouble(cOrderMapper.getOrderSum(paraMap));
        double maxAmount = Double.parseDouble(cBusiness.getMaxAmount());
        if((orderSum + Double.parseDouble(paraMap.get("money")+"")) > maxAmount){
            throw new CustomerException(2, "下单失败!商户今日的剩余消费额度:"+((maxAmount-orderSum)>0?(maxAmount-orderSum):0.0)+"元");
        }

        paraMap.put("orderId", "SS"+(System.currentTimeMillis()+"").substring(3)+((int)((Math.random()*9+1)*100)));
        paraMap.put("sellerId", cBusiness.getUserId());
        paraMap.put("quotaType", cBusiness.getQuotaType());
        return cOrderMapper.insertOrder(paraMap);
    }*/

    @Override
    public OrderTotalDto getOrderTotal(ParaMap paraMap) throws Exception {
        return cOrderMapper.getOrderTotal(paraMap);
    }

    @Override
    public PageInfo<COrderListDto> getStartOrder(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "id desc");
        List<COrderListDto> list = cOrderMapper.getStartOrder(paraMap);
        return new PageInfo<COrderListDto>(getQuotaDescByType(list));
    }

    @Override
    public PageInfo<COrderListDto> getEndOrder(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "a.id desc");
        List<COrderListDto> list = cOrderMapper.getEndOrder(paraMap);
        return new PageInfo<COrderListDto>(getQuotaDescByType(list));
    }

    @Override
    public PageInfo<COrderListDto> getClosedOrder(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "id desc");
        List<COrderListDto> list = cOrderMapper.getClosedOrder(paraMap);
        return new PageInfo<COrderListDto>(getQuotaDescByType(list));
    }

    @Override
    public OrderTotalDto getNetWorkOrderTotal(ParaMap paraMap) throws Exception {
        return cOrderMapper.getNetWorkOrderTotal(paraMap);
    }

    @Override
    public PageInfo<COrderListDto> getStartNetWorkOrder(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "id desc");
        List<COrderListDto> list = cOrderMapper.getStartNetWorkOrder(paraMap);
        return new PageInfo<COrderListDto>(getQuotaDescByType(list));
    }

    @Override
    public PageInfo<COrderListDto> getEndNetWorkOrder(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "a.id desc");
        List<COrderListDto> list = cOrderMapper.getEndNetWorkOrder(paraMap);
        return new PageInfo<COrderListDto>(getQuotaDescByType(list));
    }

    @Override
    public PageInfo<COrderListDto> getClosedNetWorkOrder(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "id desc");
        List<COrderListDto> list = cOrderMapper.getClosedNetWorkOrder(paraMap);
        return new PageInfo<COrderListDto>(getQuotaDescByType(list));
    }

    @Override
    public PageInfo<COrderListDto> getSuccessNetWorkOrder(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "id desc");
        List<COrderListDto> list = cOrderMapper.getSuccessNetWorkOrder(paraMap);
        return new PageInfo<COrderListDto>(getQuotaDescByType(list));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int saveFreeOrder(ParaMap paraMap) throws Exception {
        CheckBusinessDto checkBusinessDto = cOrderMapper.checkStoreById(paraMap);
        if(null == checkBusinessDto){
            throw new CustomerException(2, "下单失败!商户ID不正确");
        }
        if(1 == checkBusinessDto.getIsSelf()){
            throw new CustomerException(2, "该商家你不能消费!");
        }
        //商家当天订单额度
        double orderSum = Double.parseDouble(cOrderMapper.getOrderSum(paraMap));
        double maxAmount = Double.parseDouble(checkBusinessDto.getMaxAmount());
        DecimalFormat df=new DecimalFormat(".##");
        if((orderSum + Double.parseDouble(paraMap.get("money")+"")) > maxAmount){
            throw new CustomerException(2, "下单失败!商户今日的剩余消费额度:"+((maxAmount-orderSum)>0?df.format(maxAmount-orderSum):0.0)+"元");
        }
        if (Double.parseDouble(paraMap.get("money") + "") > 1000) {
            paraMap.put("consumptionPic", "");
        } else {
            paraMap.put("consumptionPic", "/image/1.png");
        }

        //抵扣
        Map<String, Object> map = cOrderMapper.getMyShandians(paraMap);
        Integer offsetType = Integer.parseInt(paraMap.get("offsetType") + "");
        BigDecimal offsetNum = new BigDecimal(paraMap.get("offsetNum") + "");
        BigDecimal bdsz = offsetNum.multiply(new BigDecimal("-1"));//负的数值
        BigDecimal money = new BigDecimal(paraMap.get("money") + "");
        if (offsetType == 2) {
            if (offsetNum.compareTo(new BigDecimal(map.get("shandian") + "")) == 1) {
                throw new CustomerException(2, "可抵用鼓励点余额不足,无法抵用!");
            }
            if (money.compareTo(offsetNum) == -1) {
                throw new CustomerException(2, "该订单最多抵用鼓励点:" + money);
            }
            //保存xfz_dzb
            paraMap.put("leixing", 0);
            paraMap.put("bdsz", bdsz);
            paraMap.put("jieyu", (new BigDecimal(map.get("shandian")+"").subtract(offsetNum)).toString());
            cOrderMapper.saveXfzDzb(paraMap);
            cOrderMapper.updateShandian(paraMap);
        }

        if (offsetType == 3) {
            if (offsetNum.compareTo(new BigDecimal(map.get("shandian2") + "")) == 1) {
                throw new CustomerException(2, "可抵用奖励点余额不足,无法抵用!");
            }
            if (money.compareTo(offsetNum) == -1) {
                throw new CustomerException(2, "该订单最多抵用奖励点:" + money);
            }

            //保存passive_shandian
            paraMap.put("bdsz", bdsz);
            paraMap.put("jieyu", (new BigDecimal(map.get("shandian2")+"").subtract(offsetNum)).toString());
            cOrderMapper.savePassiveShandian(paraMap);
            cOrderMapper.updateShandian2(paraMap);
        }

        paraMap.put("offsetRangli", "0");
        if (offsetType == 1) {
            if (offsetNum.compareTo(new BigDecimal(map.get("reusePoint") + "")) == 1) {
                throw new CustomerException(2, "可抵用代金券余额不足,无法抵用!");
            }
            BigDecimal rangli = new BigDecimal(paraMap.get("rangli") + "");
            BigDecimal offsetproportion = new BigDecimal(cOrderMapper.getOffsetproportion());
            BigDecimal rangliMoney = money.multiply(rangli);
            BigDecimal param = offsetNum.multiply(offsetproportion).divide(rangliMoney, 2, BigDecimal.ROUND_DOWN);
            if (param.compareTo(new BigDecimal("0.01")) == -1) {
                throw new CustomerException(2, "该订单最少抵用代金券:" + ((new BigDecimal("0.01")).multiply(rangliMoney).divide(offsetproportion, 2)));
            }
            if (param.compareTo(rangli) == 1) {
                throw new CustomerException(2, "该订单最多抵用代金券:" + (rangli.multiply(rangliMoney).divide(offsetproportion, 2, BigDecimal.ROUND_DOWN)));
            }
            paraMap.put("offsetRangli", rangli.subtract(param) + "");

            //保存xfz_dzb
            paraMap.put("leixing", 20); //再消分
            paraMap.put("bdsz", bdsz);
            paraMap.put("jieyu", (new BigDecimal(map.get("reusePoint")+"").subtract(offsetNum)).toString());
            cOrderMapper.saveXfzDzb(paraMap);
            cOrderMapper.updateReusePoint(paraMap);
        }

        paraMap.put("orderId", "SS"+(System.currentTimeMillis()+"").substring(3)+((int)((Math.random()*9+1)*100)));
        paraMap.put("sellerId", checkBusinessDto.getUserId());
        paraMap.put("quotaType", checkBusinessDto.getQuotaType());
        int count = cOrderMapper.saveFreeOrder(paraMap);

        //商铺会员
        if (0 == checkBusinessDto.getIsMember()) {
            cOrderMapper.saveBusinessMember(paraMap);
        }
        return count;
    }

    @Override
    public ScanBusinessDto scanBusiness(ParaMap paraMap) throws Exception {
        return cOrderMapper.scanBusiness(paraMap);
    }

    public List<COrderListDto> getQuotaDescByType(List<COrderListDto> list){
        for(COrderListDto cOrderListDto:list){
            switch (cOrderListDto.getQuotaType()){
                case 0:
                    cOrderListDto.setQuotaDesc(QuataEnum.DEFAULT_QUOTA.getQuataDesc());
                    break;
                case 1:
                    cOrderListDto.setQuotaDesc(QuataEnum.MIN.getQuataDesc());
                    break;
                case 2:
                    cOrderListDto.setQuotaDesc(QuataEnum.MID.getQuataDesc());
                    break;
                case 3:
                    cOrderListDto.setQuotaDesc(QuataEnum.BIG.getQuataDesc());
                    break;
            }
        }
        return list;
    }
}
