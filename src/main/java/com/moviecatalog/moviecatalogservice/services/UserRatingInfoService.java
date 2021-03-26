package com.moviecatalog.moviecatalogservice.services;

import com.moviecatalog.moviecatalogservice.models.Rating;
import com.moviecatalog.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getUserRatingsFallBack")
    public UserRating getUserRatings(String userId) {
        return restTemplate.getForObject("http://rating-data-service/ratingData/user/" + userId, UserRating.class);
    }

    public UserRating getUserRatingsFallBack(String userId) {
        return new UserRating(0 , Arrays.asList(new Rating("0" ,0)));
    }

}
