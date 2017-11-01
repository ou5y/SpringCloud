package com.azcx9d.agency.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.user.dao.UserDao;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public interface AgencyLoginDao extends UserDao{
	
    Agency selectUser(Agency agency) throws SQLException;
    
    Agency selectUserById(long id) throws SQLException;
    
}
