package com.ness.muzix.LastFMService.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Streamable implements Serializable{
	private static final long serialVersionID=5L;
	@JsonProperty("#text")
	private String text;
	@JsonProperty("fulltrack")
	private String fulltrack;
}

