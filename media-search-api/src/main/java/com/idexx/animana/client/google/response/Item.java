package com.idexx.animana.client.google.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * Represents one single item retrieved from Google Volumes API
 * 
 * @author mlazic
 * @since 1.0
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	private VolumeInfo volumeInfo;

	public VolumeInfo getVolumeInfo() {
		return volumeInfo;
	}

	public void setVolumeInfo(VolumeInfo volumeInfo) {
		this.volumeInfo = volumeInfo;
	}

	public static class VolumeInfo {
		private String title;

		private List<String> authors;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public List<String> getAuthors() {
			return authors;
		}

		public void setAuthors(List<String> authors) {
			this.authors = authors;
		}
	}

}
