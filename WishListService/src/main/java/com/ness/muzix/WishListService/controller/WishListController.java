package com.ness.muzix.WishListService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
	
	@GetMapping("/getFavorites/{userEmail}")
	public ResponseEntity<?> getFavorites(@RequestParam String userEmail){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/updateFavorites/{}")
	public ResponseEntity<?> updateFavorites(){
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
