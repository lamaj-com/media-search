package com.idexx.animana.client.apple.itunes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.idexx.animana.client.apple.itunes.response.SearchContentResponse;
import com.idexx.animana.domain.Album;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * 
 * @author mlazic
 * @since 1.0
 *
 */
@Component
public class ITunesClientApiImpl implements ITunesClientApi {

	WebClient iTunesWebClient;

	private static Logger LOGGER = LoggerFactory.getLogger(ITunesClientApiImpl.class);

	public ITunesClientApiImpl(@Autowired WebClient iTunesWebClient) {
		this.iTunesWebClient = iTunesWebClient;
	}

	@Override
	public Flux<Album> getAlbums(final String searchTerm, final int limit) {
		return iTunesWebClient.get()
				.uri(uriBuilder -> uriBuilder.path("/search").queryParam("term", "{searchTerm}")
						.queryParam("limit", "{limit}")
						// TODO: Make this configurable
						.queryParam("media", "{media}").queryParam("entity", "{entity}")
						.build(searchTerm, limit, "music", "album"))
				.retrieve().bodyToMono(SearchContentResponse.class)
				.doOnError(err -> LOGGER.error("Error while calling iTunes Search API. ", err))
				// If the service returns error, return new {@link GetAlbumResponse} object and
				// continue
				.onErrorReturn(new SearchContentResponse()).flatMapIterable(response -> response.getResults())
				.map(item -> new Album(item.getArtistName(), item.getCollectionName()));
	}

	public Mono<Health> isRunning() {
		return iTunesWebClient.get().uri(uriBuilder -> uriBuilder.path("/search").build()).retrieve()
				.bodyToMono(JsonNode.class).map(r -> new Health.Builder().up().build())
				// Provide information about the exception to the consumer
				.onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()));
	}

}
