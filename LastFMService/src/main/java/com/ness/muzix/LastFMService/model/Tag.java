package com.ness.muzix.LastFMService.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Tag implements Serializable{
	private static final long serialVersionID=5L;
	@JsonProperty("name")
	private String name;
	@JsonProperty("count")
	private int count;
	@JsonProperty("reach")
	private int reach;
}
