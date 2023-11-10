package com.ness.muzix.UserProfileService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserProfileResponse implements Serializable {
    private static final long serialVersionID=1L;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("UserEmail")
    private String userEmail;

    @JsonProperty("ContactNumber")
    private String contactNumber;

}
