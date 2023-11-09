package com.ness.muzix.AuthorizationService.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name = "USER_PROFILE")
@Data
public class UserProfile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMAIL", nullable = false)
	private String userEmail;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;
}
