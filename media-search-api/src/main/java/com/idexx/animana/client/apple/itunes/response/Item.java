package com.idexx.animana.client.apple.itunes.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * Represents one single item retrieved from iTunes Store Search API
 * 
 * @author mlazic
 * @since 1.0
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	private String artistName;
	private String collectionName;

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
