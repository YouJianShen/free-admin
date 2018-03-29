package com.suit.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisSessionDAO extends AbstractSessionDAO {

	private static Logger logger = LoggerFactory
			.getLogger(RedisSessionDAO.class);

//	private ConcurrentMap<Serializable, Session> sessions;

	public static String UPLOAD_FLAG = "needUpload";
	/**
	 * shiro-redis的session对象前缀
	 */
	StringRedisTemplate stringRedisTemplate;
	/**
	 * The Redis key prefix for the sessions
	 */
	private String keyPrefix = "shiro_redis_session:";

	public RedisSessionDAO() {
		super();
//		this.sessions = new ConcurrentHashMap<Serializable, Session>();
	}

	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
		Serializable id = session.getId();
//		if (id != null) {
//			sessions.remove(id);
//		}
		stringRedisTemplate.delete(this.getByteKey(session.getId()));
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}

//		if (sessions.containsKey(sessionId)) {
//			return sessions.get(sessionId);
//		}
		ValueOperations<String, String> valueOperations = stringRedisTemplate
				.opsForValue();
		Session s = (Session) SerializableUtils.deserialize(valueOperations
				.get(this.getByteKey(sessionId)));
//		if (s != null) {
//			sessions.putIfAbsent(sessionId, s);
//		}
		return s;
	}

	// 用来统计当前活动的session
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();

		Set<String> keys = stringRedisTemplate.keys(this.keyPrefix + "*");
		ValueOperations<String, String> valueOperations = stringRedisTemplate
				.opsForValue();
		if (keys != null && keys.size() > 0) {
			for (String key : keys) {
				Session s = (Session) SerializableUtils
						.deserialize(valueOperations.get(key));
				sessions.add(s);
			}
		}

		return sessions;
	}

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private String getByteKey(Serializable sessionId) {
		String preKey = this.keyPrefix + sessionId;
		return preKey;
	}

	/**
	 * Returns the Redis session keys prefix.
	 * 
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	/**
	 * save session
	 * 
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}

//		sessions.putIfAbsent(session.getId(), session);
		String key = getByteKey(session.getId());
		String value = SerializableUtils.serialize(session);

		ValueOperations<String, String> valueOperations = stringRedisTemplate
				.opsForValue();
		valueOperations.set(key, value);
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

	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	public void update(Session session) throws UnknownSessionException {
//		sessions.putIfAbsent(session.getId(), session);
		
		//检测是否需要同步session至redis
//		if (session.getAttribute(UPLOAD_FLAG) != null
//				&& "true".equals(session.getAttribute(UPLOAD_FLAG))) {
//			session.removeAttribute(UPLOAD_FLAG);
			this.saveSession(session);
//		}
	}
}