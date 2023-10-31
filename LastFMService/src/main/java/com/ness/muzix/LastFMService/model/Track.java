package com.ness.muzix.LastFMService.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Track implements Serializable{
	private static final long serialVersionID=5L;
	@JsonProperty("name")
	private String name;
	@JsonProperty("duration")
	private String duration;
	@JsonProperty("mbid")
	private String mbid;
	@JsonProperty("url")
	private String url;
	@JsonProperty("streamable")
	private Streamable streamable;
	@JsonProperty("artist")
	private Artist artist;
	@JsonProperty("image")
	private ArrayList<Image> image;
	@JsonProperty("listeners")
	private String listeners;
	@JsonProperty("@attr")
	private Attr attr;
}
