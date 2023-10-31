package com.ness.muzix.LastFMService.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResults implements Serializable{
	private static final long serialVersionID=5L;
	@JsonProperty("opensearch:Query")
	private OpensearchQuery opensearch_Query;
	@JsonProperty("opensearch:totalResults")
	private String opensearch_totalResults;
	@JsonProperty("opensearch:startIndex")
	private String opensearch_startIndex;
	@JsonProperty("opensearch:itemsPerPage")
	private String opensearch_itemsPerPage;
	@JsonProperty("artistmatches")
	private Artistmatches artistmatches;
	@JsonProperty("trackmatches")
	private Trackmatches trackmatches;
	@JsonProperty("@attr")
	private Attr attr;
}
