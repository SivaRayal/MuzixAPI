package com.ness.muzix.WishListService.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class WhislistRequest implements Serializable{
	private static final long serialVersionID=1L;
	
	@JsonProperty("email")
	private String email;
	@JsonProperty("tracks")
	private List<Track> tracks;
	
}
