package com.ness.muzix.UserProfileService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseDto {
	@JsonProperty("StatusCode")
	private int statusCode;

	@JsonProperty("StatusMessage")
	private String statusMessage;

}
