package org.devp.trip.api.repository.impl;

import static org.devp.trip.model.tables.Users.USERS;

import java.sql.Connection;

import org.devp.trip.api.dto.UserDto;
import org.devp.trip.api.repository.UserRepository;
import org.devp.trip.api.util.ConnectionUtil;
import org.devp.trip.api.util.StringUtil;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Override
	public UserDto findUserByAuthToken(String authToken) {
		if (StringUtil.isBlank(authToken)) {
			return null;
		}
		
 		try (Connection connection = ConnectionUtil.getReadConnection()){
			DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
				
				Record record =	create.selectFrom(USERS)
				.where(USERS.API_KEY.eq(authToken))
				.fetchAny();
				if(record != null) {
					return record.into(UserDto.class);
				}
		} catch (Exception e) {
			log.error("Error : ",e); 
		}
		return null;
	}
	
}
