package com.idexx.animana.service;

import com.idexx.animana.domain.Media;

import reactor.core.publisher.Flux;

/**
 * A service for different type of media (books, albums, etc.) search
 *
 * @author mlazic
 * @since 1.0
 */
public interface MediaSearchService {

	/**
	 * Search media (books, albums, etc.) based on a given term.
	 * @param searchTerm text string based on which you want to search media for
	 * @return stream of media that contains given term
	 *
	 */
	public Flux<Media> search(String searchTerm);
}
