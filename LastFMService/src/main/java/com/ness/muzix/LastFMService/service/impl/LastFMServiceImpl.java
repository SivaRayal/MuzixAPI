package com.ness.muzix.LastFMService.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("LastFMService")
public class LastFMServiceImpl implements LastFMService{
	
	private static final Logger log = LoggerFactory.getLogger(LastFMServiceImpl.class);
	
	
	// @Autowired
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	MuzixAppConfig appConfig;
	
	@Override
	public TitleSearchResponse titleSearch(String trackName) {
		final String url=appConfig.getTrackSearchURL()+trackName+MuzixAppConstants.LASTFM_API_KEY_FORMAT;
		log.info("URL - "+url);
		try{
			TitleSearchResponse titleSearchResponse = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(null,null), TitleSearchResponse.class).getBody();
			return titleSearchResponse;
		}catch(Exception e) {
			e.printStackTrace();
			log.info(e.toString());
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
		log.info("URL - "+url);
		try{
			TagsResponse topTagsResponse = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(null,null), TagsResponse.class).getBody();
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
