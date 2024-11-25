package org.devp.trip.api.dto;

import java.io.Serializable;
import java.math.BigInteger;

import org.devp.trip.model.enums.TripsStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author amdad
 *
 */
@Getter
@Setter
@ToString
public class TripRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private BigInteger transporterId;
	
	private TripsStatus status;
	
	private BigInteger tripId;
	
}
