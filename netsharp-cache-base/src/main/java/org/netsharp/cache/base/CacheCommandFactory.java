package org.netsharp.cache.base;

import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.util.ReflectManager;

public class CacheCommandFactory {
	
	private static ICacheCommand radisCommand = null;
	private static ICacheCommand dbCommand = null;
	
	public static ICacheCommand create(CacheType cacheType){
		
		if(cacheType == CacheType.db) {
			
			if(dbCommand == null) {
				String db="org.netsharp.cache.service.db.DbCacheCommand";
				dbCommand = (ICacheCommand)ReflectManager.newInstance(db);
			}
			
			return dbCommand; 
		}
		else if(cacheType == CacheType.redis) {
			
			if(radisCommand == null){
				String db="org.netsharp.cache.service.redis.RedisCacheCommand";
				radisCommand = (ICacheCommand)ReflectManager.newInstance(db);
			}
			
			return radisCommand;
		}
		else {
			throw new CacheException("not implements!");
		}
	}

}
