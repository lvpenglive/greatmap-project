package com.greatmap.managerservice.dao.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.greatmap.managerservice.dao.JedisClient;

public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisClientCluster jedisCluster;

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.get(key);
	}

	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub
		return jedisCluster.set(key, value);
	}

	@Override
	public String hget(String hkey, String key) {
		// TODO Auto-generated method stub
		return jedisCluster.hget(hkey, key);
	}

	@Override
	public long hset(String hkey, String key, String value) {
		// TODO Auto-generated method stub
		return jedisCluster.hset(hkey, key, value);
	}

	@Override
	public long incr(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.incr(key);
	}

	@Override
	public long expire(String key, long second) {
		// TODO Auto-generated method stub
		return jedisCluster.expire(key, second);
	}

	@Override
	public long ttl(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.ttl(key);
	}

	@Override
	public long del(String key) {
		return jedisCluster.del(key);
	}

	@Override
	public long hdel(String hkey, String key) {
		// TODO Auto-generated method stub
		return jedisCluster.hdel(hkey, key);
	}

	@Override
	public byte[] bset(byte[] key, byte[] value) {
		// TODO Auto-generated method stub
		return jedisCluster.bset(key, value);
	}

	@Override
	public void bexpire(byte[] key, int second) {
		// TODO Auto-generated method stub
		jedisCluster.bexpire(key, second);

	}

	@Override
	public byte[] bget(byte[] key) {
		// TODO Auto-generated method stub
		return jedisCluster.bget(key);
	}

	@Override
	public void bdel(byte[] key) {
		// TODO Auto-generated method stub
		jedisCluster.bdel(key);

	}

	@Override
	public Set<byte[]> bkeys(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.bkeys(key);
	}

}
