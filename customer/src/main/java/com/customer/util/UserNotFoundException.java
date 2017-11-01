package com.customer.util;

/**
 * Created by fangbaoyan on 2017/4/29.
 */
public class UserNotFoundException extends  RuntimeException{

    private long id;

    public UserNotFoundException(long id)
    {
        this.id=id;
    }

    public long getId() {
        return id;
    }
}
