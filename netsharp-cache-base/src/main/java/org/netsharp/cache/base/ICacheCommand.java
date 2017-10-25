package org.netsharp.cache.base;

import java.util.Set;

public interface ICacheCommand {
	
	String get(String key);
	
	Object get(byte[] key);
	
	Object get(String key, Class<?> type);
	
	void set(String key, Object value);
	
	void set(byte[] key, Object value);
	
	void set(String key, int seconds, Object value);
	
	void set(byte[] key, int seconds, Object value);
	
	void del(String key);
	
	void del(byte[] key);
	
	boolean exists(String key);
	
	boolean exists(byte[] key);
	
	public Set<String> keys(String pattern);
	
	public Set<byte[]> keys(byte[] pattern);
	
	public String flushDB();
}
