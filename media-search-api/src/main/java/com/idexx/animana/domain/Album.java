package com.idexx.animana.domain;

public class Album {

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

	public Album(String artistName, String collectionName) {
		super();
		this.artistName = artistName;
		this.collectionName = collectionName;
	}
	
	

}
