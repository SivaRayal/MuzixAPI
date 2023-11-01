package com.ness.muzix.WishListService.entities;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class WhislistDTO implements Serializable{
	private static final long serialVersionID=1L;
	
	@Id
	private String email;
	@NonNull
	private List<Track> tracks;
	
}
