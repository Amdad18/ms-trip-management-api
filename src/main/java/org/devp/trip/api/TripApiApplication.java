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
	"org.devp.trip.model",
	"org.devp.trip.api.dto", 
	"org.devp.trip.api.util",
	"org.devp.trip.api.repository",
	"org.devp.trip.api.repository.impl",
	"org.devp.trip.api.cache",
	"org.devp.trip.api.shared",
	"org.devp.trip.api.validation",
	"org.devp.trip.api.controller",
	"org.devp.trip.api",
})
@Slf4j
public class TripApiApplication{

	public static void main(String[] args) {

		SpringApplication.run(TripApiApplication.class, args);
		 
	}
	
}










