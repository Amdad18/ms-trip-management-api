/**
 * 
 */
package org.devp.trip.api.cache;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 	@author amdad
 *  env
 *
 */
@Slf4j
@Getter
@Setter
public abstract class RedisConfig {


	/**
	 * 
	 */
	private RedisConfig() {
		
	}
    
   private static String getEnvVar(String key, String defaultValue) {
    	
    	
     	if (System.getenv(key) != null 
           		&& !System.getenv(key).isEmpty()) {
               return System.getenv(key).trim();
	   	}
     	
		return defaultValue;
     	
   }
   
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Config"); 
	}
	
	public static final int ONE_HOUR_SECONDS = 3600;
	
    public static final String REDIS_HOST = getEnvVar("REDIS_HOST", "127.0.0.1").trim();
	
    public static final int REDIS_PORT = Integer.parseInt(getEnvVar("REDIS_PORT", Integer.toString(6379))); 
	
    public static final boolean ENABLE_REDIS_SSL = getEnvVar("ENABLE_REDIS_SSL", Boolean.toString(false)).trim().equalsIgnoreCase("true");
	
    public static final boolean ENABLE_REDIS_AUTH = getEnvVar("ENABLE_REDIS_AUTH", Boolean.toString(false)).trim().equalsIgnoreCase("true");
	
    public static final CharSequence REDIS_PASSWORD = getEnvVar("REDIS_PASSWORD", "").trim();
    
    public static final int DEFAULT_REDIS_EXPIRE_SECONDS = Integer.parseInt(getEnvVar("DEFAULT_REDIS_EXPIRE_SECONDS", Integer.toString(ONE_HOUR_SECONDS * 24))); 
    
    public static final boolean ENABLE_DEFAULT_REDIS_EXPIRE_SECONDS = getEnvVar("ENABLE_DEFAULT_REDIS_EXPIRE_SECONDS", Boolean.toString(false)).equalsIgnoreCase("true");
    
    public static final int DEFAULT_RETRY_TIMES = Integer.parseInt(getEnvVar("DEFAULT_RETRY_TIMES", Integer.toString(1)));  
    
    public static final boolean ENABLE_DEFAULT_REDIS_EXPIRE = getEnvVar("ENABLE_DEFAULT_REDIS_EXPIRE", Boolean.toString(false)).equalsIgnoreCase("true");

}
