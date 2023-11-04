package com.ness.muzix.AuthorizationService.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="USER_INFO")
public class UserCredentailsDTO implements Serializable{
    public static final long serialVersionID=1L;

    @Id
    @JsonProperty("UserEmail")
    @Column(name="USER_EMAIL")
    private String userEmail;

    @Column(name="USER_PWD")
    private String password;
}
