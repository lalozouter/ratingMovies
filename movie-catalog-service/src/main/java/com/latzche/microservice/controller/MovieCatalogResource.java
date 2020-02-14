package com.latzche.microservice.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.latzche.microservice.model.CatalogItem;
import com.latzche.microservice.model.Rating;
import com.latzche.microservice.services.MovieInfo;
import com.latzche.microservice.services.UserRatingInfo;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(MovieCatalogResource.class);
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		LOG.info("getting catalog from user "+userId);
		List<Rating> ratings= userRatingInfo.getUserRating(userId).getRatings();
		return ratings.stream().map( rating -> movieInfo.getCatalogItem(rating) )
				.collect(Collectors.toList()); 
	}
	
	
}


/*
 		//URL Hard-coded invocation 
		//List<Rating> ratings= restTemplate.getForObject("http://localhost:8082/ratingsdata/user/"+userId, UserRating.class).getRatings();
		//eureka discover service invocation 
 */


/* reactive call web service implementation
Movie movie=webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/movies/"+rating.getMovieId())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
*/      