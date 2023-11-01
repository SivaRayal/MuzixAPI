package com.ness.muzix.WishListService.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable{
	private static final long serialVersionID=5L;
	private String text;
	private String size;
}
