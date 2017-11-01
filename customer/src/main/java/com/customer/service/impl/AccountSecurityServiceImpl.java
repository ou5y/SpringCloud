package com.customer.service.impl;

import com.customer.dao.my.AccountSecurityDao;
import com.customer.entity.BankCard;
import com.customer.service.AccountSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@Service("accountSecurityService")

public class AccountSecurityServiceImpl implements AccountSecurityService{

    @Autowired
    private AccountSecurityDao accountSecurityDao;

    // 查询银行卡
    @Override
    public List<Map<String,Object>> queryMyBankCard(Map<String,Object> params){
        return accountSecurityDao.queryMyBankCard(params);
    }

    // 查询银行卡信息
    @Override
    public BankCard findBankCard(Map<String,Object> params){
        return accountSecurityDao.findBankCard(params);
    }

    // 绑定银行卡
    @Override
    public int addBankCard(Map<String,Object> params){
        int count = accountSecurityDao.addBankCard(params);
        String xingming = accountSecurityDao.queryUserXingming(params);
        if(xingming==null || xingming.equals("") || xingming.equalsIgnoreCase("null")){
            accountSecurityDao.updateUserXingming(params);
        }
        return count;
    }

    // 解绑银行卡
    @Override
    public int deleteBankCard(Map<String,Object> params){
        return accountSecurityDao.deleteBankCard(params);
    }

    // 更新交易密码
    @Override
    public int updateTransPwd(Map<String,Object> params){
        return  accountSecurityDao.updateTransPwd(params);
    }

    // 更新交易密码
    @Override
    public int updateLoginPwd(Map<String,Object> params){
        return  accountSecurityDao.updateLoginPwd(params);
    }

    // 查询密码
    @Override
    public Map<String,Object> queryPwd(Map<String,Object> params){
        return accountSecurityDao.queryPwd(params);
    }

}
