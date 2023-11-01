package com.ness.muzix.LastFMService.service;

import com.ness.muzix.LastFMService.model.ArtistSearchresponse;
import com.ness.muzix.LastFMService.model.RecommendationResponse;
import com.ness.muzix.LastFMService.model.TagsResponse;
import com.ness.muzix.LastFMService.model.TitleSearchResponse;

public interface LastFMService {
	TitleSearchResponse titleSearch(String trackName);
	ArtistSearchresponse artistSearch(String artistName);
	TagsResponse getTopTags();
	RecommendationResponse getTopRecommendations(String tag);
}
