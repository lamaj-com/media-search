package com.idexx.animana;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Value class for type-safe handling configuration properties for Media Search
 * application. These values should be set in application.yml.
 * 
 * @since 1.0
 * @author mlazic
 * 
 */
@ConfigurationProperties(prefix = "spring.application")
@Configuration
public class ApplicationProperties {

	// This property represents application name
	private String name;

	// This property represent origin for which the CORS support will be enabled
	private String corsOrigin; // TODO: make it a list of origins?

	private Client client = new Client();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCorsOrigin() {
		return corsOrigin;
	}

	public Client getClient() {
		return client;
	}

	public class Client {
		private Client() {

		}

		private iTunes iTunes = new iTunes();
		private Google google = new Google();

		public iTunes getiTunes() {
			return iTunes;
		}

		public void setiTunes(iTunes iTunes) {
			this.iTunes = iTunes;
		}

		public Google getGoogle() {
			return google;
		}

		public void setGoogle(Google google) {
			this.google = google;
		}

		public class iTunes {
			private iTunes() {

			}

			public String getBaseUrl() {
				return baseUrl;
			}

			public void setBaseUrl(String baseUrl) {
				this.baseUrl = baseUrl;
			}

			public Integer getMaxConnections() {
				return maxConnections;
			}

			public void setMaxConnections(Integer maxConnections) {
				this.maxConnections = maxConnections;
			}

			public Integer getTimeout() {
				return timeout;
			}

			public void setTimeout(Integer timeout) {
				this.timeout = timeout;
			}

			public int getMaxResults() {
				return maxResults;
			}

			public void setMaxResults(int maxResults) {
				this.maxResults = maxResults;
			}

			private String baseUrl;
			private Integer maxConnections;
			private Integer timeout;
			private int maxResults;
		}

		public class Google {
			private Google() {

			}

			public String getBaseUrl() {
				return baseUrl;
			}

			public void setBaseUrl(String baseUrl) {
				this.baseUrl = baseUrl;
			}

			public Integer getMaxConnections() {
				return maxConnections;
			}

			public void setMaxConnections(Integer maxConnections) {
				this.maxConnections = maxConnections;
			}

			public Integer getTimeout() {
				return timeout;
			}

			public void setTimeout(Integer timeout) {
				this.timeout = timeout;
			}

			public int getMaxResults() {
				return maxResults;
			}

			public void setMaxResults(int maxResults) {
				this.maxResults = maxResults;
			}

			private String baseUrl;
			private Integer maxConnections;
			private Integer timeout;
			private int maxResults;
		}

	}

}
