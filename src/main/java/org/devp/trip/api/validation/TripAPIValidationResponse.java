package org.devp.trip.api.validation;


import java.math.BigInteger;
import java.util.Map;

import org.devp.trip.api.dto.TripDto;
import org.devp.trip.model.tables.pojos.Trips;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author amdad
 *
 */
@Getter
@Setter
public class TripAPIValidationResponse  extends ValidationResponse{

	private TripDto tripDto;
	
	private Trips trips;
	
	private Map<String, Object> fieldMap;
	
	private BigInteger tripId;
	
}
