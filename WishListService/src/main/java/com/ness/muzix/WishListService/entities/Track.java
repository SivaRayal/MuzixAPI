package com.ness.muzix.WishListService.entities;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track implements Serializable{
	private static final long serialVersionID=1L;
	private String trackName;
	private String artist;
	private String url;
	private String image;
}
