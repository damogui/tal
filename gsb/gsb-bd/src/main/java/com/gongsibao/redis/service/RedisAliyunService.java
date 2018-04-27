package com.gongsibao.redis.service;

import com.gongsibao.redis.base.IRedisAliyunService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;

@Service
public class RedisAliyunService implements IRedisAliyunService {

    private Jedis redis ;//连接redis

    @Override
    public boolean put(String key, String value, int expireSecond) {
        getRedis().set(key,value);
        getRedis().expire(key, expireSecond);
        return true;
    }

    @Override
    public Object get(String key) {
        return getRedis().get(key);
    }

    @Override
    public boolean delete(String key) {
        getRedis().del(key);
        return true;
    }

    public boolean putHMap(String key,Map<String,String> map,int expireSecond) {
        getRedis().hmset(key, map);
        getRedis().expire(key, expireSecond);

        return true;
    }

    @Override
    public List<String> getHMap(String key, String mapKey) {
        return getRedis().hmget(key,mapKey);
    }

    @Override
    public String getMap(String key, String mapkey) {
        return getRedis().hget(key, mapkey);
    }

    @Override
    public boolean putZadd(String key, double weight, String member, int expireSecond) {
        getRedis().zadd(key, weight, member);
        getRedis().expire(key, expireSecond);
        return true;
    }

    @Override
    public boolean putZadd(String key, Map<String, Double> member, int expireSecond) {
        getRedis().zadd(key, member);
        getRedis().expire(key, expireSecond);
        return true;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return getRedis().zrangeByScore(key, min, max);
    }

    @Override
    public boolean Zincrby(double score ,String key,String member) {
        getRedis().zincrby(key, score, member);
        return true;
    }

    //TODO 把时间转换成double 然后取区间值

    @Override
    public Set<Tuple> getSortResult(String key,Integer sort) {
        if(sort.equals(0)) {
            return getRedis().zrangeWithScores("fruit", 0, -1);
        }else{
            return getRedis().zrevrangeWithScores("fruit", 0, -1);
        }

    }

    @Override
    public boolean exists(String key) {
        return getRedis().exists(key);
    }

    @Override
    public boolean putList(String key, List<String> list, int expireSecond) {
        if(CollectionUtils.isEmpty(list)) return false;
        String[] strs = list.toArray(new String[list.size()]);

        getRedis().lpush(key, strs);
        getRedis().expire(key,expireSecond);
        return true;
    }

    @Override
    public <T> List<T>  getList(String key, Integer startIdx, Integer endIdx) {
        List<T> Tlist = new ArrayList<>();
        List<String> res = getRedis().lrange(key, startIdx, endIdx);
        if(CollectionUtils.isNotEmpty(res)) {
            for (String str : res) {
                Tlist.add((T) str);
            }
        }

        return Tlist;
    }

    @Override
    public boolean putSet(String key, Set<String> set, int expireSecond) {
        if(CollectionUtils.isEmpty(set)) return false;

        String[] strs = set.toArray(new String[set.size()]);
        getRedis().sadd(key, strs);
        getRedis().expire(key, expireSecond);

        return true;
    }

    @Override
    public <T> Set<T> getSet(String key) {
        Set<T> Tset = new HashSet<>();
        Set<String> res = getRedis().sdiff(key);

        if (CollectionUtils.isNotEmpty(res)) {
            for (String str : res) {
                Tset.add((T) str);
            }
        }
        return Tset;
    }

    private Jedis getRedis() {
        if (null == redis) {
            redis = new Jedis("59.110.125.76", 8967);
            redis.auth("Gongsibao2018");
        }
        return redis;
    }
}
