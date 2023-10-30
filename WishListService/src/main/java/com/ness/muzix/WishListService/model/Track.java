package com.ness.muzix.WishListService.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Track implements Serializable{
	private static final long serialVersionID=1L;
	private String trackName;
	private String artist;
	private String url;
	private String image;
}
