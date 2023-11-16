package com.ness.muzix.UserProfileService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserProfileDto {
	@JsonProperty("UserEmail")
	private String userEmail;

	@JsonProperty("OldPassword")
	private String oldPassword;

	@JsonProperty("NewPassword")
	private String newPassword;
}
