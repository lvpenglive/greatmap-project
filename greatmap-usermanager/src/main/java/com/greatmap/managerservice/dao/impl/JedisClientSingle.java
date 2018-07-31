package com.greatmap.managerservice.dao.impl;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;

import com.greatmap.managerservice.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient {

	@Autowired
	private JedisPool jedisPool;

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();

		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();

		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();

		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, long second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, (int) second);
		jedis.close();

		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();

		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();

		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;

	}

	@Override
	public byte[] bset(byte[] key, byte[] value) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.set(key, value);
			return value;

		} finally {
			jedis.close();
		}

	}

	@Override
	public void bexpire(byte[] key, int second) {
		Jedis jedis = jedisPool.getResource();

		try {
			jedis.expire(key, second);

		} finally {
			jedis.close();
		}
	}

	@Override
	public byte[] bget(byte[] key) {
		Jedis jedis = jedisPool.getResource();

		try {
			byte[] value = jedis.get(key);
			return value;

		} finally {
			jedis.close();
		}
	}

	@Override
	public void bdel(byte[] key) {
		// TODO Auto-generated method stub		
		Jedis jedis = jedisPool.getResource();

		try {
			jedis.del(key);

		} finally {
			jedis.close();
		}
	}

	@Override
	public Set<byte[]> bkeys(String key) {
		Jedis jedis = jedisPool.getResource();

		try {
			return jedis.keys((key + "*").getBytes()) ;

		} finally {
			jedis.close();
		}
	}

}
