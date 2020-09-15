package com.idexx.animana.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

import com.idexx.animana.client.apple.itunes.ITunesClientApi;

import reactor.core.publisher.Mono;

/**
 * A {@link ReactiveHealthIndicator} for iTunes Store API.
 *
 * @author mlazic
 * @since 1.0
 */
@Component
public class ITunesApiHealthIndicator implements ReactiveHealthIndicator {

	private ITunesClientApi iTunesClient;

	public ITunesApiHealthIndicator(@Autowired ITunesClientApi iTunesClient) {
		this.iTunesClient = iTunesClient;
	}

	@Override
	public Mono<Health> health() {
		return checkITunesSearchApiHealth();
	}

	private Mono<Health> checkITunesSearchApiHealth() {
		Mono<Health> isRunning = iTunesClient.isRunning();
		return isRunning;

	}
}