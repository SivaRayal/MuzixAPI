package com.ness.muzix.WishListService.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class WhislistDTO implements Serializable{
	private static final long serialVersionID=1L;
	
	@Id
	private String email;
	@NonNull
	private List<Track> tracks;
	
}
