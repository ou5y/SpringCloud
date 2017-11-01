package com.customer.util;



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
    public TokenModel createToken(int id,int userId);

    /**
     * 
     * @param model
     * @return
     */
    public boolean checkToken(TokenModel model);

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
//    public TokenModel getTokenByUserId(String userId);

    /**
     * 
     * @param token
     */
    public void deleteToken(String token);

    /**
     *
     * @param userId
     * @return
     */
    public TokenModel getTokenByUserId(String userId);

}
