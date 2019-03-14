package com.galvanize.restaurants.controller;

import com.galvanize.restaurants.model.Restaurant;
import com.galvanize.restaurants.model.Review;
import com.galvanize.restaurants.services.RestaurantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {


    public RestaurantServices rs;

    public RestaurantController(RestaurantServices service){
        this.rs = service;
    }

    @GetMapping("/api/restaurants")
    public List<Restaurant> getAll(){

        return rs.getRestaurantList();
    }

    @PostMapping("/api/restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant newRestaurant){

        return rs.addRestaurantService(newRestaurant);
    }

    @DeleteMapping("/api/restaurants/{id}")
    public List<Restaurant> deleteRestaurant(@PathVariable("id") int id){

        return rs.deleteRestaurantService(id);
    }

    @PutMapping("/api/restaurants/{id}")
    public List<Restaurant> editRestaurant(@PathVariable("id") int id, @RequestBody Restaurant newRestaurant){

        return rs.editRestaurantService(id, newRestaurant);
    }

    @GetMapping("/api/restaurants/{id}/reviews")
    public List<Review> getAllReviews(@PathVariable("id") int id){
        return rs.getAllReviewsService(id);
    }

    @PostMapping("/api/restaurants/{id}/reviews")
    public Review addReview(@PathVariable("id") int id,@RequestBody Review newReview){

        return rs.addReviewService(id, newReview);
    }

}
