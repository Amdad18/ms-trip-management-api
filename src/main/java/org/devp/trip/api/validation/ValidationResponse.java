/**
 * 
 */
package org.devp.trip.api.validation;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * CreatedBy amdad
 *
 */

@Log4j2
@Getter
@Setter
public abstract class ValidationResponse {

	private boolean valid;

	protected JSONObject error;

	public ValidationResponse() {
		this.valid = false;
		this.error = new JSONObject();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info(""); 
	}
	
}
