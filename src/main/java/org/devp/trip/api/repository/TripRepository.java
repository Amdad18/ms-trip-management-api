package org.devp.trip.api.repository;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

import org.devp.trip.api.dto.TripDto;
import org.devp.trip.model.tables.pojos.Trips;

/**
 * @author amdad
 */
public interface TripRepository {
	
	Optional<BigInteger> add(Trips trips);
	
	Optional<Boolean> update(BigInteger id, Map<String, Object> fieldMap);
	
	Optional<BigInteger> findIdByUserIdAndTripId(BigInteger userId, BigInteger tripId);
	
	Optional<TripDto> findDtoByTripIdUserId(BigInteger userId, BigInteger tripId);
	
}
