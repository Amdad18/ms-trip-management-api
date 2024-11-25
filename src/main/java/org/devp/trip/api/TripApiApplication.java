package org.devp.trip.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 */
@SpringBootApplication
@ComponentScan({
	"org.devp.restaurant.model",
	"org.devp.restaurant.dto", 
	"org.devp.restaurant.api.util",
	"org.devp.restaurant.api.repository",
	"org.devp.restaurant.api.repository.impl",
	"org.devp.restaurant.api.controller"
})
@Slf4j
public class TripApiApplication{

	public static void main(String[] args) {

		SpringApplication.run(TripApiApplication.class, args);
		 
	}
	
}










