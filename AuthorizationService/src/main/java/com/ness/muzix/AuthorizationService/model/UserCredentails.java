package com.ness.muzix.AuthorizationService.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserCredentails implements Serializable{
    private static final long serialVersionID=1L;

    @JsonProperty("UserEmail")
    private String userEmail;

    @JsonProperty("Password")
    @NonNull
    private String password;
}
