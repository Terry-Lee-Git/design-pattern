package org.itstack.demo.design.cuisine.impl;

import org.itstack.demo.design.CacheService;
import org.itstack.demo.design.RedisUtils;

import java.util.concurrent.TimeUnit;

/**
 * 四个功能的实现类
 */
public class CacheServiceImpl implements CacheService {
    /**
     * 建立工具类的对象
     */
    private RedisUtils redisUtils = new RedisUtils();

    /**
     * 使用工具类的get方法实现获取功能
     * @param key
     * @return
     */
    public String get(String key) {
        return redisUtils.get(key);
    }

    /**
     * 使用工具类的set方法实现存入功能
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisUtils.set(key, value);
    }

    /**
     * 使用工具类的set方法实现定时失效key，value的存入
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        redisUtils.set(key, value, timeout, timeUnit);
    }

    /**
     * 使用工具类的del方法实现通过key对值进行删除的功能
     * @param key
     */
    public void del(String key) {
        redisUtils.del(key);
    }

}
