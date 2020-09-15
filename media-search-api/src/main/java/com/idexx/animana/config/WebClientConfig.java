package com.idexx.animana.config;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.metrics.AutoTimer;
import org.springframework.boot.actuate.metrics.web.reactive.client.DefaultWebClientExchangeTagsProvider;
import org.springframework.boot.actuate.metrics.web.reactive.client.MetricsWebClientFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idexx.animana.ApplicationProperties;

import io.micrometer.core.instrument.MeterRegistry;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

/**
 * 
 * Configure {@link WebClient}s for communication with the external APIs
 * 
 * @author mlazic
 * @since 1.0
 * 
 */
@Configuration
public class WebClientConfig {

	ApplicationProperties applicationProperties;

	MeterRegistry meterRegistry;

	@Autowired
	public WebClientConfig(ApplicationProperties applicationProperties, MeterRegistry meterRegistry) {
		this.applicationProperties = applicationProperties;
		this.meterRegistry = meterRegistry; // creates and manages application's set of meters

	}

	@Bean
	@Qualifier(value = "iTunesWebClient")
	public WebClient iTunesWebClient() {
		HttpClient httpClient = HttpClient
				.create(ConnectionProvider.create("iTunesConnectionProvider",
						applicationProperties.getClient().getiTunes().getMaxConnections())) // set connection pool size
				.tcpConfiguration(client -> client
						.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
								applicationProperties.getClient().getiTunes().getTimeout())
						.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(30))
								.addHandlerLast(new WriteTimeoutHandler(30))));
		// Note: I am not using pre-configured WebClient.Builder, as I want to have
		// separate metrics per upstream service
		return WebClient.builder().baseUrl(applicationProperties.getClient().getiTunes().getBaseUrl())
				// A response from iTunes Store Search API has a Content-Type:"text/javascript;
				// charset=utf-8"
				.codecs(configurer -> {
					configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper(),
							new MimeType("text", "javascript", StandardCharsets.UTF_8)));
					configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper(),
							new MimeType("text", "javascript", StandardCharsets.UTF_8)));
				})
				// configuring metrics for all http requests to iTunes Store
				.filter(new MetricsWebClientFilterFunction(meterRegistry, new DefaultWebClientExchangeTagsProvider(),
						"client.itunes.requests", AutoTimer.ENABLED))
				.clientConnector(new ReactorClientHttpConnector(httpClient)).build();
	}

	@Bean
	@Qualifier(value = "googleWebClient")
	public WebClient googleWebClient() {

		HttpClient httpClient = HttpClient
				.create(ConnectionProvider.create("googleConnectionProvider",
						applicationProperties.getClient().getGoogle().getMaxConnections()))
				.tcpConfiguration(client -> client
						.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
								applicationProperties.getClient().getGoogle().getTimeout())
						.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(30))
								.addHandlerLast(new WriteTimeoutHandler(30))));

		// configuring metrics for all http requests to Google API
		return WebClient.builder().baseUrl(applicationProperties.getClient().getGoogle().getBaseUrl())
				.filter(new MetricsWebClientFilterFunction(meterRegistry, new DefaultWebClientExchangeTagsProvider(),
						"client.google.requests", AutoTimer.ENABLED))
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

}
