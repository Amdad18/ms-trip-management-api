package org.devp.trip.api.repository.impl;

import static org.devp.trip.model.tables.Trips.TRIPS;

import java.math.BigInteger;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.devp.trip.api.dto.TripDto;
import org.devp.trip.api.repository.TripRepository;
import org.devp.trip.api.util.ConnectionUtil;
import org.devp.trip.model.tables.pojos.Trips;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.types.ULong;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 */
@Slf4j
@Repository
public class TripRepositoryImpl implements TripRepository {
	
	public Optional<BigInteger> add(Trips trips) {
		
		if(trips == null 
				|| trips.getUserId() == null 
				|| trips.getPickupLocation() == null
				|| trips.getDropoffLocation() == null) {
			return Optional.empty();
		}
		try(Connection connection = ConnectionUtil.getWriteConnection()){
			DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
			
			ULong id = create.insertInto(TRIPS, 
					TRIPS.USER_ID, 
					TRIPS.PICKUP_LOCATION,
					TRIPS.PICKUP_LAT_LNG,
					TRIPS.DROPOFF_LOCATION,
					TRIPS.DROPOFF_LAT_LNG,
					TRIPS.TRANSPORTER_ID,
					TRIPS.OTHERS_INFO,
					TRIPS.STATUS)
			.values(trips.getUserId(),
					trips.getPickupLocation(),
					trips.getPickupLatLng(),
					trips.getDropoffLocation(),
					trips.getDropoffLatLng(),
					trips.getTransporterId(),
					trips.getOthersInfo(),
					trips.getStatus())
			.returning()
			.fetchOne(TRIPS.ID);
			
			if(id != null) {
				return Optional.ofNullable(id.toBigInteger());
			}
			
			}catch(Exception e) {
				log.info("Error :", e);
			}

		return Optional.empty();
	}


	public Optional<Boolean> update(BigInteger id, Map<String, Object> fieldMap) {
		
		if (id == null || fieldMap == null) {
			return Optional.ofNullable(Boolean.FALSE);
		}
		
		fieldMap.put("updated_at", LocalDateTime.now());
		
		try (Connection connection = ConnectionUtil.getWriteConnection()) {
			DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
			
			boolean isUpdated = create.update(TRIPS)
			.set(fieldMap)
			.where(TRIPS.ID.eq(ULong.valueOf(id)))
			.execute() > 0;
			
			return Optional.ofNullable(isUpdated);
			
		} catch (Exception e) {
			log.error("Error :", e);
		}
		
		return Optional.ofNullable(Boolean.FALSE);
	}

	
	public Optional<BigInteger> findIdByUserIdAndTripId(BigInteger userId,BigInteger tripId) {
		
		if (userId == null 
				|| tripId == null) {
			return Optional.empty();
		}
		try (Connection connection = ConnectionUtil.getReadConnection()) {
			DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
			
			Record1<ULong> record = create.select(TRIPS.ID)
			.from(TRIPS)
			.where(TRIPS.USER_ID.eq(ULong.valueOf(userId))
					.and(TRIPS.ID.eq(ULong.valueOf(tripId))))
			.fetchOne();
			
			if(record != null) {
				return Optional.ofNullable(record.getValue(TRIPS.ID).toBigInteger());
			}

		} catch (Exception e) {
			log.error("Error :", e);
		}
		return Optional.empty();
	}
	

	public Optional<TripDto> findDtoByTripIdUserId(BigInteger userId, BigInteger tripId) {

		if (tripId == null || userId == null) {
			return Optional.empty();
		}
		try (Connection connection = ConnectionUtil.getReadConnection()) {
			DSLContext create = DSL.using(connection, SQLDialect.MYSQL);

			Record record = create.selectFrom(TRIPS)
					.where(TRIPS.USER_ID.eq(ULong.valueOf(userId))
							.and(TRIPS.ID.eq(ULong.valueOf(tripId))))
					.fetchAny();

			if (record != null) {
				return Optional.ofNullable(record.into(TripDto.class));
			}

		} catch (Exception e) {
			log.error("Error :", e);
		}
		return Optional.empty();
	}
	
	
}
