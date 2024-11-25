package org.devp.trip.api.repository;

import org.devp.trip.api.dto.UserDto;

/**
 * @author amdad
 */
public interface UserRepository {
	
	UserDto findUserByAuthToken(String authToken);
	
}
