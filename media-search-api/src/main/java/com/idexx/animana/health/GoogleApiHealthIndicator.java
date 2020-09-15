package com.idexx.animana.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

import com.idexx.animana.client.google.GoogleClientApi;

import reactor.core.publisher.Mono;

/**
 * A {@link ReactiveHealthIndicator} for Google API.
 *
 * @author mlazic
 * @since 1.0
 */
@Component
public class GoogleApiHealthIndicator implements ReactiveHealthIndicator {

	private GoogleClientApi googleClient;

	public GoogleApiHealthIndicator(@Autowired GoogleClientApi googleClient) {
		this.googleClient = googleClient;
	}

	@Override
	public Mono<Health> health() {
		return checkGoogleBooksApiHealth().onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()));
	}

	private Mono<Health> checkGoogleBooksApiHealth() {
		Mono<Health> isRunning = googleClient.isRunning();
		return isRunning;

	}
}