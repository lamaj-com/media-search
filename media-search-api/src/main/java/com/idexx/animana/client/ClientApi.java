package com.idexx.animana.client;

import org.springframework.boot.actuate.health.Health;

import reactor.core.publisher.Mono;

/**
 * 
 * Non-blocking, reactive client for querying content from third-party APIs
 * 
 * 
 * 
 * @author mlazic
 * @since 1.0
 * 
 */
public interface ClientApi {

	/**
	 * Checks whether the iTunes Search API is available
	 * 
	 * @return true if Search API is available, false otherwise
	 */
	Mono<Health> isRunning();
}
