package com.renrentui.renrencore.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.renrentui.renrencore.util.PropertyUtils;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private ValueOperations<String, Object> getOperation() {
		return redisTemplate.opsForValue();
	}

	/**
	 * redis存储，默认过期时间为1小时
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public void set(String key, Object value) {
		set(key, value, 1, TimeUnit.HOURS);
	}

	/**
	 * redis存储方法
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @param timeout
	 *            过期时间，单位默认为秒，如果需要更改，些调用重载方法{@link set(String key, Object value,
	 *            long timeout, TimeUnit timeUnit)}
	 */
	public void set(String key, Object value, long timeout) {
		set(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * redis存储
	 * 
	 * @param key
	 *            redis key
	 * @param value
	 *            redis value
	 * @param timeout
	 *            过期时间
	 * @param timeUnit
	 *            过期时间单位
	 */
	public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
		ValueOperations<String, Object> operation = getOperation();
		operation.set(suffxKey(key), value, timeout, timeUnit);
	}

	public <T> T get(String key, Class<T> type) {
		ValueOperations<String, Object> operation = getOperation();
		Object object = operation.get(suffxKey(key));

		// TODO: 这里没有判断object的类型是否是T，之后再加；
		return (T) object;
	}

	public void remove(String keyPattern) {
		Set<String> removeKeys = redisTemplate.keys(suffxKey(keyPattern));
		redisTemplate.delete(removeKeys);
	}

	/**
	 * 给key添加后缀或前缀
	 * 
	 * @author hailongzhao
	 * @date 20150902
	 * @param orginKey
	 * @return
	 */
	private String suffxKey(String orginKey) {
		return "java_" + PropertyUtils.getProperty("GlobalVersion") + "_"
				+ orginKey;
	}
}
