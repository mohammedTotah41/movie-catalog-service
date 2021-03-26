package com.moviecatalog.moviecatalogservice.resources;

import com.moviecatalog.moviecatalogservice.models.CatalogItem;
import com.moviecatalog.moviecatalogservice.models.Rating;
import com.moviecatalog.moviecatalogservice.models.UserRating;
import com.moviecatalog.moviecatalogservice.services.MovieInfoService;
import com.moviecatalog.moviecatalogservice.services.UserRatingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private UserRatingInfoService userRatingInfoService;


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        UserRating userRating = userRatingInfoService.getUserRatings(userId);
        List<Rating> ratings = userRating.getRatings();
        return  ratings.stream().map(rating -> movieInfoService.getCatalogItem(rating) )
                                .collect(Collectors.toList());
    }

    /*  Movie movie  = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/
}
