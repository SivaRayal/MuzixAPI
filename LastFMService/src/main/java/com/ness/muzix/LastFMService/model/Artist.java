package com.ness.muzix.LastFMService.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Artist implements Serializable{
	private static final long serialVersionID=1L;
	@JsonProperty("name")
	private String name;
	@JsonProperty("listeners")
	private String listeners;
	@JsonProperty("mbid")
	private String mbid;
	@JsonProperty("url")
	private String url;
	@JsonProperty("streamable")
	private String streamable;
	@JsonProperty("image")
	private ArrayList<Image> image;
}
