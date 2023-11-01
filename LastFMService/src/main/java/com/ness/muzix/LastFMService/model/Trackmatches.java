package com.ness.muzix.LastFMService.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Trackmatches implements Serializable{
	private static final long serialVersionID=5L;
	@JsonProperty("track")
	private ArrayList<TitleTrack> track;
}
