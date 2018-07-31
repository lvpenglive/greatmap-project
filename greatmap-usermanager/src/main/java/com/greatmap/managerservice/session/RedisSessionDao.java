package com.greatmap.managerservice.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.SerializationUtils;

import com.greatmap.managerservice.dao.JedisClient;


public class RedisSessionDao extends AbstractSessionDAO{

	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;	
	@Value("${USER_SESSION_EXPIRE}")
	private Integer USER_SESSION_EXPIRE;
	
	
	private byte[] getKey(String key) {
		return (REDIS_USER_SESSION_KEY + key).getBytes();
		
	}
	
	
	private void saveSession(Session session) {
		if(session != null && session.getId() != null) {
			byte[] key = getKey(session.getId().toString());
			byte[] value = SerializationUtils.serialize(session);
			jedisClient.bset(key, value);
			jedisClient.bexpire(key, USER_SESSION_EXPIRE);
		}
		
	}
	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub
		if(session != null && session.getId() != null) {
			return ;
		}
		byte[] key = getKey(session.getId().toString());
		jedisClient.bdel(key);
	}

	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		Set<byte[]> keys =jedisClient.bkeys(REDIS_USER_SESSION_KEY);
		Set<Session> sessions = new HashSet<>();
		if(CollectionUtils.isEmpty(keys)) {
			return sessions;
		}
		for(byte[] key : keys) {
			Session session = (Session) SerializationUtils.deserialize(jedisClient.bget(key));
			sessions.add(session);
		}
		return sessions;
	}

	@Override
	public void update(Session arg0) throws UnknownSessionException {
		// TODO Auto-generated method stub
		saveSession(arg0);
	}

	@Override
	protected Serializable doCreate(Session session) {
		// 创建session
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable arg0) {
		// TODO Auto-generated method stub
		System.out.println("doReadSession");
		if(arg0 == null) {
			return null;

		}
		byte[] key = getKey(arg0.toString());
		byte[] value = jedisClient.bget(key);
		return (Session) SerializationUtils.deserialize(value);
	}

}
