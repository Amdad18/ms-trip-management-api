package org.devp.trip.api.cache;

import java.math.BigInteger;
import java.util.Map;

import org.devp.trip.api.dto.UserCacheDto;
import org.devp.trip.api.dto.UserDto;
import org.devp.trip.api.repository.UserRepository;
import org.devp.trip.api.shared.SimpleMapper;
import org.devp.trip.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author amdad
 *
 */ 
@Slf4j
@Service
public class UserCacheServiceImpl implements  UserCacheService {
	 
	private RedisService redisService;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserCacheServiceImpl() {


		if(redisService == null) {
			redisService = new RedisServiceImpl();
		}
		
		
	}
	
	private String buildIDKey(BigInteger userId) { 
		return new StringBuilder().append(CacheConstant.USER_ID_HASH).append(userId.toString()).toString();	
	}
	
	private UserCacheDto cache(UserDto user) {
		
		UserCacheDto userCacheDto = null;
		
		final ObjectMapper mapper = new CustomObjectMapper().getObjectMapper();
		
		String hashIdKey = buildIDKey(user.getId()); 
		
		String hashTokenKey = CacheConstant.USER_AUTHTOKEN_HASH+user.getApikey();
		
		log.debug("hashIdKey : "+hashIdKey); 
		
		log.debug("hashTokenKey : "+hashTokenKey); 
		
		userCacheDto = SimpleMapper.INSTANCE.toUserCacheDto(user);
		
		Map<String, Object> result = mapper.convertValue(userCacheDto, Map.class);
		long idSuccessCount = redisService.hashMSet(hashIdKey, result);
		long keySuccessCount = redisService.hashMSet(hashTokenKey, result);
		log.info("idSuccessCount : "+idSuccessCount); 
		log.info("keySuccessCount : "+keySuccessCount); 
		
		return userCacheDto;
		
	} 

	@Override
	public UserCacheDto findByAuthToken(String authToken) {
		
		UserCacheDto userCacheDto = null;
		
		if(StringUtil.isBlank(authToken)) { return userCacheDto; }
		
		final ObjectMapper mapper = new CustomObjectMapper().getObjectMapper();
		
		String hashKey = CacheConstant.USER_AUTHTOKEN_HASH+authToken;
		
		log.debug("hashKey : "+hashKey); 
		
		Map<String, Object> result = redisService.hashGetAll(hashKey);
		
		log.debug("result : "+result); 
		
		if(null !=result 
				&& !result.isEmpty()) {
			userCacheDto = mapper.convertValue(result, UserCacheDto.class);
			return userCacheDto;
		}
		
		UserDto user = userRepository.findUserByAuthToken(authToken);
		if(null!=user) {
			userCacheDto = cache(user);
		}
		
		return userCacheDto;
	}

}
