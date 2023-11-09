package com.ness.muzix.WishListService.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Track implements Serializable{
	private static final long serialVersionID=1L;
	@JsonProperty("trackName")
	private String trackName;
	@JsonProperty("artist")
	private String artist;
	@JsonProperty("url")
	private String url;
	@JsonProperty("image")
	private String image;
}
