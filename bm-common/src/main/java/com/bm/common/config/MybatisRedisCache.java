package com.bm.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class MybatisRedisCache implements Cache {

    private String id;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static final long TTL = 120;

    public MybatisRedisCache(String id) {
        this.id = id;
    }

    private RedisTemplate<String, Object> getRedisTemplate() {
        return ApplicationContextHolder.getBean("redisTemplate");
    }

    /**
     * @return The identifier of this cache
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * @param key   Can be any object but usually it is a {@link }
     * @param value The result of a select.
     */
    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
        redisTemplate.boundHashOps(getId()).put(key, value);
        redisTemplate.boundHashOps(getId()).expire(TTL,TimeUnit.MINUTES);
        log.info("[结果放入到缓存中: " + key + "=" + value + " ]");
    }

    @Override
    public Object getObject(Object key) {
        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
        Object value = redisTemplate.boundHashOps(getId()).get(key);
        log.info("[从缓存中获取了: " + key + "=" + value + " ]");
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
        Object value = redisTemplate.boundHashOps(getId()).delete(key);
        log.info("[从缓存删除了: " + key + "=" + value + " ]");
        return value;
    }

    @Override
    public void clear() {
        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
        redisTemplate.delete(getId());
        log.info("清空" + getId() + "的缓存!!!");
    }

    @Override
    public int getSize() {
        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
        Long size = redisTemplate.boundHashOps(getId()).size();
        return size == null ? 0 : size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

}
