package org.devp.trip.api.shared;

import java.security.Principal;

import org.devp.trip.api.dto.UserCacheDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author amdad
 *
 */
@Getter
@Setter
public class BasicCacheUserPrincipal implements Principal {
	
	private UserCacheDto user;
	
	public BasicCacheUserPrincipal(UserCacheDto user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return user.getEmail();
	}

}
