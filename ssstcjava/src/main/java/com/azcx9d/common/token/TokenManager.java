package com.azcx9d.common.token;

import com.azcx9d.agency.entity.TokenModel;

/**
 * 
 * @author fby
 *
 */
public interface TokenManager {
	/**
	 * 
	 * @param userId
	 * @return
	 */
    public TokenModel createToken(int userId);

    /**
     * 
     * @param token
     * @return
     */
    public boolean checkToken(TokenModel token);

    /**
     * 
     * @param authentication
     * @return
     */
    public TokenModel getToken(String authentication);

    /**
     *
     * @param userId
     * @return
     */
    public TokenModel getTokenByUserId(String userId);

    /**
     * 
     * @param token
     */
    public void deleteToken(String token);

}
