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
@Service(value ="tokenService")
public class TokenManagerServiceImpl implements TokenManager {

	private Logger logger = LoggerFactory.getLogger(TokenManagerServiceImpl.class);

	@Autowired
	private RedisManager redisManager;

	

	@Override
	public TokenModel createToken(int id,int userId) {
		String token = UUID.randomUUID().toString().replace("-", "");
		TokenModel model = new TokenModel(id,userId, token);
		redisManager.set(String.valueOf(id),token, redisManager.getExpire());
		return model;
	}



	@Override
	public TokenModel getToken(String authentication) {

		if (authentication == null || authentication.length() == 0)
			return null;
		String[] param = authentication.split("_");
		if (param.length != 3)
			return null;
		String id=param[0];
		String userId =param[1];
		String token = param[2];

		
		return new TokenModel(Integer.valueOf(id),Integer.valueOf(userId), token);
	}

//	@Override
//	public TokenModel getTokenByUserId(String id) {
//
//		if (id == null || id.length() == 0)
//			return null;
//
//		String token =redisManager.get(id);
//		return new TokenModel(Integer.valueOf(id),Integer.valueOf(), token);
//	}

	@Override
	public boolean checkToken(TokenModel model) {
		if (model==null)
			return false;
		String token = redisManager.get(model.getId()+"");
		String t=model.getToken();
		if (token!=null){
			if(token.equals(t)){
				redisManager.expire(model.getToken(), redisManager.getExpire());
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public void deleteToken(String token) {
		redisManager.del(token);

	}
	
	

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;

		/**
		 * 初始化redisManager
		 */
		this.redisManager.init();
	}
	
	public static byte[] long2Bytes(long num) {  
	    byte[] byteNum = new byte[8];  
	    for (int ix = 0; ix < 8; ++ix) {  
	        int offset = 64 - (ix + 1) * 8;  
	        byteNum[ix] = (byte) ((num >> offset) & 0xff);  
	    }  
	    return byteNum;  
	}  
	  
	public static long bytes2Long(byte[] byteNum) {  
	    long num = 0;  
	    for (int ix = 0; ix < 8; ++ix) {  
	        num <<= 8;  
	        num |= (byteNum[ix] & 0xff);  
	    }  
	    return num;  
	}

	@Override
	public TokenModel getTokenByUserId(String id) {

		if (id == null || id.length() == 0)
			return null;

		String token =redisManager.get(id);
		return new TokenModel(Integer.valueOf(id),Integer.valueOf(id), token);
	}

}
