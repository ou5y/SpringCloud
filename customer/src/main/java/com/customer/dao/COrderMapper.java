package com.customer.dao;

import com.customer.dto.COrderListDto;
import com.customer.dto.CheckBusinessDto;
import com.customer.dto.OrderTotalDto;
import com.customer.dto.ScanBusinessDto;
import com.customer.entity.CBusiness;
import com.customer.entity.COrder;
import com.customer.entity.ParaMap;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/3/31 0031.
 */
public interface COrderMapper {

    CheckBusinessDto checkStoreById(ParaMap paraMap);

    String getOrderSum(ParaMap paraMap);

//    int insertOrder(ParaMap paraMap);

    OrderTotalDto getOrderTotal(ParaMap paraMap);

    List<COrderListDto> getStartOrder(ParaMap paraMap);

    List<COrderListDto> getEndOrder(ParaMap paraMap);

    List<COrderListDto> getClosedOrder(ParaMap paraMap);

    OrderTotalDto getNetWorkOrderTotal(ParaMap paraMap);

    List<COrderListDto> getStartNetWorkOrder(ParaMap paraMap);

    List<COrderListDto> getEndNetWorkOrder(ParaMap paraMap);

    List<COrderListDto> getClosedNetWorkOrder(ParaMap paraMap);

    List<COrderListDto> getSuccessNetWorkOrder(ParaMap paraMap);

    int saveFreeOrder(ParaMap paraMap);

//    int isBusinessMember(ParaMap paraMap);

    int saveBusinessMember(ParaMap paraMap);

    ScanBusinessDto scanBusiness(ParaMap paraMap);

    Map<String, Object> getMyShandians(ParaMap paraMap);

    String getOffsetproportion();

    int saveXfzDzb(ParaMap paraMap);

    int updateShandian(ParaMap paraMap);

    int savePassiveShandian(ParaMap paraMap);

    int updateShandian2(ParaMap paraMap);

    int updateReusePoint(ParaMap paraMap);
}
