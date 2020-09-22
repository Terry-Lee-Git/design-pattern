package org.itstack.demo.design;

import java.util.concurrent.TimeUnit;

/**
 * 待实现的抽象方法
 */
public interface CacheService {

    String get(final String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void del(String key);

}
