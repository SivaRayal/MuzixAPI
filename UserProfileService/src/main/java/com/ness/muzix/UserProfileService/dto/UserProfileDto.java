package com.ness.muzix.UserProfileService.dto;

public class UserProfileDto {
	
	private String userEmail;
	private String oldPassword;
	private String newPassword;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "UserProfileDto [userEmail=" + userEmail + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + "]";
	}
}
