package org.devp.trip.api.cache;

import org.devp.trip.api.dto.UserCacheDto;

/**
 * 
 * @author amdad
 *
 */
public interface UserCacheService {

	UserCacheDto findByAuthToken(String authToken);
	
}
