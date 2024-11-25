/**
 * 
 */
package org.devp.trip.api.cache;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.devp.trip.api.util.StringUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import io.lettuce.core.KeyValue;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 *
 */
@Slf4j
@Component
public class RedisServiceImpl implements RedisService {

	/** 
	 * 
	 */
	public RedisServiceImpl() {
		log.debug("RedisServiceImpl");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		log.info("RedisServiceImpl");
		RedisServiceImpl service = new RedisServiceImpl();
		Map<String, Object> data = new HashMap<>(); 
		data.put("hello", "world1");
		System.out.println(service.lpush("contacts",  new String[] {data.toString()})); 
		System.out.println(service.lcount("contacts")); 
		System.out.println((List<Map<String, Object>>) (List<?>)service.lrange("contacts", 0 , -1)); 
		System.out.println(service.delete("contacts"));
		System.out.println(service.lcount("contacts")); 
		
	}
	

	@Override
	public boolean expire(String key, long seconds) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.expire(key, seconds);
			success = true;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return success;
	}

	
	@Override
	public boolean expireAt(String key, Date timeStamp) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.expireat(key, timeStamp);
			success = true;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return success;
	}

	@Override
	public boolean exists(String key) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			long existKeyCount = commands.exists(key); 
			log.debug("existKeyCount : "+existKeyCount);
			success = existKeyCount>0;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return success;
	}

	@Override
	public String readStringValue(String key) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return null;
			}
			RedisCommands<String, Object> commands = connection.sync();
			Object objValue = commands.get(key);
			return (objValue!=null)?objValue.toString():null;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return null;
	}

	@Override
	public boolean writeStringValue(String key, String value) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.set(key, value);
			success = true;
			
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		
		if(success) {
			expire(key, RedisConfig.DEFAULT_REDIS_EXPIRE_SECONDS);
		}
		
		return success;
	}

	@Override
	public boolean writeStringValue(String key, String value, long expireSeconds) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.setex(key, expireSeconds , value);
			success = true;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return success;
	}

	@Override
	public Double readFloatValue(String key) {
		Double value = null;
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return value;
			}
			RedisCommands<String, Object> commands = connection.sync();
			Object objValue = commands.get(key); 
			if(null == objValue) {
				return value;
			}
			if(objValue  instanceof Double) {
				return (Double) objValue; 
			}
			String objStringValue = (String) objValue;
			if(NumberUtils.isCreatable(objStringValue)) { 
				value = Double.parseDouble(objStringValue);
			}
			return value;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return value;
	}

	@Override
	public boolean writeFloatValue(String key, Double value) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.set(key, value);
			success = true;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		
		if(success) {
			expire(key, RedisConfig.DEFAULT_REDIS_EXPIRE_SECONDS);
		}
		
		return success;
	}
	
	@Override
	public boolean writeFloatValue(String key, Double value, long expireSeconds) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.setex(key, expireSeconds, value);
			success = true;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		
		return success;
	}

	@Override
	public Double incFloatValue(String key, Double value) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return null;
			}
			RedisCommands<String, Object> commands = connection.sync();
			return commands.incrbyfloat(key , value);
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return null;
	}

	@Override
	public Long readIntegerValue(String key) {
		Long value = null;
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return value;
			}
			RedisCommands<String, Object> commands = connection.sync();
			Object objValue = commands.get(key); 
			if(null == objValue) {
				return value;
			}
			if(objValue instanceof Long) {
				return (Long) objValue; 
			}
			String objStringValue = (String) objValue;
			if(NumberUtils.isCreatable(objStringValue)) { 
				value = Long.parseLong(objStringValue);
			}
			return value;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return value;
	}

	@Override
	public boolean writeIntegerValue(String key, Long value) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.set(key, value);
			success = true;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		
		if(success) {
			expire(key, RedisConfig.DEFAULT_REDIS_EXPIRE_SECONDS);
		}
		
		return success;
	}
	
	@Override
	public boolean writeIntegerValue(String key, Long value, long expireSeconds) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.setex(key, expireSeconds, value);
			success = true;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		
		return success;
	}

	@Override
	public Long incIntegerValue(String key, Long value) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return null;
			}
			RedisCommands<String, Object> commands = connection.sync();
			return commands.incrby(key , value);
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return null;
	}

	@Override
	public boolean delete(String key) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.del(key);
			success = true;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return success;
	}

	@Override
	public boolean hashDelete(String hashName, String fieldName) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			commands.hdel(fieldName, fieldName);
			return true;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return false;
	}

	@Override
	public boolean hashExists(String hashName, String fieldName) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			return commands.hexists(hashName, fieldName);
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return false;
	}
	

	@Override
	public String hashGetString(String hashName, String fieldName) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return null;
			}
			RedisCommands<String, Object> commands = connection.sync();
			Object objValue = commands.hget(hashName, fieldName);
			if(null == objValue) {
				return null;
			}
			return objValue.toString();
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return null;
	}

	@Override
	public Long hashGetInteger(String hashName, String fieldName) {
		StatefulRedisConnection<String, Object> connection = null;
		Long value = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return value;
			}
			RedisCommands<String, Object> commands = connection.sync();
			Object objValue = commands.hget(hashName, fieldName);
			if(null == objValue) {
				return value;
			}
			if(objValue  instanceof Long) {
				return (Long) objValue; 
			}
			String objStringValue = objValue.toString();
			if(NumberUtils.isCreatable(objStringValue)) { 
				value = Long.parseLong(objStringValue);
			}
			return value;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return value;
	}

	@Override
	public Double hashGetDouble(String hashName, String fieldName) {
		StatefulRedisConnection<String, Object> connection = null;
		Double value = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return value;
			}
			RedisCommands<String, Object> commands = connection.sync();
			Object objValue = commands.hget(hashName, fieldName);
			if(null == objValue) {
				return value;
			}
			if(objValue  instanceof Double) {
				return (Double) objValue; 
			}
			String objStringValue = objValue.toString();
			if(NumberUtils.isCreatable(objStringValue)) { 
				value = Double.parseDouble(objStringValue);
			}
			return value;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return value;
	}

	@Override
	public Map<String,Object> hashGetAll(String hashName) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean shouldRetry = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return new HashMap<>();
			}
			RedisCommands<String, Object> commands = connection.sync();
			return commands.hgetall(hashName);
		} catch (IOException e) {
			shouldRetry = true;
		} catch (Exception e) {
			log.error("Error :",e); 
			shouldRetry = true;
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		
		if(shouldRetry) { 
			log.error("shouldRetry : "+shouldRetry); 
			try {
				connection = RedisUtil.getInstance().openSyncConnection();
				if(null == connection) {
					return new HashMap<>();
				}
				RedisCommands<String, Object> commands = connection.sync();
				return commands.hgetall(hashName);
			} catch (IOException e) {
				log.error("Error :",e); 
			} catch (Exception e) {
				log.error("Error :",e); 
			} finally {
				if(null!=connection) {
					RedisUtil.getInstance().releaseConnection(); 
				}
			}
			
		}
		
		return new HashMap<>();
	}
	
	@Override
	public boolean hashRemoveAll(String hashName) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			List<String> keys = commands.hkeys(hashName);
			if(null == keys || keys.isEmpty()) {
				return false;
			}
			log.debug("keys :" +keys);
 			long count = commands.hdel(hashName, StringUtil.toStringArray(keys)); 
			log.debug("count : "+count); 
			return count>0;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return false;
	}

	@Override
	public JSONObject hashMGet(String hashName, String[] selectedFields) {
		StatefulRedisConnection<String, Object> connection = null;
		JSONObject result = new JSONObject();
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return result;
			}
			RedisCommands<String, Object> commands = connection.sync();
			List<KeyValue<String, Object>> results = commands.hmget(hashName, selectedFields);
			results.stream().forEach(idx -> {
				result.put(idx.getKey(), idx.getValue());
			});
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return result;
	}

	@Override
	public long hashMSet(String hashName, Map<String,Object> fieldValueMap) {
		StatefulRedisConnection<String, Object> connection = null;
		long count = 0;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return count;
			}
			RedisCommands<String, Object> commands = connection.sync();
			count = commands.hset(hashName, fieldValueMap);
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		
		if(count>0) {
			expire(hashName, RedisConfig.DEFAULT_REDIS_EXPIRE_SECONDS);
		}
		
		return count;
	}

	@Override
	public boolean hashSet(String hashName, String fieldName, String fieldValue) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			success = commands.hset(hashName, fieldName, fieldValue);
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		
		if(success) {
			expire(hashName, RedisConfig.DEFAULT_REDIS_EXPIRE_SECONDS);
		}
		
		return success;
	}

	@Override
	public boolean hashSetIfNotExists(String hashName, String fieldName, String fieldValue) {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			RedisCommands<String, Object> commands = connection.sync();
			success = commands.hsetnx(hashName, fieldName, fieldValue);
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		
		if(success) {
			expire(hashName, RedisConfig.DEFAULT_REDIS_EXPIRE_SECONDS);
		}
		
		return success;
	}

	@Override
	public boolean isRedisAlive() {
		StatefulRedisConnection<String, Object> connection = null;
		boolean success = false;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			if(null == connection) {
				return false;
			}
			success = null != connection;
		} catch (Exception e) {
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return success;
	}

	@Override
	public List<String> findMatchingKeys(String pattern) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			return commands.keys(pattern);
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return Collections.emptyList();
	}

	@Override
	public long lpushObject(String key, Object[] values) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			return commands.lpush(key, values);
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return 0;
	}
	
	@Override
	public long lpush(String key, String[] values) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			return commands.lpush(key, values);
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return 0;
	}
	
	@Override
	public long rpushObject(String key, Object[] values) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			return commands.rpush(key, values);
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return 0;
	}
	
	@Override
	public long rpush(String key, String[] values) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			return commands.rpush(key, values);
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return 0;
	}

	@Override
	public List<Object> lpopObject(String key, long count) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			return (List<Object>) (Object) commands.lpop(key, count);
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return Collections.emptyList();
	}
	
	@Override
	public List<String> lpop(String key, long count) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			return (List<String>) (Object) commands.lpop(key, count);
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return Collections.emptyList();
	}

	@Override
	public long lcount(String key) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			return  commands.llen(key);
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return 0;
	}
	
	@Override
	public List<Object> lrange(String key, int from, int to) {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			return commands.lrange(key, from, to);
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return Collections.emptyList();
	}

	@Override
	public boolean hashRemoveAllKey() {
		StatefulRedisConnection<String, Object> connection = null;
		try {
			connection = RedisUtil.getInstance().openSyncConnection();
			RedisCommands<String, Object> commands = connection.sync();
			
			List<String> keys = commands.keys("*");
			log.info("keys"+keys); 
			
			for(String key:keys) { 
				log.info("key :"+key); 
				long count = commands.del(key);
				log.info("count :"+count); 
			}
			
			return true;
			
		} catch (Exception e) { 
			log.error("Error :",e); 
		} finally {
			if(null!=connection) {
				RedisUtil.getInstance().releaseConnection(); 
			}
		}
		return false;
	}


}
