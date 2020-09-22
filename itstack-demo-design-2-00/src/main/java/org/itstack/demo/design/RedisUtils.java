package org.itstack.demo.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RedisUtils {
    /**
     * 开启日志
     */
    private Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    /**
     * 数据Map
     */
    private Map<String, String> dataMap = new ConcurrentHashMap<String, String>();

    /**
     *
     * @param key redis通过key获取值
     * @return
     */
    public String get(String key) {
        logger.info("Redis获取数据 key：{}", key);
        return dataMap.get(key);
    }

    /**
     * redis存入key，value
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        logger.info("Redis写入数据 key：{} val：{}", key, value);
        dataMap.put(key, value);
    }

    /**
     * 存入一个有过去时间的key，value
     * @param key 键
     * @param value 值
     * @param timeout 超时时间
     * @param timeUnit 时间单位
     */
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        logger.info("Redis写入数据 key：{} val：{} timeout：{} timeUnit：{}", key, value, timeout, timeUnit.toString());
        dataMap.put(key, value);
    }

    /**
     * 通过key对数据进行删除
     * @param key
     */
    public void del(String key) {
        logger.info("Redis删除数据 key：{}", key);
        dataMap.remove(key);
    }

}
