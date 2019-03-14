package com.galvanize.restaurants.services;

import com.galvanize.restaurants.model.Restaurant;
import com.galvanize.restaurants.model.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantServices {

    private static int review_id;
    private static int id;

    List<Restaurant> restaurantList = new ArrayList<>();

    public List<Restaurant> getRestaurantList(){
        return restaurantList;
    }

    public void resetList() {
        restaurantList = new ArrayList<>();
        id = 0;
        review_id = 0;
    }


    public Restaurant addRestaurantService( Restaurant newRestaurant){
        newRestaurant.setId(id++);
        restaurantList.add(newRestaurant);
        return newRestaurant;
    }


    public List<Restaurant> deleteRestaurantService( int id){

        restaurantList.remove(id);
        return restaurantList;
    }


    public List<Review> getAllReviewsService( int id){

        Restaurant r = restaurantList.get(id);
        return r.getReviews();
    }


    public Review addReviewService(int id, Review newReview) {
        newReview.setId(review_id++);
        Restaurant r = restaurantList.get(id);
        List<Review> reviewList =r.getReviews();
        reviewList.add(newReview);
        r.setReviews(reviewList);
        restaurantList.set(id,r);
        return newReview;
    }


    public List<Restaurant> editRestaurantService(int id, Restaurant newRestaurant){

        restaurantList.set(id,newRestaurant);
        return restaurantList;
    }

}
