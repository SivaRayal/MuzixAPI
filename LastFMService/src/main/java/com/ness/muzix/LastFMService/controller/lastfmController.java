package com.ness.muzix.LastFMService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ness.muzix.LastFMService.service.LastFMService;

@RestController
@RequestMapping("/lastfm")
public class lastfmController {
	@Autowired
	LastFMService lastfmService;
	
	@GetMapping("/searchByTitle/{title}")
	public ResponseEntity<?> getTitleSearch(@PathVariable String title){
		return new ResponseEntity<>(lastfmService.titleSearch(title), HttpStatus.OK);
	}
	
	@GetMapping("/searchByArtist/{artist}")
	public ResponseEntity<?> getArtistSearch(@PathVariable String artist){
		return new ResponseEntity<>(lastfmService.artistSearch(artist) , HttpStatus.OK);
	}
	
	@GetMapping("/recommendedTags")
	public ResponseEntity<?> getRecommendedTag(){
		return new ResponseEntity<>(lastfmService.getTopTags(),HttpStatus.OK);
	}
	
	@GetMapping(path="/recommendedTracks",params="tag")
	public ResponseEntity<?> getRecommendedTracks(@RequestParam String tag){
		return new ResponseEntity<>(lastfmService.getTopRecommendations(tag) , HttpStatus.OK);
	}
}
