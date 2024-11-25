/**
 * 
 */
package org.devp.trip.api.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

/**
 * @author amdad
 *
 */
public interface RedisService {
	
	/**
	 * String data operations api
	 */
	String readStringValue(String key);
	
	boolean writeStringValue(String key, String value);
	
	boolean writeStringValue(String key, String value, long expireSeconds);
	
	/**
	 * Floating data operations api
	 */
	Double readFloatValue(String key);
	
	boolean writeFloatValue(String key, Double value);
	
	boolean writeFloatValue(String key, Double value, long expireSeconds);
	
	Double incFloatValue(String key, Double value);
	
	/**
	 * Integer data operations api
	 */
	Long readIntegerValue(String key);
	
	boolean writeIntegerValue(String key, Long value);
	
	boolean writeIntegerValue(String key, Long value, long expireSeconds);
	
	Long incIntegerValue(String key, Long value);
	
	
	/**
	 * expire operations api
	 */
	boolean expire(String key, long seconds);
	
	boolean expireAt(String key, Date timeStamp);
	
	/**
	 * check exist operations api
	 */
	boolean exists(String key);
	
	/**
	 * Delete Key
	 */
	boolean delete(String key);
	
	boolean hashRemoveAllKey();
	
	
	/**
	 * Hash Operations Methods
	 */
	boolean hashDelete(String hashName, String fieldName);
	
	boolean hashExists(String hashName, String fieldName);
	
	String hashGetString(String hashName, String fieldName);
	
	Long hashGetInteger(String hashName, String fieldName);
	
	Double hashGetDouble(String hashName, String fieldName);
	
	Map<String,Object> hashGetAll(String hashName);
	
	boolean hashRemoveAll(String hashName);
	
	JSONObject hashMGet(String hashName, String[] selectedFields);
	
	long hashMSet(String hashName, Map<String,Object> fieldValueMap);
	
	boolean hashSet(String hashName, String fieldName, String fieldValue);
	
	boolean hashSetIfNotExists(String hashName, String fieldName, String fieldValue);
	
	boolean isRedisAlive();
	
	List<String> findMatchingKeys(String pattern);
	
	long lpushObject(String key, Object[] values);
	
	long rpushObject(String key, Object[] values);
	
	List<Object> lpopObject(String key, long count);
	
	long lpush(String key, String[] values);
	
	long rpush(String key, String[] values);
	
	List<String> lpop(String key, long count);
	
	long lcount(String key);
	
	List<Object> lrange(String key, int from, int to);	

}
