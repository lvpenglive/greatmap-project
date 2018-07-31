package com.greatmap.managerservice.dao;

import java.util.Set;

public interface JedisClient {

	String get(String key);
	String set(String key,String value);

	String hget(String hkey,String key);
	long hset(String hkey,String key,String value);
    long incr(String key);
    long expire(String key,long second);

    long ttl(String key);
    long del(String key);
    long hdel(String hkey,String key);
    
    
    byte[] bget(byte[] key);
    byte[] bset(byte[] key,byte[] value);
    void bexpire(byte[] key,int second);
    void bdel(byte[] key);
    Set<byte[]> bkeys(String key);

}
