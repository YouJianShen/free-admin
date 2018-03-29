package com.suit.shiro;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
  
public class RedisCacheManager implements CacheManager{  
  
    private static final Logger logger = LoggerFactory  
            .getLogger(RedisCacheManager.class);  
  
    // fast lookup by name map  
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();  
  
    StringRedisTemplate stringRedisTemplate;
  
    /** 
     * The Redis key prefix for caches  
     */  
    private String keyPrefix = "shiro_redis_cache:";

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {  
        logger.debug("获取名称为: " + name + " 的RedisCache实例");  
          
        Cache c = caches.get(name);  
          
        if (c == null) {  
  
//            // initialize the Redis manager instance  
//            redisManager.init();  
              
            // create a new cache instance  
            c = new RedisCache<K, V>(stringRedisTemplate, keyPrefix);  
              
            // add it to the cache collection  
            caches.put(name, c);  
        }  
        return c;  
    }

	/** 
     * Returns the Redis session keys 
     * prefix. 
     * @return The prefix 
     */  
    public String getKeyPrefix() {  
        return keyPrefix;  
    }  
      
    public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}  
  
    /** 
     * Sets the Redis sessions key  
     * prefix. 
     * @param keyPrefix The prefix 
     */  
    public void setKeyPrefix(String keyPrefix) {  
        this.keyPrefix = keyPrefix;  
    }  
      
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}  
}  