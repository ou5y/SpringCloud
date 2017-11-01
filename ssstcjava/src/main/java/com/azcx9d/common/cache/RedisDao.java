package com.azcx9d.common.cache;

import redis.clients.jedis.JedisPool;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public class RedisDao {
    private final JedisPool jedisPool;

    public RedisDao(String ip,int port) {
        this.jedisPool = new JedisPool("localhost",6379);
    }

    
}
