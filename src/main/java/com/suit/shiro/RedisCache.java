package com.suit.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisCache<K, V> implements Cache<K, V> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * The wrapped Jedis instance.
	 */
	StringRedisTemplate cache;

	/**
	 * The Redis key prefix for the sessions
	 */
	private String keyPrefix = "shiro_redis_session:";

	/**
	 * Returns the Redis session keys prefix.
	 * 
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix
	 *            The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	/**
	 * 通过一个JedisManager实例构造RedisCache
	 */
	public RedisCache(StringRedisTemplate cache) {
		if (cache == null) {
			throw new IllegalArgumentException("Cache argument cannot be null.");
		}
		this.cache = cache;
	}

	/**
	 * Constructs a cache instance with the specified Redis manager and using a
	 * custom key prefix.
	 * 
	 * @param cache
	 *            The cache manager instance
	 * @param prefix
	 *            The Redis key prefix
	 */
	public RedisCache(StringRedisTemplate cache, String prefix) {

		this(cache);

		// set the prefix
		this.keyPrefix = prefix;
	}

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private String getByteKey(K key) {
		String preKey = this.keyPrefix + key;
		return preKey;
	}




	public V get(K key) throws CacheException {
		logger.debug("根据key从Redis中获取对象 key [" + key + "]");
		try {
			if (key == null) {
				return null;
			} else {
				ValueOperations<String,String> valueOperations = cache.opsForValue();
				String rawValue = valueOperations.get(getByteKey(key));
				return (V)rawValue;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	public V put(K key, V value) throws CacheException {
		logger.debug("根据key从存储 key [" + key + "]");
		try {
			ValueOperations<String,String> valueOperations = cache.opsForValue();
			valueOperations.set(getByteKey(key), SerializableUtils.serialize(value));
			return value;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	public V remove(K key) throws CacheException {
		logger.debug("从redis中删除 key [" + key + "]");
		try {
			V previous = get(key);
			cache.delete(getByteKey(key));
			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	public void clear() throws CacheException {
		logger.debug("从redis中删除所有元素");
		try {
			cache.discard();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	public int size() {
		try {
			Long longSize = new Long(cache.keys(this.keyPrefix + "*").size());
			return longSize.intValue();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@SuppressWarnings("unchecked")
	public Set<K> keys() {
		try {
			Set<String> keys = cache.keys(this.keyPrefix + "*");
			if (CollectionUtils.isEmpty(keys)) {
				return Collections.emptySet();
			} else {
				Set<K> newKeys = new HashSet<K>();
				for (String key : keys) {
					newKeys.add((K) key);
				}
				return newKeys;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	public Collection<V> values() {
		try {
			Set<String> keys = cache.keys(this.keyPrefix + "*");
			if (!CollectionUtils.isEmpty(keys)) {
				List<V> values = new ArrayList<V>(keys.size());
				for (String key : keys) {
					@SuppressWarnings("unchecked")
					V value = get((K) key);
					if (value != null) {
						values.add(value);
					}
				}
				return Collections.unmodifiableList(values);
			} else {
				return Collections.emptyList();
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}
}