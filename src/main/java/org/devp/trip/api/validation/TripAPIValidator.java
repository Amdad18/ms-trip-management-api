package org.devp.trip.api.validation;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.devp.trip.api.dto.TripDto;
import org.devp.trip.api.dto.TripRequestDto;
import org.devp.trip.api.dto.UserCacheDto;
import org.devp.trip.api.repository.TripRepository;
import org.devp.trip.api.util.StringUtil;
import org.devp.trip.model.enums.TripsStatus;
import org.devp.trip.model.tables.pojos.Trips;
import org.jooq.types.ULong;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author amdad
 *
 */
@Log4j2
@Component
public class TripAPIValidator {
	
	@Autowired
	private TripRepository tripRepository;
	

	public <T extends ValidationResponse> T  verifyCreateTripRequest(TripDto tripDto, UserCacheDto user) {
	
		JSONObject error = new JSONObject();
		
		TripAPIValidationResponse verifyResponse = new TripAPIValidationResponse();
		
		Trips trips = new Trips();
		
		if(tripDto == null) {
			
			error.put("message", "RequestBody can't be empty");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("RequestBody can't be empty");
			return (T) verifyResponse;
		}
		
		if(StringUtil.isBlank(tripDto.getPickupLocation())){
			
			error.put("message", "pick up location cant be empty");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("pick up location cant be empty");
			return (T) verifyResponse;
		}
		
		if(StringUtil.isBlank(tripDto.getDropoffLocation())){
			
			error.put("message", "Drop off location cant be empty");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("Drop off location cant be empty");
			return (T) verifyResponse;
		}
		
		trips.setUserId(ULong.valueOf(user.getId()));
		trips.setStatus(TripsStatus.CREATED);
		trips.setDropoffLatLng(tripDto.getDropoffLatLng());
		trips.setDropoffLocation(tripDto.getDropoffLocation());
		trips.setOthersInfo(tripDto.getOthers());
		trips.setPickupLatLng(tripDto.getPickupLatLng());
		trips.setPickupLocation(tripDto.getPickupLocation());
		trips.setTransporterId(ULong.valueOf(tripDto.getTransporterId()));
		
		verifyResponse.setTripDto(tripDto);
		verifyResponse.setTrips(trips);
		verifyResponse.setValid(true);
		
		return (T)verifyResponse;
	}
	
	
	public <T extends ValidationResponse> T  verifyModifyTripRequest(TripRequestDto tripRequestDto, UserCacheDto user) {
		
		JSONObject error = new JSONObject();
		
		TripAPIValidationResponse verifyResponse = new TripAPIValidationResponse();
		
		Map<String, Object> fieldMap = new HashMap<>();
		
		if(tripRequestDto == null) {
			
			error.put("message", "RequestBody can't be empty");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("RequestBody can't be empty");
			return (T) verifyResponse;
		}
		
		if(tripRequestDto.getTripId() == null){
			
			error.put("message", "TripId is required");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("TripId is required");
			return (T) verifyResponse;
		}
		
		if(tripRequestDto.getStatus() == null && tripRequestDto.getTransporterId() == null){
			
			error.put("message", "Trip status need to provide");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("Trip status need to provide");
			return (T) verifyResponse;
		}
		
		Optional<BigInteger> id = tripRepository.findIdByUserIdAndTripId(user.getId(), tripRequestDto.getTripId());
		if(id.isEmpty()) {
			error.put("message", "No trip found");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("No trip found");
			return (T) verifyResponse;
		}

				
		fieldMap.put("status", tripRequestDto.getStatus());
		
		verifyResponse.setFieldMap(fieldMap);
		verifyResponse.setValid(true);
		
		return (T)verifyResponse;
	}
	
	public <T extends ValidationResponse> T  verifyModifyTripAssignRequest(TripRequestDto tripRequestDto, UserCacheDto user) {
		
		JSONObject error = new JSONObject();
		
		TripAPIValidationResponse verifyResponse = new TripAPIValidationResponse();
		
		Map<String, Object> fieldMap = new HashMap<>();
		
		if(tripRequestDto == null) {
			
			error.put("message", "RequestBody can't be empty");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("RequestBody can't be empty");
			return (T) verifyResponse;
		}
		
		if(tripRequestDto.getTripId() == null){
			
			error.put("message", "TripId is required");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("TripId is required");
			return (T) verifyResponse;
		}
		
		if(tripRequestDto.getStatus() == null && tripRequestDto.getTransporterId() == null){
			
			error.put("message", "Trip transporter id need to provide");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("Trip transporter id need to provide");
			return (T) verifyResponse;
		}
		
		Optional<BigInteger> id = tripRepository.findIdByUserIdAndTripId(user.getId(), tripRequestDto.getTripId());
		if(id.isEmpty()) {
			error.put("message", "No trip found");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("No trip found");
			return (T) verifyResponse;
		}

				
		fieldMap.put("status", TripsStatus.BOOKED);
		fieldMap.put("transporter_id", tripRequestDto.getTransporterId());
		
		verifyResponse.setFieldMap(fieldMap);
		verifyResponse.setValid(true);
		
		return (T)verifyResponse;
	}
	
	public <T extends ValidationResponse> T  verifyTripDetailsRequest(BigInteger tripId, UserCacheDto user) {
		
		JSONObject error = new JSONObject();
		
		TripAPIValidationResponse verifyResponse = new TripAPIValidationResponse();
		
		if(tripId == null){
			
			error.put("message", "TripId is required");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("TripId is required");
			return (T) verifyResponse;
		}
		
		Optional<BigInteger> id = tripRepository.findIdByUserIdAndTripId(user.getId(), tripId);
		if(id.isEmpty()) {
			error.put("message", "No trip found");
			error.put("success", false);
			verifyResponse.setError(error);
			log.error("No trip found");
			return (T) verifyResponse;
		}

		verifyResponse.setTripId(tripId);
		verifyResponse.setValid(true);
		
		return (T)verifyResponse;
	}
	
}
