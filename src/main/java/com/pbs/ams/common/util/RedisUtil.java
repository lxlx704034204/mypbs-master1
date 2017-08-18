package com.pbs.ams.common.util;

import com.pbs.ams.common.constant.RedisEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis 工具类
 * Created by ams on 2016/11/26.
 */
public class RedisUtil {

	protected static ReentrantLock lockPool = new ReentrantLock();
	protected static ReentrantLock lockJedis = new ReentrantLock();

	private static Logger _log = LoggerFactory.getLogger(RedisUtil.class);

	private static JedisPool jedisPool = null;

	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(RedisEnum.MAX_ACTIVE.getInt());
			config.setMaxIdle(RedisEnum.MAX_IDLE.getInt());
			config.setMaxWaitMillis(RedisEnum.MAX_WAIT.getInt());
			config.setTestOnBorrow(RedisEnum.TEST_ON_BORROW.getBoolean());
			jedisPool = new JedisPool(config,RedisEnum.IP.getString(),RedisEnum.PORT.getInt(),RedisEnum.TIMEOUT.getInt());
		} catch (Exception e) {
			_log.error("First create JedisPool error : " + e);
		}
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (null == jedisPool) {
			initialPool();
		}
	}


	/**
	 * 同步获取Jedis实例
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		poolInit();
		Jedis jedis = null;
		try {
			if (null != jedisPool) {
				jedis = jedisPool.getResource();
				try {
					System.out.println("-0--before of jedis.auth(PASSWORD): "+jedis.ping());//
//					jedis.auth(RedisEnum.PASSWORD.getString());
					System.out.println("-0.1--redis connect sucessfully-Server is running: "+jedis.ping());//去ping我们redis的主机所在ip和端口
				} catch (Exception e) {
					System.out.println("-0.2--jedis--e.toString(): "+e.toString());//
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("-0.3--redis connect failure-1 !");
			_log.error("Get jedis error : " + e);
		}
		return jedis;
	}

	/**
	 * 设置 String
	 * @param key
	 * @param value
	 */
	public synchronized static void set(String key, String value) {
		try {
			value = StringUtils.isBlank(value) ? "" : value;
			Jedis jedis = getJedis();
			jedis.set(key, value);
			jedis.close();
		} catch (Exception e) {
			_log.error("Set key error : " + e);
		}
	}

	/**
	 * 设置 byte[]
	 * @param key
	 * @param value
	 */
	public synchronized static void set(byte[] key, byte[] value) {
		try {
			Jedis jedis = getJedis();
			jedis.set(key, value);
			jedis.close();
		} catch (Exception e) {
			_log.error("Set key error : " + e);
		}
	}

	/**
	 * 设置 String 过期时间
	 * @param key
	 * @param value
	 * @param seconds 以秒为单位
	 */
	public synchronized static void set(String key, String value, int seconds) {
		try {
			value = StringUtils.isBlank(value) ? "" : value;
			Jedis jedis = getJedis();
			jedis.setex(key, seconds, value);
			jedis.close();
		} catch (Exception e) {
			_log.error("Set keyex error : " + e);
		}
	}

	/**
	 * 设置 byte[] 过期时间
	 * @param key
	 * @param value
	 * @param seconds 以秒为单位
	 */
	public synchronized static void set(byte[] key, byte[] value, int seconds) {
		try {
			Jedis jedis = getJedis();
			jedis.set(key, value);
			jedis.expire(key, seconds);
			jedis.close();
		} catch (Exception e) {
			_log.error("Set key error : " + e);
		}
	}

	/**
	 * 获取String值
	 * @param key
	 * @return value
	 */
	public synchronized static String get(String key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	/**
	 * 获取byte[]值
	 * @param key
	 * @return value
	 */
	public synchronized static byte[] get(byte[] key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		byte[] value = jedis.get(key);
		jedis.close();
		return value;
	}

	/**
	 * 删除值
	 * @param key
	 */
	public synchronized static void remove(String key) {
		try {
			Jedis jedis = getJedis();
			jedis.del(key);
			jedis.close();
		} catch (Exception e) {
			_log.error("Remove keyex error : " + e);
		}
	}

	/**
	 * 删除值
	 * @param key
	 */
	public synchronized static void remove(byte[] key) {
		try {
			Jedis jedis = getJedis();
			jedis.del(key);
			jedis.close();
		} catch (Exception e) {
			_log.error("Remove keyex error : " + e);
		}
	}

	/**
	 * lpush
	 * @param key
	 * @param key
	 */
	public synchronized static void lpush(String key, String... strings) {
		try {
			Jedis jedis = RedisUtil.getJedis();
			jedis.lpush(key, strings);
			jedis.close();
		} catch (Exception e) {
			_log.error("lpush error : " + e);
		}
	}

	/**
	 * lrem
	 * @param key
	 * @param count
	 * @param value
	 */
	public synchronized static void lrem(String key, long count, String value) {
		try {
			Jedis jedis = RedisUtil.getJedis();
			jedis.lrem(key, count, value);
			jedis.close();
		} catch (Exception e) {
			_log.error("lpush error : " + e);
		}
	}

	/**
	 * sadd
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public synchronized static void sadd(String key, String value, int seconds) {
		try {
			Jedis jedis = RedisUtil.getJedis();
			jedis.sadd(key, value);
			jedis.expire(key, seconds);
			jedis.close();
		} catch (Exception e) {
			_log.error("sadd error : " + e);
		}
	}

}
