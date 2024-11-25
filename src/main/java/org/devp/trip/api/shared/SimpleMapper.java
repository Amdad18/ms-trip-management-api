/**
 * 
 */
package org.devp.trip.api.shared;

import org.devp.trip.api.dto.UserCacheDto;
import org.devp.trip.api.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


/**
 * 
 * @author amdad
 *
 */

@Mapper
public interface SimpleMapper {

	public static final SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);
	
	@Mappings({
		
	})
	UserCacheDto toUserCacheDto(UserDto user);
	
	@Mappings({
		
	})
	UserDto toUserDto(UserCacheDto user);
	
}
