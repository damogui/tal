package org.netsharp.cache.service.db;

import java.util.Set;

import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.JsonManage;

/*数据库缓存实现*/
public class DbCacheCommand implements ICacheCommand {
	
    private static ICacheItemService service = ServiceFactory.create(ICacheItemService.class);
    
    public void set(String key, Object value) {
    	
    	if(value == null){
    		return;
    	}
    	
    	String json = JsonManage.serialize2(value);
    	
    	CacheItem item = service.byId(key);
    	if(item==null){
    		item = new CacheItem();
    		{
    			item.setId(key);
    			item.setValue(json);
    			item.setType(value.getClass().getName());
    			
    			item.toNew();
    		}
    	}
    	else{
    		item.setValue(json);
			item.setType(value.getClass().getName());
			
			item.toPersist();
    	}

    	service.save(item);
    }
    
    public String get(String key) {
    	
    	CacheItem item = service.byId(key);
    	if(item==null){
    		return null;
    	}
    	
		return item.getValue();
    }

	public Object get(String key, Class<?> type) {
		
		CacheItem item = service.byId(key);
    	if(item==null){
    		return null;
    	}
    	
    	Object obj = JsonManage.deSerialize2(type, item.getValue());
    	
		return obj;
	}

	public void del(String key) {
    	
    	CacheItem item = new CacheItem();
    	{
    		item.toDeleted();
    		
    		item.setId(key);
    	}
    	
    	service.save(item);
    }
    
	public boolean exists(String key) {
    	CacheItem item = service.byId(key);
    	return item != null;
    }
	
	@Override
	public void set(String key, int seconds, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(byte[] key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(byte[] key, int seconds, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(byte[] key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(byte[] key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> keys(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String flushDB() {
		// TODO Auto-generated method stub
		return null;
	}
}
