package com.idexx.animana.domain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Media {

	private List<String> authors;
	
	private String title;
	
	private MediaType mediaType;

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}
	
	

	public Media() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Media(List<String> authors, String title, MediaType mediaType) {
		super();
		this.authors = authors;
		this.title = title;
		this.mediaType = mediaType;
	}
	
	public Media(String authors, String title, MediaType mediaType) {
		super();
		this.authors = new LinkedList<String>(Arrays.asList(authors));
		this.title = title;
		this.mediaType = mediaType;
	}
		

}
