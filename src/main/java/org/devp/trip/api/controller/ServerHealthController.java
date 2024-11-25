package org.devp.trip.api.controller;

import javax.inject.Singleton;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 *
 */
@Path("healths")
@Slf4j
@Component
@Singleton
public class ServerHealthController {

	@Context
	private UriInfo info;
	
	@Context
	private HttpHeaders httpHeaders;

	@Context
	private HttpServletRequest servletRequest;
	

	@Path("/online")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response online() {

		JSONObject response = new JSONObject();
		log.info("online");

		response.put("status", true);
		
		return Response.status(HttpStatus.SC_OK).entity(response.toString()).build();
	}

}
