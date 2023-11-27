package com.ness.muzix.WishListService.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class WhislistRequest implements Serializable{
	private static final long serialVersionID=1L;
	
	@JsonProperty("email")
	private String email;
	@JsonProperty("tracks")
	private List<Track> tracks;
}
