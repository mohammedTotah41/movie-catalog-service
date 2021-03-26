package com.moviecatalog.moviecatalogservice.services;

import com.moviecatalog.moviecatalogservice.models.CatalogItem;
import com.moviecatalog.moviecatalogservice.models.Movie;
import com.moviecatalog.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getCatalogItemFallBack")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(),Movie.class);
        return new CatalogItem(movie.getName() , movie.getDescription(), rating.getRating());
    }

    public CatalogItem getCatalogItemFallBack(Rating rating) {
        return new CatalogItem("Movie Not Found" , "no Description",rating.getRating());
    }

}
