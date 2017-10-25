package org.netsharp.cache.base;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.netsharp.cache.base.annotation.CacheEvict;
import org.netsharp.cache.base.annotation.CacheKey;
import org.netsharp.cache.base.annotation.Cacheable;
import org.netsharp.cache.base.dict.KeyMode;
import org.netsharp.util.PropertyConfigurer;
import org.netsharp.util.ReflectManager;

public class CacheKeyHandler {
	
	private static String firstKey = "visi";
	
	static{
		PropertyConfigurer conf=PropertyConfigurer.newInstance("/conf/redis.properties");
		firstKey = conf.get("redis.cache.first.key");
	}

	public static String createCacheKey(Cacheable cacheable, CacheEvict cacheEvict, String ctxName, Method method, Object[] Parameters) throws Exception{
		
		StringBuilder buf = new StringBuilder();
        //系统标识
        buf.append(firstKey);
        //方法注解的部分
        if(cacheable!=null && cacheEvict==null){
        	getSubKey(buf, ctxName, method, Parameters, cacheable.key(), cacheable.keyMode());
        }
        //方法注解的部分
        if(cacheable==null && cacheEvict!=null){
        	getSubKey(buf, ctxName, method, Parameters, cacheEvict.key(), cacheEvict.keyMode());
        }
        //完整的KEY
        return buf.toString();
	}
	
	public static StringBuilder getSubKey(StringBuilder buf, String ctxName, Method method, Object[] args, String key, KeyMode keyModel) throws Exception{
		
		if(key.length()>0){
    		buf.append(".").append(key);
    	}else{
    		buf.append(".").append(ctxName);
    	}
		
    	//参数注解部分
        StringBuilder hashBuf = new StringBuilder();
        
        if (keyModel == KeyMode.DEFAULT) {
            Annotation[][] pas = method.getParameterAnnotations();
            
            for (int i = 0; i < pas.length; i++) {
            	
                for (Annotation an : pas[i]) {
                	
                    if (an instanceof CacheKey) {
                    	
                    	String property = ((CacheKey) an).property();
                    	if(property.length()>0){
                    		
                    		Field field = ReflectManager.getField(args[i].getClass(), property);
                    		PropertyDescriptor pd = new PropertyDescriptor(field.getName(), args[i].getClass());
                    		Method rM = pd.getReadMethod();
                    		Object obj = rM.invoke(args[i]);
                    		hashBuf.append("{").append(obj.toString()).append("}");
                    	}else{
                    		
                    		hashBuf.append("{").append(args[i].toString()).append("}");
                    	}
                        break;
                    }
                }
            }
        }else if (keyModel == KeyMode.BASIC) {
        	
            for (Object arg : args) {
            	
                if (arg instanceof String) {
                	
                    hashBuf.append("{").append(arg).append("}");
                }else if (arg instanceof Short || arg instanceof Integer || arg instanceof Long 
                		|| arg instanceof Float || arg instanceof Double || arg instanceof Boolean) {
                    
                	hashBuf.append("{").append(arg.toString()).append("}");
                }
            }
        }
        
        //hash参数，不然Map参数参数影响key值的删除
        //buf.append(".").append(SHA1.stringToSHA(hashBuf.toString()));
        buf.append(".").append(hashBuf.toString());
        
        return buf;
	}
}
