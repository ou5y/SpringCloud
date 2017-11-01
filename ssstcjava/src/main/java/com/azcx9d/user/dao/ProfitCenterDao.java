package com.azcx9d.user.dao;

import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.Business;
import com.azcx9d.common.entity.OrderForm;
import com.azcx9d.scheduled.dto.OderFormDto;
import com.azcx9d.user.entity.User;
import com.azcx9d.user.entity.MarketEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 收益中心
 * @author fby
 *
 */
public interface ProfitCenterDao {
	
	/**
	 * 订单详情
	 * @param id  订单id
	 * @return 订单
	 */
	OrderForm orderFormDetail(long id);
	
	
	/**
	 * 商家信息
	 * @param id id
	 * @return 商家信息
	 */
	Business queryBusinessDetail(int id);
	
	/**
	 * 结算
	 * @param user user
	 * @return
	 */
	int updateUser(User user);
	
	
	/**
	 * 
	 * @param user user
	 * @return
	 */
	User queryUserByAreaId(User user);

	/**
	 * 当天结算善点
	 * @param user user
	 * @return
	 */
	int updateUserLovePoints(User user) throws SQLException;

	/**
	 * 查询消费者大盘信息
	 * @return
	 */
	MarketEntity queryMarket();

	/**
	 *
	 * @param giveUpProfitPercentage 商家让利比例(1:5%,2:10%,3:20%)
	 * @return
	 */
	double getVeryDayMarketTotalMoney(@Param("giveUpProfitPercentage") int giveUpProfitPercentage);



	/**
	 * 获取参与分配的用户列表
	 * @return
	 */
	List<User> queryCustomerList();


	/**
	 *
	 * @param map map
	 * @return int
	 */
	int insertLovePointRecord(Map<String,Object> map) throws SQLException;

	/**
	 *
	 * @param map map
	 * @return
	 */
	int insertMarketCustomer(Map<String, Object> map)throws SQLException;


	/**
	 *
	 * @param map map
	 * @return
	 */
	int insertMarketBusiness(Map<String, Object> map);

	/**
	 * 查询商家大盘信息
	 * @return
	 */
	MarketEntity queryMarketBusiness();

	/**
	 * 获取参与分配的商家列表
	 * @return
	 */
	List<User> queryBusinessList();

	/**
	 *
	 * @return 周统计列表
	 */
    List<OderFormDto> queryStatisticsList();

	/**
	 * 商家限额调整
	 * @param b 商家
	 * @return
	 */
	int doAdjust(Business b);

	/**
	 *
	 * @param map map
	 * @return
	 */
	Agency queryUploadUserInfo(Map<String,Object> map);
	
	/**
	 * 省行业代理
	 * @param params
	 * @return List<Map<String,String>>
	 */
	List<Map<String,String>> queryProviceTrade(Map<String,Object> params);
	
	/**
	 * 市行业代理
	 * @param params
	 * @return List<Map<String,String>>
	 */
	List<Map<String,String>> queryCityTrade(Map<String,Object> params);
	
	/**
	 * 区行业代理
	 * @param params
	 * @return List<Map<String,String>>
	 */
	List<Map<String,String>> queryAreaTrade(Map<String,Object> params);
	
	/**
	 * 省代理
	 * @param params
	 * @return List<Map<String,String>>
	 */
	List<Map<String,String>> queryProviceAgent(Map<String,Object> params);
	
	/**
	 * 市代理
	 * @param params
	 * @return List<Map<String,String>>
	 */
	List<Map<String,String>> queryCityAgent(Map<String,Object> params);
	
	/**
	 * 区代理
	 * @param params
	 * @return List<Map<String,String>>
	 */
	List<Map<String,String>> queryAreaAgent(Map<String,Object> params);
	
	/**
	 * 查询代理商
	 * @param params
	 * @return Agency
	 */
	Agency queryByUserId(Map<String,Object> params);
	
	/**
	 * 更新善点
	 * @param params
	 * @return int
	 */
	int updateShandian(Map<String,Object> params);

	/**
	 * 更新被动善点
	 * @param params
	 * @return int
	 */
	int updateShandian2(Map<String,Object> params);
	
	/**
	 * 更新善心积分
	 * @param params
	 * @return int 受影响行数
	 */
	int updateShanxin(Map<String,Object> params);
	
	/**
	 * 新增善点变动记录
	 * @param params
	 * @return int 受影响行数
	 */
	int saveCustomBill(Map<String,Object> params);
	
}
