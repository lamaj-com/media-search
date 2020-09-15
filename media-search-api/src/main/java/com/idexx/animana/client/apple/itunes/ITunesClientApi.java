package com.idexx.animana.client.apple.itunes;

import com.idexx.animana.client.ClientApi;
import com.idexx.animana.domain.Album;

import reactor.core.publisher.Flux;

/**
 * 
 * Non-blocking, reactive client for querying content within the iTunes Store.
 * 
 * More over Search API via: <a href=
 * "https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/">iTunes
 * Search API</a>
 * 
 * 
 * @author mlazic
 * @since 1.0
 * 
 */
public interface ITunesClientApi extends ClientApi {
	/**
	 * @param searchTerm
	 *            the URL-encoded text string you want to search for
	 * @param limit
	 *            the number of search results you want the iTunes Store to return
	 * @return album records which contain the searchTerm
	 */
	Flux<Album> getAlbums(final String searchTerm, final int limit);

}
