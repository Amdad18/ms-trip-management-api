/**
 * 
 */
package org.devp.trip.api;

import lombok.Getter;

/**
 * @author amdad
 */
@Getter
public class ApplicationConfig {

	private ApplicationConfig() {
	}

	public static String getEnvVar(String key, String defaultValue) {

		if (System.getenv(key) != null && !System.getenv(key).isEmpty()) {
			return System.getenv(key);
		}

		return defaultValue;

	}

}
