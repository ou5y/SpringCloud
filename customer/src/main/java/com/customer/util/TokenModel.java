package com.customer.util;
/**
 * 
 * @author fangbaoyan
 *
 */
public class TokenModel {
	
    private int id;

    
    private String token;

    private int userId;

    public TokenModel(int id,int userId, String token) {
        this.id=id;
		this.userId = userId;
		this.token = token;
	}

	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
