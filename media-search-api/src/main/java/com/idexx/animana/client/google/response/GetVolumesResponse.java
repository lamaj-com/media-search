package com.idexx.animana.client.google.response;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * Response for Google Books API 
 * 
 * @author mlazic
 * @since 1.0
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetVolumesResponse {

	private Long totalItems;
	private List<Item> items = new LinkedList<>();

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalNumbers) {
		this.totalItems = totalNumbers;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}


}
