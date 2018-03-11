package com.gongsibao.sms.base.cache;

import com.gongsibao.sms.utils.constant.ConstantCache;

public interface ICacheService {
    /**
     * 默认有效时间为24小时
     *
     * @param key
     * @param value
     * @return
     */
    default boolean put(String key, Object value) {
        return put(key, value, ConstantCache.TIME_ONE_DAY);
    }

    boolean put(String key, Object value, int expireSecond);

    @SuppressWarnings("unchecked")
	default <T> T get(String key, Class<T> clazz) {
        return (T) get(key);
    }

    Object get(String key);

    Object delete(String key);

    @SuppressWarnings("unchecked")
	default <T> T delete(String key, Class<T> clazz) {
        return (T) delete(key);
    }

    /**
     * 获取key使用次数
     *
     * @param key
     * @return
     */
    int getUseTimes(String key);


    void incrUseTimes(String key, int timeLong);

    /**
     * 自增key使用次数
     *
     * @param key
     */
    default void incrUseTimes(String key){
        incrUseTimes(key, ConstantCache.TIME_ONE_DAY);
    }

    /**
     * 清空key使用次数
     *
     * @param key
     */
    void removeUseTimes(String key);


}
