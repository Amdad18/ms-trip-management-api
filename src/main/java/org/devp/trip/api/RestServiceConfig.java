/**
 * 
 */
package org.devp.trip.api;


import org.devp.trip.api.controller.ServerHealthController;
import org.devp.trip.api.controller.TripController;
import org.devp.trip.api.shared.AuthTokenFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import jakarta.ws.rs.ApplicationPath;
import lombok.extern.slf4j.Slf4j;


/**
 * @author amdad
 */
@Configuration
@Slf4j
@ApplicationPath("/v1")
public class RestServiceConfig extends ResourceConfig {

	    
    public RestServiceConfig() {
    	register(ClientAbortExceptionInterceptor.class);
    	register(ServerHealthController.class);
    	register(AuthTokenFilter.class);
    	register(TripController.class);
		
		log.info("RestServiceConfig"); 
    }
}