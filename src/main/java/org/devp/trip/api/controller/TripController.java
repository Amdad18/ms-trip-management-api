package org.devp.trip.api.controller;

import java.math.BigInteger;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.devp.trip.api.dto.TripDto;
import org.devp.trip.api.dto.TripRequestDto;
import org.devp.trip.api.repository.TripRepository;
import org.devp.trip.api.shared.BasicCacheUserPrincipal;
import org.devp.trip.api.validation.TripAPIValidationResponse;
import org.devp.trip.api.validation.TripAPIValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 */

@Path("trip")
@Slf4j
@Component
public class TripController {

	@Context
	private HttpServletRequest servletRequest;

	@Context
	private SecurityContext securityContext;
	
	@Autowired
	private TripRepository tripRepository;

	@Inject
	private TripAPIValidator paramValidator;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTrip(@RequestBody TripDto tripDto) {

		JSONObject response = new JSONObject();
		
		BasicCacheUserPrincipal userPrincipal = (BasicCacheUserPrincipal) securityContext.getUserPrincipal();

		if (userPrincipal == null || userPrincipal.getUser() == null) {
			response.put("message", "Unauthorized");
			response.put("success", Boolean.FALSE);
			response.put("responseCode", HttpStatus.SC_UNAUTHORIZED);
			log.error("Error :" + response.toString());
			return Response.status(HttpStatus.SC_UNAUTHORIZED).entity(response.toString()).build();
		}
		
		TripAPIValidationResponse verifyResponse = paramValidator.verifyCreateTripRequest(tripDto, userPrincipal.getUser());

		if (!verifyResponse.isValid()) {
			response.put("message", verifyResponse.getError().get("message"));
			response.put("success", false);
			response.put("responseCode", HttpStatus.SC_BAD_REQUEST);

			log.error("Error :" + verifyResponse.getError().toString());
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(response.toString()).build();
		}
		
		Optional<BigInteger> id = tripRepository.add(verifyResponse.getTrips());
		if(id.isEmpty()) {
			response.put("message", "Trip creation failed");
			response.put("success", false);
			response.put("responseCode", HttpStatus.SC_BAD_REQUEST);

			log.error("Error :" + verifyResponse.getError().toString());
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(response.toString()).build();
		}
		
		response.put("tripId", id.get());
		response.put("success", true);
		response.put("message", "Trip created successfully");
		
		return Response.status(HttpStatus.SC_OK).entity(response.toString()).build();
	}
	
	@PUT
	@Path("/status/change")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeStatus(@RequestBody TripRequestDto tripRequestDto) {

		JSONObject response = new JSONObject();
		
		BasicCacheUserPrincipal userPrincipal = (BasicCacheUserPrincipal) securityContext.getUserPrincipal();

		if (userPrincipal == null || userPrincipal.getUser() == null) {
			response.put("message", "Unauthorized");
			response.put("success", Boolean.FALSE);
			response.put("responseCode", HttpStatus.SC_UNAUTHORIZED);
			log.error("Error :" + response.toString());
			return Response.status(HttpStatus.SC_UNAUTHORIZED).entity(response.toString()).build();
		}
		
		TripAPIValidationResponse verifyResponse = paramValidator.verifyModifyTripRequest(tripRequestDto, userPrincipal.getUser());

		if (!verifyResponse.isValid()) {
			response.put("message", verifyResponse.getError().get("message"));
			response.put("success", false);
			response.put("responseCode", HttpStatus.SC_BAD_REQUEST);

			log.error("Error :" + verifyResponse.getError().toString());
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(response.toString()).build();
		}
		
		Optional<Boolean> isUpdated = tripRepository.update(tripRequestDto.getTripId(), verifyResponse.getFieldMap());
	
		response.put("success", isUpdated.isPresent());
		response.put("message", "Status changed successfully");
		
		return Response.status(HttpStatus.SC_OK).entity(response.toString()).build();
	}
	
	@PUT
	@Path("/transporter/assign")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignTransporter(@RequestBody TripRequestDto tripRequestDto) {

		JSONObject response = new JSONObject();
		
		BasicCacheUserPrincipal userPrincipal = (BasicCacheUserPrincipal) securityContext.getUserPrincipal();

		if (userPrincipal == null || userPrincipal.getUser() == null) {
			response.put("message", "Unauthorized");
			response.put("success", Boolean.FALSE);
			response.put("responseCode", HttpStatus.SC_UNAUTHORIZED);
			log.error("Error :" + response.toString());
			return Response.status(HttpStatus.SC_UNAUTHORIZED).entity(response.toString()).build();
		}
		
		TripAPIValidationResponse verifyResponse = paramValidator.verifyModifyTripAssignRequest(tripRequestDto, userPrincipal.getUser());

		if (!verifyResponse.isValid()) {
			response.put("message", verifyResponse.getError().get("message"));
			response.put("success", false);
			response.put("responseCode", HttpStatus.SC_BAD_REQUEST);

			log.error("Error :" + verifyResponse.getError().toString());
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(response.toString()).build();
		}
		
		Optional<Boolean> isUpdated = tripRepository.update(tripRequestDto.getTripId(), verifyResponse.getFieldMap());
		
		response.put("success", isUpdated);
		response.put("message", "Transporter assigned successfully");
		
		return Response.status(HttpStatus.SC_OK).entity(response.toString()).build();
	}
	
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTripDetails(@QueryParam("tripId") BigInteger tripId) {

		JSONObject response = new JSONObject();
		
		BasicCacheUserPrincipal userPrincipal = (BasicCacheUserPrincipal) securityContext.getUserPrincipal();

		if (userPrincipal == null || userPrincipal.getUser() == null) {
			response.put("message", "Unauthorized");
			response.put("success", Boolean.FALSE);
			response.put("responseCode", HttpStatus.SC_UNAUTHORIZED);
			log.error("Error :" + response.toString());
			return Response.status(HttpStatus.SC_UNAUTHORIZED).entity(response.toString()).build();
		}
		
		TripAPIValidationResponse verifyResponse = paramValidator.verifyTripDetailsRequest(tripId, userPrincipal.getUser());

		if (!verifyResponse.isValid()) {
			response.put("message", verifyResponse.getError().get("message"));
			response.put("success", false);
			response.put("responseCode", HttpStatus.SC_BAD_REQUEST);

			log.error("Error :" + verifyResponse.getError().toString());
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(response.toString()).build();
		}
		
		Optional<TripDto> tripDto = tripRepository.findDtoByTripIdUserId(userPrincipal.getUser().getId(), verifyResponse.getTripId());
		
		response.put("data", new JSONObject(tripDto.get()));
		response.put("success", true);
		response.put("message", "data found successfully");
		
		return Response.status(HttpStatus.SC_OK).entity(response.toString()).build();
	}
	
}

