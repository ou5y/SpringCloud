package com.azcx9d.Thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.Business;
import com.azcx9d.common.entity.OrderForm;
import com.azcx9d.common.util.Arith;
import com.azcx9d.user.dao.ProfitCenterDao;


public class OrderClean implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(OrderClean.class);
	
    private ProfitCenterDao dao;

	/**
	 * 订单号
	 */
	private long orderId = 0;
	
	/**
	 * 省代理手续费
	 */
	private final double PROVINCE = 0.002;
	
	/**
	 * 市代理手续费
	 */
	private final double CITY = 0.003;
	
	/**
	 * 区代理手续费
	 */
	private final double AREA = 0.004;
	
	/**
	 * 省行业代理手续费
	 */
	private final double PROVINCE_TRADE = 0.005;
	
	/**
	 * 市行业代理手续费
	 */
	private final double CITY_TRADE = 0.003;
	
//	/**
//	 * 区域行业代理费
//	 */
//	private final double AREA_TRADE = 0.004;
	
	
	
	public OrderClean(ProfitCenterDao dao, long orderId) {
		this.dao = dao;
		this.orderId = orderId;
	}



	@Override
	public void run() {
		synchronized (this) {
			logger.debug("run方法开始执行：" + orderId);
			OrderForm oForm = dao.orderFormDetail(orderId);

			double orderMoney = oForm.getMoney();// 消费金额

			Business business = dao.queryBusinessDetail(oForm.getStoreId());

			Map<String, Object> params = new HashMap<String, Object>(0);

			if (!business.getProvinceCode().equals("0")) {
				params.put("provinceId", business.getProvinceCode());
			}

			if (!business.getCityCode().equals("0")) {
				params.put("cityId", business.getCityCode());
			}

			if (!business.getAreaId().equals("0")) {
				params.put("areaId", business.getAreaId());
			}

			if (!business.getOperateType().equals("0")) {
				params.put("tradeId", business.getOperateType());
			}

			boolean flag = true;

			List<Map<String, String>> proviceTrades = dao.queryProviceTrade(params);        //省行业代理
			List<Map<String, String>> cityTrades = dao.queryCityTrade(params);            //市行业代理
//		List<Map<String,String>> areaTrades = dao.queryAreaTrade(params);			//区行业代理

			List<Map<String, String>> proviceAgents = dao.queryProviceAgent(params);        //省代理
			List<Map<String, String>> cityAgents = dao.queryCityAgent(params);            //市代理
			List<Map<String, String>> areaAgents = dao.queryAreaAgent(params);            //区代理

			if (proviceTrades != null && proviceTrades.size() > 0) {
				for (Map<String, String> provinceTrade : proviceTrades) {
					String userId = String.valueOf(provinceTrade.get("userId"));
					params.put("userId", userId);
					Agency agency = dao.queryByUserId(params);
					double percent = Double.valueOf(provinceTrade.get("percent"));
					double shandian = Arith.mul(orderMoney, PROVINCE_TRADE * percent, 2);
					saveCustomBill(params, 3, agency.getShandian2(), shandian);            //生成对账表记录,被动善点
					params.put("newShandian", Arith.add(agency.getShandian2(), shandian));
					dao.updateShandian2(params);                    //更新代理商被动善点
				}
				flag = false;
			}

			if (cityTrades != null && cityTrades.size() > 0) {
				for (Map<String, String> cityTrade : cityTrades) {
					String userId = String.valueOf(cityTrade.get("userId"));
					params.put("userId", userId);
					Agency agency = dao.queryByUserId(params);
					double percent = Double.valueOf(cityTrade.get("percent"));
					double shandian = Arith.mul(orderMoney, CITY_TRADE * percent, 2);
					saveCustomBill(params, 3, agency.getShandian2(), shandian);            //生成对账表记录,被动善点
					params.put("newShandian", Arith.add(agency.getShandian2(), shandian));
					dao.updateShandian2(params);
				}
				flag = false;
			}

//		if(areaTrades!=null && areaTrades.size()>0){
//			for(Map<String,String> areaTrade : areaTrades){
//				String userId = String.valueOf(areaTrade.get("userId"));
//				params.put("userId", userId);
//				Agency agency  = dao.queryByUserId(params);
//				double percent = Double.valueOf(areaTrade.get("percent"));
//				double shandian = Arith.mul(orderMoney, AREA_TRADE*percent, 2);
//				saveCustomBill(params, 0, agency.getShandian(), shandian);			//生成对账表记录
//				dao.updateShandian(params);				//更新代理商善点
//			}
//			flag = false;
//		}

			if (proviceAgents != null && proviceAgents.size() > 0) {
				for (Map<String, String> proviceAgent : proviceAgents) {
					String userId = String.valueOf(proviceAgent.get("userId"));
					params.put("userId", userId);
					Agency agency = dao.queryByUserId(params);
					double percent = Double.valueOf(proviceAgent.get("percent"));
					double shandian = Arith.mul(orderMoney, PROVINCE * percent, 2);
					saveCustomBill(params, 3, agency.getShandian2(), shandian);            //生成对账表记录,被动善点
					params.put("newShandian", Arith.add(agency.getShandian2(), shandian));
					dao.updateShandian2(params);
				}
				flag = false;
			}

			if (cityAgents != null && cityAgents.size() > 0) {
				for (Map<String, String> cityAgent : cityAgents) {
					String userId = String.valueOf(cityAgent.get("userId"));
					params.put("userId", userId);
					Agency agency = dao.queryByUserId(params);
					double percent = Double.valueOf(cityAgent.get("percent"));
					double shandian = Arith.mul(orderMoney, CITY * percent, 2);
					saveCustomBill(params, 3, agency.getShandian2(), shandian);            //生成对账表记录,被动善点
					params.put("newShandian", Arith.add(agency.getShandian2(), shandian));
					dao.updateShandian2(params);
				}
				flag = false;
			}

			if (areaAgents != null && areaAgents.size() > 0) {
				for (Map<String, String> areaAgent : areaAgents) {
					String userId = String.valueOf(areaAgent.get("userId"));
					params.put("userId", userId);
					Agency agency = dao.queryByUserId(params);
					double percent = Double.valueOf(String.valueOf(areaAgent.get("percent")));
					double shandian = Arith.mul(orderMoney, AREA * percent, 2);
					saveCustomBill(params, 3, agency.getShandian2(), shandian);            //生成对账表记录,被动善点
					params.put("newShandian", Arith.add(agency.getShandian2(), shandian));
					dao.updateShandian2(params);
				}
				flag = false;
			}

			///////////////////-------------------更新商家积分善心------------------///////////////////
			double businessPoint = calcBusinessPoint(orderMoney, oForm.getRangli());            // 商家积分
			double businessShanxin = calcBusinessShanxin(businessPoint);                        // 商家善心
			params.put("userId", oForm.getSellerId());
			Agency bUser = dao.queryByUserId(params);
			params.put("newShanxin", Arith.add(bUser.getShanxin(), businessShanxin));
			params.put("newJifen", Arith.add(bUser.getJifen(), businessPoint));

			saveCustomBill(params, 1, bUser.getJifen(), businessPoint);            //记录积分变动
			saveCustomBill(params, 2, bUser.getShanxin(), businessShanxin);        //记录善心变动
			dao.updateShanxin(params);                                            //更新用户表信息


			///////////////////-------------------更新用户积分善心------------------///////////////////
			double userPoint = calcUserPoint(orderMoney, oForm.getRangli());                    // 用户积分
			double userShanxin = calcUserShanxin(userPoint);                                    // 用户善心
			params.put("userId", oForm.getUserId());

			Agency agency = dao.queryByUserId(params);
			params.put("newShanxin", Arith.add(agency.getShanxin(), userShanxin));
			params.put("newJifen", Arith.add(agency.getJifen(), userPoint));

			saveCustomBill(params, 1, agency.getJifen(), userPoint);            //记录积分变动
			saveCustomBill(params, 2, agency.getShanxin(), userShanxin);        //记录善心变动
			dao.updateShanxin(params);                                            //更新用户表信息

			if (flag) {
				//  没有任何善点接受人时
			}

		}
	}
	
	/**
	 * 计算商家应得积分
	 * @param orderMoney
	 * @param rangli
	 * @return double
	 */
	private static double calcBusinessPoint(double orderMoney,double rangli){
		return Arith.mul(orderMoney,rangli,2);
	}
	
	/**
	 * 计算商家应得善心
	 * @param businessPoint
	 * @return
	 */
	private static double calcBusinessShanxin(double businessPoint){
		return Arith.div(businessPoint, 100, 2);
	}
	
	/**
	 * 计算用户应得积分
	 * @param orderMoney
	 * @param rangli
	 * @return double
	 */
	private static double calcUserPoint(double orderMoney,double rangli){
		double userPoint = 0.0;
		String mul = String.valueOf(Arith.mul(rangli, 100));
		String percent = mul.substring(0, mul.indexOf("."));
		switch (percent) {
			case "5":
				userPoint = Arith.mul(orderMoney,0.5,2);
				break;
			case "10":
				userPoint = Arith.mul(orderMoney,0.75,2);
				break;
			case "20":
				userPoint = Arith.mul(orderMoney,1,2);
				break;
	
			default:
				userPoint = 0.0;
				break;
			}
		return userPoint;
	}

	/**
	 * 计算用户应得善心
	 * @param userPoint
	 * @return double
	 */
	private static double calcUserShanxin(double userPoint){
		return Arith.div(userPoint, 500, 2);
	}
	
	/**
	 * 添加积分/善心/善点  变动记录
	 * @param params	参数map
	 * @param leixing	0：善点    1：积分    2：善心
	 * @param oldsz		原来拥有的数值
	 * @param bdsz		变动数值
	 */
	public void saveCustomBill(Map<String, Object> params,int leixing,double oldsz,double bdsz){
		params.put("jieyu", Arith.add(oldsz,bdsz));
		params.put("leixing", leixing);				
		params.put("bdsz", bdsz);
		dao.saveCustomBill(params);
	}
	
}
