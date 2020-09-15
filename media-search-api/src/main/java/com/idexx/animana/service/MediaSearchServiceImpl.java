package com.idexx.animana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idexx.animana.ApplicationProperties;
import com.idexx.animana.client.apple.itunes.ITunesClientApi;
import com.idexx.animana.client.google.GoogleClientApi;
import com.idexx.animana.domain.Media;
import com.idexx.animana.domain.MediaType;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class MediaSearchServiceImpl implements MediaSearchService {

	private ITunesClientApi iTunesClientApi;

	private GoogleClientApi googleClientApi;

	private ApplicationProperties applicationProperties;

	@Autowired
	public MediaSearchServiceImpl(ITunesClientApi iTunesClientApi, GoogleClientApi googleClientApi,
			ApplicationProperties applicationProperties) {
		this.iTunesClientApi = iTunesClientApi;
		this.googleClientApi = googleClientApi;
		this.applicationProperties = applicationProperties;

	}

	public Flux<Media> search(final String searchTerm) {
		Flux<Media> books = googleClientApi
				.getBooks(searchTerm, applicationProperties.getClient().getGoogle().getMaxResults())
				.map(book -> new Media(book.getAuthors(), book.getTitle(), MediaType.BOOK))
				//Specify what kind of Scheduler to use when the subscribe call happens. 
				//We're using the elastic scheduler which ensures each subscription happens on a dedicated single thread
				.subscribeOn(Schedulers.elastic());
		Flux<Media> albums = iTunesClientApi
				.getAlbums(searchTerm, applicationProperties.getClient().getGoogle().getMaxResults())
				.map(album -> new Media(album.getArtistName(), album.getCollectionName(), MediaType.ALBUM)).subscribeOn(Schedulers.elastic());
		return Flux.merge(books, albums).parallel().runOn(Schedulers.elastic())
				.sorted((m1, m2) -> m1.getTitle().compareTo(m2.getTitle()));

	}

}
