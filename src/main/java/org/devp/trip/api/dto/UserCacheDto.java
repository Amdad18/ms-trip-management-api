package org.devp.trip.api.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 
 */
@Getter
@Setter
@ToString
public class UserCacheDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger id;

	private String address;

	private String apiKey;
	
	private String companyName;

	private Date createdAt;

	private String email; 
	
	private String fullName;
	
	private String firstName;
	
	private String lastName;

	private String logo;
	
	private String minimizedLogo;

	private String name;

	private String password;

	private String phone;

	private String profileImage;

	private Byte status;
	
	private Date updatedAt;

	private String url;

	private String userName;
	
	public UserCacheDto() {
		
	}
	
	public UserCacheDto(BigInteger id) {
		this.id = id;
	}
	
	public static void main(String args[]) {
		
		JSONObject testJson = new JSONObject();
		testJson.put("hi", JSONObject.NULL);
		System.out.println(testJson.toString());
		
	}

}