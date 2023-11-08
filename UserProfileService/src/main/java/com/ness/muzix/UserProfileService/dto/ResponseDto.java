package com.ness.muzix.UserProfileService.dto;

public class ResponseDto {
	
	private int statusCode;
	
	private String statusMessage;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		return "ResponseDto [statusCode=" + statusCode + ", statusMessage=" + statusMessage + "]";
	}

}
