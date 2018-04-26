package com.gongsibao.redis.base;

import com.gongsibao.redis.dto.ConstantCache;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRedisAliyunService  {

    default boolean put(String key, String value) {
        return put(key, value, ConstantCache.TIME_ONE_DAY);
    }

    boolean put(String key, String value, int expireSecond);

    default <T> T get(String key, Class<T> clazz) {
        return (T) get(key);
    }

    Object get(String key);

    boolean delete(String key);


    //region 批量设置map
    /*
    批量设置map
     */
    boolean putHMap(String key, Map<String, String> map, int expireSecond);

    default boolean putHMap(String key, Map<String, String> map) {
        return putHMap(key, map, ConstantCache.TIME_ONE_DAY);
    }

    List<String> getHMap(String key, String mapKey);

    String getMap(String key, String mapkey);
    //endregion

    /*
    有序集合
     */
    boolean putZadd(String key, double weight, String member, int expireSecond);

    default boolean put(String key, double weight, String member){
        return putZadd(key,weight,member,ConstantCache.TIME_ONE_DAY);
    }

    //批量导入有序结合
    boolean putZadd(String key, Map<String, Double> member, int expireSecond);

    default boolean put(String key, Map<String, Double> member){
        return putZadd(key, member, ConstantCache.TIME_ONE_DAY);
    }

    //按条件获取
    Set<String> zrangeByScore(String key, double min, double max);

    //给指定key增量
    boolean Zincrby(double score, String key, String member);

    /*
    获取排序结果
    sort 0:正序 1:倒序
     */
    Set<Tuple> getSortResult(String key, Integer sort);

    /*
    List
     */
    boolean putList(String key, List<String> list, int expireSecond);

    default boolean putList(String key, List<String> list) {
        return putList(key,list, ConstantCache.TIME_ONE_DAY);
    }

    <T>List<T> getList(String key, Integer startIdx, Integer endIdx);

    //-1代表倒数一个元素，-2代表倒数第二个元素
    default <T> List<T> getList(String key, Class<T> clazz){
        return getList(key, 1, -1);
    }

    /*
    Set
     */
    boolean putSet(String key, Set<String> set, int expireSecond);

    default boolean putSet(String key, Set<String> set){
        return putSet(key, set, ConstantCache.TIME_ONE_DAY);
    }

    <T> Set<T> getSet(String key);

    default <T> Set<T> getSet(String key, Class<T> clazz) {
        return getSet(key);
    }





    boolean exists(String key);
}
