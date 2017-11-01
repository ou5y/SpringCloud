package com.azcx9d.system.redis;

import com.azcx9d.agency.entity.TokenModel;

/**
 * Created by HuangQing on 2017/4/10 0010 13:53.
 */
public interface SysUserTokenManager {

    /**
     *
     * @param userId
     * @return
     */
    public TokenModel createToken(int userId);

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
    public TokenModel getTokenModel(String authentication);

    /**
     *
     * @param userId
     */
    public void deleteToken(String userId);
}
