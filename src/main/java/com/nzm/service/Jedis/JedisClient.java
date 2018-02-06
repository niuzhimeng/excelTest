package com.nzm.service.Jedis;

/**
 * jedis操作类接口
 * Created by nzm on 2017/6/7.
 */
public interface JedisClient {
    String get(String key);

    String set(String key, String value);

    String hget(String hkey, String key);

    long hset(String hkey, String key, String value);

    long incr(String key);

    long expire(String key, int second);

    long ttl(String key);

    long del(String key);

    long hdel(String hkey, String key);
}
