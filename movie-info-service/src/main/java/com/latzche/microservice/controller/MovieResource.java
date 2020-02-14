package com.latzche.microservice.controller;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.latzche.microservice.model.Movie;



@RestController
@RequestMapping("/movies")
public class MovieResource {

	@RequestMapping("/{movieId}")
	public Movie getCatalog(@PathVariable("movieId") String userId){
		
		return new Movie("It","Penny Wise the killer");
		
		
	}
	
}
