package com.greatmap.managerservice.cache;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.greatmap.managerservice.dao.JedisClient;

/**
 * 
 * @author lvpen
 * 
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

	@Resource
	private JedisClient jedisClient;
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${USER_CACHE_EXPIRE}")
	private Integer USER_CACHE_EXPIRE;
	private byte[] getKey(K k) {
		if (k instanceof String) {
			return (REDIS_USER_SESSION_KEY + k).getBytes();
		}
		return SerializationUtils.serialize(k);
	}

	@Override
	public V get(K arg0) throws CacheException {
		// TODO Auto-generated method stub

		System.out.println("从redis获取权限数据");
		byte[] value = jedisClient.bget(getKey(arg0));
		if (value != null) {
			return (V) SerializationUtils.deserialize(value);
		}

		return null;
	}

	@Override
	public V put(K arg0, V arg1) throws CacheException {
		// TODO Auto-generated method stub
		byte[] key = getKey(arg0);
		
		byte[] value = SerializationUtils.serialize(arg1);
		
		jedisClient.bset(key, value);
		jedisClient.bexpire(key, USER_CACHE_EXPIRE);
		
		return arg1;
	}

	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K arg0) throws CacheException {
		// TODO Auto-generated method stub
		byte[] key = getKey(arg0);
		byte[] value = jedisClient.bget(key);
		jedisClient.bdel(key);
		if (value != null) {
			return (V) SerializationUtils.deserialize(value);
			
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
