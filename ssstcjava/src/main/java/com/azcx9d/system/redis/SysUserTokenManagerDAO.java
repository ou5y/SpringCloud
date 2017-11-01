package com.azcx9d.system.redis;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.util.UuidUtil;
import com.azcx9d.common.cache.RedisManager;
import org.apache.commons.lang.StringUtils;

/**
 * Created by HuangQing on 2017/4/10 0010 13:54.
 */
public class SysUserTokenManagerDAO implements SysUserTokenManager {

    private RedisManager redisManager;

    @Override
    public TokenModel createToken(int userId) {

        String uuid = UuidUtil.get32UUID();
        TokenModel model = new TokenModel(userId, uuid);
        //redisManager.set(token,Long.toString(userId), redisManager.getExpire());

        //缓存格式为  ： key : sysueer + id   value : uuid
        redisManager.set(RedisCacheType.SYSUSER + String.valueOf(userId),uuid, redisManager.getExpire());
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model==null){
            return false;
        }
        String token = redisManager.get(RedisCacheType.SYSUSER + model.getUserId());  //获取redis中的token
        if(StringUtils.isBlank(token)){
            return false;
        }
        String t=model.getToken();
        if (!token.equals(t))
        {
            return false;
        }
        redisManager.expire(model.getToken(), redisManager.getExpire());
        return true;
    }

    @Override
    public TokenModel getTokenModel(String authentication) {
        if (authentication == null || authentication.length() == 0){
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2){
            return null;
        }
        String userId =param[0].replace(RedisCacheType.SYSUSER,"");
        String token = param[1];

        return new TokenModel(Integer.valueOf(userId), token);
    }

    @Override
    public void deleteToken(String userId) {
        redisManager.del(RedisCacheType.SYSUSER + userId);
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;

        /**
         * 初始化redisManager
         */
        this.redisManager.init();
    }

}
