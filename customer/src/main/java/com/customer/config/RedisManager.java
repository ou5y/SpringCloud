package com.customer.config;

import com.customer.dto.RoleListDto;
import com.customer.util.ProtoStuffSerializerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Configuration
@ConfigurationProperties(prefix="spring.redis", ignoreNestedProperties = false)
public class RedisManager {

	private Logger logger = LoggerFactory.getLogger(RedisManager.class);

	public final static String VIRTUAL_ROLE_PREX = "_user_level_";

	private String host = "127.0.0.1";

	private int port = 6379;

	// 0 - never expire
	private int expire = 0;

	// timeout for jedis try to connect to redis server, not expire time! In
	// milliseconds
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
	public synchronized Jedis getJedis() {
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

	/**
	 * 得到Key
	 * @param key
	 * @return
	 */
	public String buildKey(String key){
		return VIRTUAL_ROLE_PREX + key;
	}
	/**
	 * get value from redis
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) {
		byte[] value = null;
		Jedis jedis = jedisPool.getResource();
		try {
			value = jedis.get(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(byte[] key, byte[] value) {
		Jedis jedis = jedisPool.getResource();
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
	
	/**
	 * get value from redis
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String value = null;
		Jedis jedis = jedisPool.getResource();
		try {
			value = jedis.get(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}
	
	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key,String value)
	{
		Jedis jedis = jedisPool.getResource();
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


	/**
	 * get value from redis
	 *
	 * @param phone 手机号，通过手机号查询验证码
	 * @return
	 */
	public String getPhontCode(String phone) {
		String value = null;
		Jedis jedis = jedisPool.getResource();
		try {
			value = jedis.get(phone);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	/**
	 * set
	 *
	 * @param phone 手机号
	 * @param code 验证码
	 * @return
	 */
	public String setPhoneAndCode(String phone,String code)
	{
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.set(phone, code);

			if (this.expire != 0) {
				jedis.expire(phone, 60*30);
			}
		} finally {

			if (jedis != null) {
				jedis.close();
			}
		}
		return code;
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] set(byte[] key, byte[] value, int expire) {
		Jedis jedis = jedisPool.getResource();
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
	
	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public String set(String key, String value, int expire) {
		Jedis jedis = jedisPool.getResource();
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


	/**
	 * 设置 list
	 * @param <T>
	 * @param key

	 */
	public <T> void setList(String key ,List<T> list){
		String bKey = buildKey(key);
		try {
			getJedis().set(bKey.getBytes(), ProtoStuffSerializerUtil.serializeList(list));
		} catch (Exception e) {
			logger.error("Set key error : "+e);
		}
	}
	/**
	 * 获取list
	 * @param <T>
	 * @param key
	 * @return list
	 */
	public <T> List<T> getList(String key){
		String bKey = buildKey(key);
		if(getJedis() == null || !getJedis().exists(key.getBytes())){
			return null;
		}
		byte[] in = getJedis().get(bKey.getBytes());
		List<T> list = new ArrayList<T>();
		if (in != null) {
			 list= (List<T>) ProtoStuffSerializerUtil.deserializeList(in, RoleListDto.class);

		}
		return list;

	}
	/**
	 * 设置 map
	 * @param <T>
	 * @param key

	 */
	public <T> void setMap(String key ,Map<String,T> map){
		try {
			getJedis().set(key.getBytes(),ProtoStuffSerializerUtil.serialize(map));
		} catch (Exception e) {
			logger.error("Set key error : "+e);
		}
	}
	/**
	 * 获取map
	 * @param <T>
	 * @param key
	 * @return list
	 */
	public <T> Map<String,T> getMap(String key){
		String bKey = buildKey(key);
		if(getJedis() == null || !getJedis().exists(key.getBytes())){
			return null;
		}
		byte[] in = getJedis().get(key.getBytes());
		Map<String,T> map = (Map<String, T>) ProtoStuffSerializerUtil.deserialize(in,RoleListDto.class);
		return map;
	}

	/**
	 * expire
	 * 
	 * @param key
	 * @param expire
	 * @return
	 */
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
	
	/**
	 * expire
	 * 
	 * @param key
	 * @param expire
	 * @return
	 */
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

	/**
	 * del
	 * 
	 * @param key
	 */
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
	
	/**
	 * del
	 * 
	 * @param key
	 */
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

	/**
	 * flush
	 */
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

	/**
	 * size
	 */
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

	/**
	 * keys
	 * 
	 * @param pattern
	 * @return
	 */
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
