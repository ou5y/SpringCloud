package com.azcx9d.agency.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.azcx9d.agency.base.Const;
import com.azcx9d.agency.dao.ExchangeDao;
import com.azcx9d.agency.entity.Exchange;
import com.azcx9d.agency.service.ExchangeService;
import com.azcx9d.common.util.Arith;
import com.azcx9d.common.util.Page;
import com.azcx9d.user.dao.UserDao;
import com.azcx9d.user.entity.User;

@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService{
	
	@Autowired
	private ExchangeDao exchangeDao;
	
	@Autowired
	private UserDao userDao;

	// 查询善点明细
	@Override
	public Page queryBalanceDetail(Page page,Map<String, Object> params) {
		int totalRow = exchangeDao.countBalance(params);
		page.setTotalRow(totalRow);
		params.put("offset", page.getOffset());
		List<Map<String,Object>> list = exchangeDao.queryBalance(params);
		page.setPageList(list);
		return page;
	}
	
	// 查询兑换记录列表
	@Override
	public Page queryExchangeList(Page page,Map<String, Object> params) {
		int totalRow = exchangeDao.countExchange(params);
		page.setTotalRow(totalRow);
		params.put("offset", page.getOffset());
		List<Map<String,Object>> list = exchangeDao.queryExchangeList(params);
		page.setPageList(list);
		return page;
	}

	// 查询兑换记录详情
	@Override
	public Exchange queryDetail(Map<String, Object> params) {
		return exchangeDao.queryDetail(params);
	}

	// 统计总兑换金额
	@Override
	public double countTotalConversion(Map<String,Object> params){
		return exchangeDao.countTotalConversion(params);
	}
	
	// 查询登录用户的善点
	@Override
	public Map<String,Object> queryMyShandian(Map<String,Object> params){
		return exchangeDao.queryMyShandian(params);
	}
	
	// 查询大盘善心比例
	@Override
	public Map<String,Object> queryLovePercent(){
		return exchangeDao.queryLovePercent();
	}
	
	// 保存兑换记录
	@Override
	public int saveExchange(Map<String,Object> params) throws Exception{
		int count =0;
		//计算兑换比例
		double exchangeShandian = (double) params.get("exchangeShandian");
		User user = userDao.selectUserById((long)params.get("userId"));
		int type = (int)params.get("type");
		double currentShandian = 0.0 ;
		double currentJifen = 0.0;
		if(type==0){
			currentShandian = user.getShandian()-exchangeShandian;   //结余
			currentJifen = user.getJifen()-exchangeShandian;
		}else{
			currentShandian = user.getShandian2()-exchangeShandian;   //结余
			currentJifen = user.getJifen2()-exchangeShandian;
		}

		params.put("currentShandian", currentShandian);		//扣除后的善点
		params.put("currentJifen", currentJifen);				//扣除后的积分
		params.put("state", 0);

		count += exchangeDao.saveExchange(params);
		if(type==0){
			count += userDao.updateShandian(params);
			params.put("exchangeShandian", Arith.sub(0, exchangeShandian));
			count+=exchangeDao.saveCustomBill(params);
			params.put("leixing",1);
			params.put("currentShandian", currentJifen);		//扣除后的积分
			count+=exchangeDao.saveCustomBill(params);
		}else {
			count += userDao.updateShandian2(params);
			params.put("exchangeShandian", Arith.sub(0, exchangeShandian));
			count+=exchangeDao.saveCustomBill(params);
		}
		return count;
	}
	
	// 计算兑换金额
	@Override
	public Map<String,Object> calcShandian(Map<String,Object> params,int type) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>(); 
		User user = userDao.selectUserById((long)params.get("userId"));
		//计算兑换比例
		double exchangeShandian = (double) params.get("exchangeShandian");
		double poundage = 0.0;							//手续费
		double shandian = 0.0;
		double jifen = 0.0;
		if(type==0){
			shandian = user.getShandian();
			jifen = user.getJifen();
			if(exchangeShandian>jifen) return null;
		}else{
			shandian = user.getShandian2();
			jifen = user.getJifen2();
		}
		if(shandian>=exchangeShandian){
			// 积分、善点 大于等于兑换善点
			switch ((int)params.get("arrivalMode")) {
				case 1:
					poundage = Arith.mul(exchangeShandian,Const.AGENT_POUNDAGE_1,2);
					break;
				case 3:
					poundage = Arith.mul(exchangeShandian,Const.AGENT_POUNDAGE_3,2);
					break;
				case 7:
					poundage = 5;			
					break;
				default:
					poundage = Arith.mul(exchangeShandian,Const.AGENT_POUNDAGE_1,2);
					break;
			}
			
			poundage = poundage>=5?poundage:5;
			double realMoney = exchangeShandian-poundage;				//实际到账金额
			result.put("poundage", poundage);
			result.put("realMoney", realMoney);
			return result;
		}else{
			return null;
		}
	}
		
	
}
