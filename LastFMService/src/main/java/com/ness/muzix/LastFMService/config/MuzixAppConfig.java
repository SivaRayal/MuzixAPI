package com.ness.muzix.LastFMService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class MuzixAppConfig {
	
	@Value("${lastfm.track.search}")
	private String trackSearchURL;
	
	@Value("${lastfm.artist.search}")
	private String artistSearchURL;
	
	@Value("${lastfm.recomendedTags}")
	private String recommendedTagsURL;
	
	@Value("${lastfm.recomendedTracks}")
	private String recommendedTracksURL;

	@Value("${access.ip}")
	private String accessIP;
}
