package com.customer.dao;


import com.customer.dto.*;
import com.customer.entity.CUserRoleAttribute;
import com.customer.entity.User;
import com.customer.entity.UserWeixin;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/4/24.
 */

//@Mapper
public interface UserDao{



    User findByName(String name) ;

//    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name) ;

    //@Select("SELECT id,name,phone FROM user")
    //@ResultType(User.class)
    List<User> findAll() ;


    User findByPhone(String phone);

    UserDto findUserByUserIdAndPass(Map<String,Object> map);

    CUserRoleAttribute findByUserIdWithRoleId(int userId);

    int insert(User user);

    UserDto findById(Integer id);

    Integer findTotalIncome(Map<String,Object> map);

    List<CMyIntegralRecordDto> findIntegralRecodeAll(Map<String, Object> parmarter);

    List<CMyShandianRecode> findLovePointRecode(Map<String, Object> parmarter);

    List<RoleListDto> findUserRole(int id);

    Integer findTotalRecommodIncome(Map<String, Object> map);

    List<MyRecommendPointRecordDto> findRecommendPointRecord(Map<String, Object> map);

    /**
     * 代理业务统计
     * @param map
     * @return Map<String,Object>
     */
    Map<String,Object> queryAgencyStatistics(Map<String,Object> map);

//    /**
//     * 列表查询每天总收益
//     * @param params
//     * @return  List<Map<String,Object>>
//     */
//    List<Map<String,Object>> queryAgencyLineChart(Map<String,Object> params);

    /**
     * 列表查询每天总收益
     * @param params
     * @return  List<Map<String,Object>>
     */
    List<AgencyProfit> queryAgencyLineChart(Map<String,Object> params);

    /**
     * 查询全部区域
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryAllAreas(Map<String,Object> params);

    /**
     * 查询区域相信信息
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryAreaInfo(Map<String,Object> params);

    /**
     * 查询全部区域编号
     * @param areaId
     * @return List<Object>
     */
    List<Object> queryByProvinceCode(String areaId);

    /**
     * 查询全部区域编号
     * @param areaId
     * @return List<Object>
     */
    List<Object> queryByCityCode(String areaId);

    /**
     * 单独查询省
     * @param params
     * @return
     */
    List<Map<String,Object>> queryAgencyProvince(Map<String,Object> params);

    /**
     * 单独查询市
     * @param params
     * @return
     */
    List<Map<String,Object>> queryAgencyCity(Map<String,Object> params);

    /**
     * 查询单个区域名称行业名称
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> querySingleArea(Map<String,Object> params);

    /**
     * 查询区域详情
     * @param params
     * @return List<AreaDetailDto>
     */
    List<AreaDetailDto> queryAreaDetail(Map<String,Object> params);

    /**
     * 查询交易密码
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryTransPwd(Map<String,Object> params);

    User findByOpenid(String openid);

    int updateUserOpenid(Map map);

    int weixinInsert(UserWeixin user);

    /**
     * 查询注册赠送积分
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryRegisterReward(Map<String,Object> params);

}
