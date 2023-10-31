package com.ness.muzix.LastFMService.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Attr implements Serializable{
	private static final long serialVersionID=5L;
	@JsonProperty("rank")
	private String rank;
	@JsonProperty("tag")
	private String tag;
	@JsonProperty("page")
	private String page;
	@JsonProperty("perPage")
	private String perPage;
	@JsonProperty("totalPages")
	private String totalPages;
    //private String total;
	@JsonProperty("offset")
	private int offset;
	@JsonProperty("num_res")
	private int num_res;
    //private int total;
	@JsonProperty("for") 
	private String myfor;
}
