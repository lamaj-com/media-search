package com.idexx.animana.client.apple.itunes.response;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * Response for iTunes Store Search API 
 * 
 * @author mlazic
 * @since 1.0
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchContentResponse {
	
	private Long resultCount;
	
	private List<Item> results = new LinkedList<>();

	public Long getResultCount() {
		return resultCount;
	}

	public void setResultCount(Long resultCount) {
		this.resultCount = resultCount;
	}

	public List<Item> getResults() {
		return results;
	}

	public void setResults(List<Item> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetAlbumResponse [resultCount=");
		builder.append(resultCount);
		builder.append(", results=");
		builder.append(results);
		builder.append("]");
		return builder.toString();
	}
	
	

}
