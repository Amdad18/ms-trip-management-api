package org.devp.trip.api.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

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
public class TripDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private BigInteger id;
	
	private BigInteger transporterId;
	
	private BigInteger userId;
	
	private String pickupLocation;
	
	private String dropoffLocation;
	
	private String pickupLatLng;
	
	private String dropoffLatLng;
	
	private String others;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private TripsStatus status;
	
}
