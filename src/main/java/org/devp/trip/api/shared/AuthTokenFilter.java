package org.devp.trip.api.shared;


import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.List;

import org.devp.trip.api.cache.UserCacheService;
import org.devp.trip.api.dto.UserCacheDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * Created By:amdad
 */

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
@Slf4j
@Component
public class AuthTokenFilter implements ContainerRequestFilter{
	
	@Context
    private ResourceInfo resourceInfo;
	
	@Autowired
	private UserCacheService userCacheService;
     
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    
	public AuthTokenFilter() {
		
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext)throws IOException {
		Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if(method.isAnnotationPresent(PermitAll.class)){
        	log.debug("Method PermitAll"); 
        	return;
        }
		
		//Access denied for all
        if(method.isAnnotationPresent(DenyAll.class)){
        	log.debug("Method Access blocked for all users !!");
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build());
            return;
        }
		
        //Get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
        
        //Fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
        
        //If no authorization information present; block access
        if(authorization == null || authorization.isEmpty()){
        	log.error("AUTHORIZATION_PROPERTY is empty");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
            return;
        }
        
        //Get authToken
        String authToken = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME, "");
        
        log.info("authToken : "+authToken);
        System.out.println("authToken : "+authToken);
        
        //If no authorization information present; block access
        if(authToken == null || authToken.trim().isEmpty()){
        	log.error("authToken is empty");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
            return;
        }
        
        authToken = authToken.trim();
        
        log.debug("authToken : "+authToken);
        
        BasicCacheUserPrincipal principal = collectAuthInfo(authToken);
		if (principal.getUser() == null) {
			log.error("no user found with authtoken : "+authToken);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
            return;
		}
		
		final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		requestContext.setSecurityContext(new SecurityContext() {

	        @Override
	        public Principal getUserPrincipal() {
	            return principal;
	        }

		    @Override
		    public boolean isUserInRole(String role) {
		        return true;
		    }

		    @Override
		    public boolean isSecure() {
		        return currentSecurityContext.isSecure();
		    }

		    @Override
		    public String getAuthenticationScheme() {
		        return "Bearer Token";
		    }
		});
  
	}
	
	private BasicCacheUserPrincipal collectAuthInfo(String authToken) {
		UserCacheDto userCacheDto = loadAuthInfoFromCache(authToken);
		return new BasicCacheUserPrincipal(userCacheDto);
	}
	
	private UserCacheDto loadAuthInfoFromCache(String authToken) {
		
		UserCacheDto userCacheDto = userCacheService.findByAuthToken(authToken);
		
		return userCacheDto;
	}


}