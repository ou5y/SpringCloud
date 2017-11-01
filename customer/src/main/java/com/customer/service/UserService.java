package com.customer.service;

import com.customer.dto.AgencyBenefitsDto;
import com.customer.dto.AgencyProfit;
import com.customer.dto.LoginExecution;
import com.customer.dto.UserDto;
import com.customer.entity.CIncomeEntity;
import com.customer.entity.User;
import com.customer.exception.CustomerException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/4/24.
 */
public interface UserService {

    public User getUserByName(String username) throws Exception;

    public PageInfo<User> findAll() throws Exception;

    public LoginExecution<UserDto> login(Map<String, Object> user) throws Exception;

    public LoginExecution<UserDto> login(String phone) throws Exception;

    UserDto addUser(Map<String, Object> user) throws CustomerException;

    UserDto getUserDetail(Integer id);

    CIncomeEntity query(Map<String,Object> map);

    /**
     * 代理业务统计
     * @param map
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryAgencyStatistics(Map<String,Object> map);

    /**
     * 列表查询每天总收益
     * @param params
     * @return  List<Map<String,Object>>
     */
    List<AgencyProfit> queryAgencyLineChart(Map<String,Object> params);

    /**
     * 查询区域详情
     * @param params
     * @return PageInfo
     */
    PageInfo queryAreaDetail(Map<String,Object> params);

    /**
     * 查询全部区域编号
     * @param areaId
     * @return List<Object>
     */
    List<Object> queryAllAreaIds(String areaId);

    LoginExecution<UserDto> weixinLgoin(Map<String, Object> user) throws Exception;

    LoginExecution<UserDto> weixinBind(Map<String, Object> user) throws Exception;

}
