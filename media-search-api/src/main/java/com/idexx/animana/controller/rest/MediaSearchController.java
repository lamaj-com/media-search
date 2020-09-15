package com.idexx.animana.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idexx.animana.Constants;
import com.idexx.animana.domain.Media;
import com.idexx.animana.service.MediaSearchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(Constants.API_ROOT)
public class MediaSearchController {

	MediaSearchService searchService;

	public MediaSearchController(@Autowired MediaSearchService searchService) {
		this.searchService = searchService;

	}

	@GetMapping(path = "/search", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ApiOperation(value = "Searches media based on a given term", response = Flux.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Stream of media which matches the given search term") })
	public Flux<Media> search(
			@ApiParam(value = "searchTerm", required = false) @RequestParam(value = "searchTerm", required = true) String searchTerm) {
		return searchService.search(searchTerm);
	}

}
