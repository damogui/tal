package org.netsharp.cache.service.redis;

import java.util.Set;

import org.netsharp.cache.base.CacheException;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.util.SerizlizeUtil;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

import redis.clients.jedis.Jedis;

/*Raids数据库访问路径*/
public class RedisCacheCommand implements ICacheCommand {

	private static Jedis jedis = null;

	public void open(RedisConnection con) {

		try{
			if (jedis == null) {
	
				jedis = new Jedis(con.getIp(), con.getPort());
				{
					jedis.auth(con.getPassword());
					jedis.select(con.getDatabase());
				}
			}
		}catch(Exception ce){
			throw new CacheException("打开Redis连接异常！");
		}
	}

	public void close() {

		try{
			jedis.disconnect();
		}catch(Exception ce){
			throw new CacheException("断开Redis连接异常！");
		}
	}

	public String get(String key) {

		String value = jedis.get(key);
		return value;
	}

	public Object get(byte[] key) {

		byte[] byteArray = jedis.get(key);
		if (byteArray == null || byteArray.length <= 0) {
			return null;
		} else {
			Object value = SerizlizeUtil.unserizlize(byteArray);
			return value;
		}
	}

	public Object get(String key, Class<?> type) {

		String json = jedis.get(key);
		if (StringManager.isNullOrEmpty(json)) {
			return null;
		} else {
			Object value = JsonManage.deSerialize2(type, json);
			return value;
		}
	}

	public void set(String key, Object value) {

		if (value == null) {
			return;
		}

		if (value instanceof String) {
			jedis.set(key, (String) value);
		} else {
			String json = JsonManage.serialize2(value);
			jedis.set(key, json);
		}
	}

	public void set(byte[] key, Object value) {

		if (value == null) {
			return;
		}

		byte[] byteArray = SerizlizeUtil.serialize(value);
		jedis.set(key, byteArray);
	}

	public void set(String key, int seconds, Object value) {

		if (value == null) {
			return;
		}

		if (value instanceof String) {
			jedis.set(key, (String) value);
		} else {
			String json = JsonManage.serialize2(value);
			jedis.set(key, json);
		}
		jedis.expire(key, seconds);
	}

	public void set(byte[] key, int seconds, Object value) {

		if (value == null) {
			return;
		}

		byte[] byteArray = SerizlizeUtil.serialize(value);
		jedis.set(key, byteArray);
		jedis.expire(key, seconds);
	}

	public void del(String keys) {
		
		jedis.del(keys);
	}

	public void del(byte[] keys) {
		
		jedis.del(keys);
	}

	public boolean exists(String key) {
		
		return jedis.exists(key);
	}

	public boolean exists(byte[] key) {
		
		return jedis.exists(key);
	}

	public Set<String> keys(String pattern) {
		
		return jedis.keys(pattern);
	}

	public Set<byte[]> keys(byte[] pattern) {
		
		return jedis.keys(pattern);
	}

	public String flushDB() {
		
		return jedis.flushDB();
	}
}
