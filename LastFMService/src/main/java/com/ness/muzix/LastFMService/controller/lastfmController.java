package com.ness.muzix.LastFMService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lastfm")
public class lastfmController {
	@GetMapping("/searchByTitle/{title}")
	public ResponseEntity<?> getTitleSearch(@RequestParam String title){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/searchByArtist/{artist}")
	public ResponseEntity<?> getArtistSearch(@RequestParam String artist){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/recommendedTags")
	public ResponseEntity<?> getRecommendedTag(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/recommendedTracks/{tag}")
	public ResponseEntity<?> getRecommendedTracks(@RequestParam String tag){
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
