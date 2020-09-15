package com.idexx.animana.client.google;

import com.idexx.animana.client.ClientApi;
import com.idexx.animana.domain.Book;

import reactor.core.publisher.Flux;

/**
 * 
 * Non-blocking, reactive client for querying content within the Google Books
 * API.
 * 
 * More over Books API via: <a href=
 * "https://developers.google.com/books/docs/v1/reference/">Google Books API</a>
 * 
 * 
 * @author mlazic
 * @since 1.0
 * 
 */
public interface GoogleClientApi extends ClientApi {

	/**
	 * @param searchTerm
	 *            full-text search query string.
	 * @param maxResults
	 *            maximum number of results to return. Acceptable values are 0 to
	 *            40, inclusive.
	 * @return book records which contain the searchTerm
	 */
	Flux<Book> getBooks(final String searchTerm, final int maxResults);
}
