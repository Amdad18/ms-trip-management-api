package org.devp.trip.api.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

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
public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private BigInteger id;
	
	private String firstName;
	
	private String lastName;
	
	private String number;
	
	private String email;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String latitude;
	
	private String longitude;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private Byte status;
	
	private String apikey;
	
}
