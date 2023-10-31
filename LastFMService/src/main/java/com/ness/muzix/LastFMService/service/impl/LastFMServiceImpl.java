package com.ness.muzix.LastFMService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ness.muzix.LastFMService.config.MuzixAppConfig;
import com.ness.muzix.LastFMService.config.MuzixAppConstants;
import com.ness.muzix.LastFMService.exception.LastFMServiceException;
import com.ness.muzix.LastFMService.model.ArtistSearchresponse;
import com.ness.muzix.LastFMService.model.RecommendationResponse;
import com.ness.muzix.LastFMService.model.TagsResponse;
import com.ness.muzix.LastFMService.model.TitleSearchResponse;
import com.ness.muzix.LastFMService.service.LastFMService;

@Service("LastFMService")
public class LastFMServiceImpl implements LastFMService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MuzixAppConfig appConfig;
	
	@Override
	public TitleSearchResponse titleSearch(String trackName) {
		final String url=appConfig.getTrackSearchURL()+trackName+MuzixAppConstants.LASTFM_API_KEY_FORMAT;
		try{
			TitleSearchResponse titleSearchResponse = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(null,new HttpHeaders()), TitleSearchResponse.class).getBody();
			return titleSearchResponse;
		}catch(Exception e) {
			throw new LastFMServiceException("Title search api failed from last-fm.com");
		}
		
	}

	@Override
	public ArtistSearchresponse artistSearch(String artistName) {
		final String url=appConfig.getArtistSearchURL()+artistName+MuzixAppConstants.LASTFM_API_KEY_FORMAT;
		try{
			ArtistSearchresponse artistSearchResponse = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(null,new HttpHeaders()), ArtistSearchresponse.class).getBody();
			return artistSearchResponse;
		}catch(Exception e) {
			throw new LastFMServiceException("Artist search api failed from last-fm.com");
		}
	}

	@Override
	public TagsResponse getTopTags() {
		final String url=appConfig.getRecommendedTagsURL()+MuzixAppConstants.LASTFM_API_KEY_FORMAT;
		try{
			TagsResponse topTagsResponse = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(null,new HttpHeaders()), TagsResponse.class).getBody();
			return topTagsResponse;
		}catch(Exception e) {
			throw new LastFMServiceException("Top Tags api failed from last-fm.com");
		}
	}

	@Override
	public RecommendationResponse getTopRecommendations(String tag) {
		final String url=appConfig.getRecommendedTracksURL()+tag+MuzixAppConstants.LASTFM_API_KEY_FORMAT;
		try{
			RecommendationResponse recommendationsResponse = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(null,new HttpHeaders()), RecommendationResponse.class).getBody();
			return recommendationsResponse;
		}catch(Exception e) {
			throw new LastFMServiceException("Recommendations on Tags api failed from last-fm.com");
		}
	}
	
}
