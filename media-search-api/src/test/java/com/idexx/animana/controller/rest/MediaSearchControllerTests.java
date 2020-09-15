package com.idexx.animana.controller.rest;

//import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.idexx.animana.domain.Media;

@RunWith(SpringRunner.class)
//Starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//Replace above with this one to test what happens when iTunes Store API is not available - Normally these would be separate tests
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.application.client.iTunes.baseUrl=https://itunes-wrong.apple.com"})
//Replace above with this one to test what happens when iTunes Store API and Google API are not available
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.application.client.iTunes.baseUrl=https://itunes-wrong.apple.com", "spring.application.client.google.baseUrl=https://google-wrong.apple.com"})
public class MediaSearchControllerTests {

	@Autowired
	private WebTestClient webTestClient;
	
//	@Autowired
//	private ApplicationProperties applicationProperties;

	@Test
	public void searchTest() {
		webTestClient
				.get().uri("/api/search?searchTerm=world")
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.TEXT_EVENT_STREAM_VALUE)
				.expectBodyList(Media.class);
				//Uncomment to test the size of the returned result
//				.hasSize(applicationProperties.getClient().getGoogle().getMaxResults() + applicationProperties.getClient().getiTunes().getMaxResults())
				//Uncomment to test the content of the returned results
//				.consumeWith(mediaRecords ->
//						assertThat(mediaRecords.getResponseBody())
//								.allSatisfy(media -> assertThat(media.getTitle())));
	}
	
}