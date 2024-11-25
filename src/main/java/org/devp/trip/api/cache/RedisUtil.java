/**
 * 
 */
package org.devp.trip.api.cache;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.support.ConnectionPoolSupport;
import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 */
@Slf4j
public class RedisUtil {
	
    private static final int MAX_REDIS_CONNECTION_IDLE = Integer.parseInt(getEnvVar("MAX_REDIS_CONNECTION_IDLE", "100")); //Maximum maintenance connection
    private static final int MIN_REDIS_CONNECTION_IDLE = Integer.parseInt(getEnvVar("MIN_REDIS_CONNECTION_IDLE", "10")); //Minimal Maintaining available quantity
    private static final int MAX_TOTAL_REDIS_CONNECTION = Integer.parseInt(getEnvVar("MAX_TOTAL_REDIS_CONNECTION", "100")); //Maximum number of available quantities
    private static final int MAX_WAIT_SECONDS = Integer.parseInt(getEnvVar("MAX_WAIT_SECONDS", "60"));
    private static final boolean TEST_ON_BORROW = true ;
	
	private static RedisUtil instance = null;
	
	private static RedisClient redisClient = null;
	private static final ThreadLocal<StatefulRedisConnection<String, Object>> CONNECTION_HOLDER = new ThreadLocal<>()  ;
	private static GenericObjectPool<StatefulRedisConnection<String, Object>> pool = null;
	
	private static String getEnvVar(String key, String defaultValue) {
    	
     	if (System.getenv(key) != null 
           		&& !System.getenv(key).isEmpty()) {
               return System.getenv(key);
	   	}
     	
		return defaultValue;
		
 	}
	
	private RedisUtil() {
		log.debug(RedisConfig.REDIS_HOST+"");
		log.debug(RedisConfig.REDIS_PORT+"");
		log.debug("RedisUtil"); 
		
		System.out.println(RedisConfig.REDIS_HOST+"");
		System.out.println(RedisConfig.REDIS_PORT+"");
		
		RedisURI uri = RedisURI.create(RedisConfig.REDIS_HOST, RedisConfig.REDIS_PORT);
		
		log.debug(RedisConfig.ENABLE_REDIS_SSL+"");
		
		if(RedisConfig.ENABLE_REDIS_SSL) {
			uri.setSsl(RedisConfig.ENABLE_REDIS_SSL);
		}
		
		log.debug(RedisConfig.ENABLE_REDIS_AUTH+"");
		if(RedisConfig.ENABLE_REDIS_AUTH) {
			uri.setPassword(RedisConfig.REDIS_PASSWORD);
		}
		
		redisClient = RedisClient.create(uri);
		redisClient.setOptions(ClientOptions.builder()
                .scriptCharset(StandardCharsets.US_ASCII)
                .pingBeforeActivateConnection(true)
                .build());
		log.debug("redisClient: "+redisClient); 
		
        GenericObjectPoolConfig config = new GenericObjectPoolConfig(); //Configure object 
        config.setMaxIdle (MAX_REDIS_CONNECTION_IDLE); // Set the maximum number of maintenance connections
        config.setMinIdle (MIN_REDIS_CONNECTION_IDLE); // Set the minimum number of maintenance connections
        config.setMaxTotal (MAX_TOTAL_REDIS_CONNECTION); // The number of available connections for the connection pool
        config.setTestOnBorrow (TEST_ON_BORROW); // Return after the connection test
        config.setMaxWaitMillis(1000*MAX_WAIT_SECONDS); 
		
		pool = ConnectionPoolSupport.createGenericObjectPool(() -> redisClient.connect( new  SerializedObjectCodec()), config);
		
	}
	
	
	public static RedisUtil getInstance() {
		if (instance == null) {
			synchronized (RedisUtil.class) {
				instance = new RedisUtil();
			}
		}
		log.debug("instance :"+instance);  
		return instance;
	}
	
	public StatefulRedisConnection<String, Object> openSyncConnection() throws Exception {
		
		StatefulRedisConnection<String, Object> connection = CONNECTION_HOLDER.get();
		log.debug("connection : "+connection);
		if(null == connection) {
			synchronized(RedisUtil.class) { 
				if(null!=pool) {
					connection = build(); 
					log.debug("connection : "+connection);
					CONNECTION_HOLDER.set(connection);
				}
			}
		}
		return connection;
		
	}
	
	private static StatefulRedisConnection<String, Object> build() {
	        try {
	            return pool.borrowObject() ;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null ;
    }
	
	public void releaseConnection() {
		StatefulRedisConnection<String, Object> connection = CONNECTION_HOLDER.get() ;
        if (connection != null) {
            connection.close();
            CONNECTION_HOLDER.remove();
        }
	}
	
	public static void closePool() {
		
		try {
			
			// terminating
			if(null!=pool) {
				pool.close();
				pool = null;
			}
			if(null!=redisClient) {
				redisClient.shutdown();
				redisClient = null;
			}
			
		} catch (Exception e) {
			log.error("Error :",e); 
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("RedisUtil");  
		
		RedisService redisService = new RedisServiceImpl();
		
		String key = "users:1";
		
		Map<String,Object> userMap = new HashMap<>();
		userMap.put("id", 1);
		userMap.put("firstName", "firstName");
		userMap.put("lastName", "lastName");
		userMap.put("point", 0.919);
		
		redisService.hashMSet(key, userMap);
		
		userMap = redisService.hashGetAll(key);
		
		redisService.expire(key, 2);
		
		System.out.println("value l174 : "+userMap); 
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e); 
		}
		
		redisService.expire(key, 3);
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e); 
		}
		
		userMap = redisService.hashGetAll(key);
		
		System.out.println("value l192 : "+userMap); 
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e); 
		}
		
		userMap = redisService.hashGetAll(key);
		
		System.out.println("value l202 : "+userMap); 
		
		Long id = redisService.hashGetInteger(key, "id");
		
		System.out.println("id : "+id); 
		
		Double point = redisService.hashGetDouble(key, "point");
		
		System.out.println("point : "+point);  
		
	}

}
