package com.idexx.animana.client.google;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.idexx.animana.client.google.response.GetVolumesResponse;
import com.idexx.animana.domain.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GoogleClientApiImpl implements GoogleClientApi {

	private WebClient googleWebClient;

	public GoogleClientApiImpl(@Autowired WebClient googleWebClient) {
		this.googleWebClient = googleWebClient;

	}

	private static Logger LOGGER = LoggerFactory.getLogger(GoogleClientApiImpl.class);

	@Override
	public Flux<Book> getBooks(String searchTerm, int maxResults) {
		Flux<Book> books = googleWebClient.get()
				.uri(uriBuilder -> uriBuilder.path("/books").path("/v1").path("/volumes").queryParam("q", searchTerm)
						.queryParam("printType", "BOOKS").queryParam("maxResults", maxResults)
						/*
						 * By default, the server sends back the full representation of a resource after
						 * processing requests. For better performance, we are asking the server to send
						 * only the fields we need and get a partial response instead.
						 */
						.queryParam("fields", "totalItems,items(volumeInfo(title,authors))").build())
				.retrieve().bodyToMono(GetVolumesResponse.class)
				.doOnError(err -> LOGGER.error("Error while calling Google Book API. ", err))
				.onErrorResume(throwable -> Mono.empty()).flatMapIterable(response -> response.getItems())
				.map(item -> item.getVolumeInfo() == null ? null
						: new Book(item.getVolumeInfo().getTitle(), item.getVolumeInfo().getAuthors()));
		return books;

	}

	public Mono<Health> isRunning() {
		return googleWebClient.get()
				.uri(uriBuilder -> uriBuilder.path("/books").path("/v1").path("/volumes").queryParam("q", "{q}")
						.queryParam("printType", "{printType}").queryParam("maxResults", "{maxResults}")
						.queryParam("fields", "{fields}")
						.build("The Inhabited Island", "BOOKS", 1, "totalItems,items(volumeInfo(title,authors))"))
				.retrieve().bodyToMono(JsonNode.class).map(r -> new Health.Builder().up().build())
				.onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()));
	}

}
