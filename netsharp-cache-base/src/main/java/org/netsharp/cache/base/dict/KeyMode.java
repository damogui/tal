package org.netsharp.cache.base.dict;

public enum KeyMode {

	DEFAULT, // 只有加了@CacheKey的参数,才加入key后缀中
    BASIC; // 只有基本类型参数,才加入key后缀中,如:String,Short,Integer,Long,Float,Double,Boolean
}
