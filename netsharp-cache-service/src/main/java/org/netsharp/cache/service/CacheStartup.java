package org.netsharp.cache.service;

import org.netsharp.cache.base.CacheConfigruation;
import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.cache.service.redis.RedisCacheCommand;
import org.netsharp.cache.service.redis.RedisConnection;
import org.netsharp.startup.IStartup;
import org.netsharp.util.PropertyConfigurer;

public class CacheStartup implements IStartup {
	
	public boolean startCondition() {
		
		return true;
	}

	public void startup() {
		
		//Redis
		PropertyConfigurer conf=PropertyConfigurer.newInstance("/conf/redis.properties");
		
		boolean disabled = Boolean.parseBoolean(conf.get("redis.disabled"));
		CacheConfigruation.setDisabled(CacheType.redis, disabled);

		if(!CacheConfigruation.getDisabled(CacheType.redis)){
			
			RedisConnection connection = new RedisConnection();
			
			connection.setMaxActive(Integer.parseInt(conf.get("redis.pool.maxActive")));
			connection.setMaxIdle(Integer.parseInt(conf.get("redis.pool.maxIdle")));
			connection.setMaxWait(Integer.parseInt(conf.get("redis.pool.maxWait")));
			connection.setIp(conf.get("redis.ip"));
			connection.setPort(Integer.parseInt(conf.get("redis.port")));
			connection.setPassword(conf.get("redis.password"));
			connection.setTimeout(Long.parseLong(conf.get("redis.timeout")));
			connection.setDatabase(Integer.parseInt(conf.get("redis.database")));
			connection.setDisabled(Boolean.parseBoolean(conf.get("redis.disabled")));
			connection.setFirstKey(conf.get("redis.cache.first.key"));

			RedisCacheCommand radis = new RedisCacheCommand();
			radis.open(connection);
		}

		//其他缓存
		
	}

	public void shutdown() {
		
	}

	public String getName() {
		return "缓存初始化";
	}

}
