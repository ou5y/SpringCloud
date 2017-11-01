package com.customer.util;



import com.customer.config.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 通过redis存储和验证token
 *
 * @author Administrator
 *
 */
@Service
public class TokenManagerDao {

    private Logger logger = LoggerFactory.getLogger(TokenManagerDao.class);

//    @Autowired
//    private RedisManager redisManager;
//
//
//
//    @Override
//    public TokenModel createToken(int userId) {
//        String token = UUID.randomUUID().toString().replace("-", "");
//        TokenModel model = new TokenModel(userId, token);
//        //redisManager.set(token,Long.toString(userId), redisManager.getExpire());
//
//        redisManager.set(String.valueOf(userId),token, redisManager.getExpire());
//        return model;
//    }
//
//
//
//    @Override
//    public TokenModel getToken(String authentication) {
//
//        if (authentication == null || authentication.length() == 0)
//            return null;
//        String[] param = authentication.split("_");
//        if (param.length != 2)
//            return null;
//        String userId =param[0];
//        String token = param[1];
//
//        return new TokenModel(Integer.valueOf(userId), token);
//    }
//
//    @Override
//    public TokenModel getTokenByUserId(String userId) {
//
//        if (userId == null || userId.length() == 0)
//            return null;
//
//        String token =redisManager.get(userId);
//        return new TokenModel(Integer.valueOf(userId), token);
//    }
//
//    @Override
//    public boolean checkToken(TokenModel model) {
//        if (model==null)
//            return false;
////		String userId = redisManager.get(model.getToken());
//        String token = redisManager.get(model.getUserId()+"");
//        String t=model.getToken();
//        if (token!=null){
//            if(token.equals(t)){
//                redisManager.expire(model.getToken(), redisManager.getExpire());
//                return true;
//            }else{
//                return false;
//            }
//        }else{
//            return false;
//        }
////		try
////		{
////			 user_id=  Long.parseLong(userId);
////
////		}
////		catch (Exception e)
////		{
////			logger.error(e.getMessage());
////			return false;
////		}
//
//
////
////		if (user_id == 0 || user_id!= model.getUserId())
////			return false;
////		redisManager.expire(model.getToken(), redisManager.getExpire());
////		return true;
//    }
//
//    @Override
//    public void deleteToken(String token) {
//        redisManager.del(token);
//
//    }
//
//
//
//    public RedisManager getRedisManager() {
//        return redisManager;
//    }
//
//    public void setRedisManager(RedisManager redisManager) {
//        this.redisManager = redisManager;
//
//        /**
//         * 初始化redisManager
//         */
//        this.redisManager.init();
//    }
//
//    public static byte[] long2Bytes(long num) {
//        byte[] byteNum = new byte[8];
//        for (int ix = 0; ix < 8; ++ix) {
//            int offset = 64 - (ix + 1) * 8;
//            byteNum[ix] = (byte) ((num >> offset) & 0xff);
//        }
//        return byteNum;
//    }
//
//    public static long bytes2Long(byte[] byteNum) {
//        long num = 0;
//        for (int ix = 0; ix < 8; ++ix) {
//            num <<= 8;
//            num |= (byteNum[ix] & 0xff);
//        }
//        return num;
//    }
}
