package com.azcx9d.common.cache;

import java.util.Set;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {

	private String host = "127.0.0.1";

	private int port = 6379;

	// 0 - never expire
	private int expire = 0;

	// timeout for jedis try to connect to redis server, not expire time! In
	// milliseconds
	private int timeout = 0;

	private int database = 0;

	private String password = "";
	
	private static JedisPool jedisPool = null;

	public RedisManager() {

	}

	/**
	 * 初始化方法
	 */
	public void init() {
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
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
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
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String value = null;
		Jedis jedis = jedisPool.getResource();
		try {
			value = jedis.get(key);
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
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
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

			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);

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
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);

			if (jedis != null) {
				jedis.close();
			}
		}
		return code;
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
			
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
			
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
			
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
			
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
			
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
			
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
			
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
			
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
			
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
			
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
			
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
			
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
			
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
			
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
			
			if(jedisPool!=null)
				jedisPool.returnBrokenResource(jedis);
			
			
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

}
