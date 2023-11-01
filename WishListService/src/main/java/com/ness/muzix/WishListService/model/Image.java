package com.ness.muzix.WishListService.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Image implements Serializable{
	private static final long serialVersionID=5L;
	@JsonProperty("#text")
	private String text;
	@JsonProperty("size")
	private String size;
}
