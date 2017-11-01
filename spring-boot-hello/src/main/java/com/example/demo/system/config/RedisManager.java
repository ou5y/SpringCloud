package com.example.demo.system.config;

import com.example.demo.util.ProtoStuffSerializerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

@Configuration
@ConfigurationProperties(prefix="spring.redis")
public class RedisManager {

	private Logger logger = LoggerFactory.getLogger(RedisManager.class);

	private String host;
	private int port;
	private int expire = 0;// 0 - never expire
	// timeout for jedis try to connect to redis server, not expire time! In（milliseconds）
	private int timeout = 0;
	private int database = 0;
	private String password = "";
	private int maxIdle;
	private long maxWaitMillis;
	private int maxTotal;

	private static JedisPool jedisPool = null;

	public RedisManager() {

	}

	/**
	 * 初始化方法
	 */
	@Bean
	public JedisPool init() {
		if (jedisPool == null) {
			if (password != null && !"".equals(password)) {
				JedisPoolConfig config = new JedisPoolConfig();
				jedisPool = new JedisPool(config, host, port, timeout, password);
			} else if (timeout != 0) {
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout);
			} else {
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
			}

		}
		return jedisPool;
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private synchronized void poolInit() {
		if (jedisPool == null) {
			init();
		}
	}

	/**
	 * 同步获取Jedis实例
	 * @return Jedis
	 */
	private synchronized Jedis getJedis() {
		if (jedisPool == null) {
			poolInit();
		}
		Jedis jedis = null;
		try {
			if (jedisPool != null) {
				jedis = jedisPool.getResource();
			}
		} catch (Exception e) {
			logger.error("Get jedis error : "+e);
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
		return jedis;
	}

	/**
	 * 释放jedis资源
	 * @param jedis
	 */
	public void returnResource(final Jedis jedis) {
		if (jedis != null && jedisPool !=null) {
			jedisPool.returnResource(jedis);
		}
	}

	public byte[] get(byte[] key) {
		byte[] value = null;
		Jedis jedis = getJedis();
		try {
			value = jedis.get(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	public byte[] set(byte[] key, byte[] value) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
			if (this.expire != 0) {
				jedis.expire(key, this.expire);
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	public String get(String key) {
		String value = null;
		Jedis jedis = getJedis();
		try {
			value = jedis.get(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	public String set(String key,String value){
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
			if (this.expire != 0) {
				jedis.expire(key, this.expire);
			}
		} finally {
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	public byte[] set(byte[] key, byte[] value, int expire) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	public String set(String key, String value, int expire) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	public <T> void setList(String key ,List<T> list){
		try {
			getJedis().set(key.getBytes(), ProtoStuffSerializerUtil.serializeList(list));
		} catch (Exception e) {
			logger.error("Set key error : "+e);
		}
	}

	public <T> List<T> getList(String key){
		if(getJedis() == null || !getJedis().exists(key.getBytes())){
			return null;
		}
		byte[] in = getJedis().get(key.getBytes());
		List<T> list = new ArrayList<T>();
		if (in != null) {
			list= (List<T>) ProtoStuffSerializerUtil.deserializeList(in, HashMap.class);
		}
		return list;

	}

	public <T> void setMap(String key ,Map<String,T> map){
		try {
			getJedis().set(key.getBytes(), ProtoStuffSerializerUtil.serialize(map));
		} catch (Exception e) {
			logger.error("Set key error : "+e);
		}
	}

	public <T> Map<String,T> getMap(String key){
		if(getJedis() == null || !getJedis().exists(key.getBytes())){
			return null;
		}
		byte[] in = getJedis().get(key.getBytes());
		Map<String,T> map = (Map<String, T>) ProtoStuffSerializerUtil.deserialize(in, HashMap.class);
		return map;
	}

	public void expire(byte[] key, int expire) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.expire(key, expire);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public void expire(String key, int expire) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.expire(key, expire);
		} finally {

			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public void del(byte[] key) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.del(key);
		} finally {

			if (jedis != null) {
				jedis.close();
			}
		}
	}


	public void del(String key) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.del(key);
		} finally {
//			if(jedis
			if (jedis != null) {
				jedis.close();
			}
		}
	}


	public void flushDB() {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.flushDB();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}


	public Long dbSize() {
		Long dbSize = 0L;
		Jedis jedis = jedisPool.getResource();
		try {
			dbSize = jedis.dbSize();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return dbSize;
	}

	public Long incr(String key) {
		long sequence = 0L;
		Jedis jedis = jedisPool.getResource();
		try {
			if (Long.parseLong(jedis.get(key)) > 8999)
				jedis.set(key, "0");
			sequence = jedis.incr(key);
		} finally {

			if (jedis != null) {
				jedis.close();
			}
		}
		return sequence;
	}


	public Set<byte[]> keys(String pattern) {
		Set<byte[]> keys = null;
		Jedis jedis = jedisPool.getResource();
		try {
			keys = jedis.keys(pattern.getBytes());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return keys;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
}
