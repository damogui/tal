package org.netsharp.cache.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.netsharp.cache.base.dict.KeyMode;

/*缓存配置*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheEvict {
	
	//缓存key  
	public String key() default "";
	//key的后缀模式
    public KeyMode keyMode() default KeyMode.DEFAULT;     
}
