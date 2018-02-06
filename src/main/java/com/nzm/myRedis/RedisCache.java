package com.nzm.myRedis;

import org.apache.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by Nzm on 2018/2/6.
 */
public class RedisCache implements Cache {
    private final Logger LOGGER = Logger.getLogger(RedisCache.class);

    private RedisTemplate<String, Object> redisTemplate;
    private String name;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    private static final String KEY_PREFIX_VALUE = "tx:dataApi:";

    @Override
    public ValueWrapper get(Object key) {
        final String k = KEY_PREFIX_VALUE + key.toString();
        LOGGER.info("==============获取 key：" + k);
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        Object object;
        if (k.contains("*")) {
            Set<String> keys = redisTemplate.keys(k);
            object = valueOps.multiGet(keys);
        } else {
            object = valueOps.get(k);
        }
        if (object != null) {
            return new SimpleValueWrapper(object);
        } else {
            return null;
        }
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        final String k = KEY_PREFIX_VALUE + key.toString();
        if (value != null) {
            LOGGER.info("============================缓存key：" + k);
            try {
                ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
                valueOps.set(k, value);
            } catch (Throwable t) {
                LOGGER.error("缓存[" + k + "]失败");
            }
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    @Override
    public void evict(Object key) {
        final String k = KEY_PREFIX_VALUE + key.toString();
        LOGGER.info("============================删除key:" + k);
        try {
            redisTemplate.delete(redisTemplate.keys(k));
        } catch (Throwable t) {
            LOGGER.error("删除缓存失败key[" + k + ", error[" + t + "]");
        }
    }

    @Override
    public void clear() {
    }
}
