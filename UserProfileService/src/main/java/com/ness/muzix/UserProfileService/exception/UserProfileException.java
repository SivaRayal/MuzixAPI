package com.ness.muzix.UserProfileService.exception;

public class UserProfileException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;
	private String description;
	
	public UserProfileException(int statusCode, String description) {
		super();
		this.statusCode = statusCode;
		this.description = description;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UserProfileException [statusCode=" + statusCode + ", description=" + description + "]";
	}

	

}
