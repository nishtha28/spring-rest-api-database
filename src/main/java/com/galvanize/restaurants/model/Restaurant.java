package com.galvanize.restaurants.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

      String name;
      int id;
    List<Review> reviews = new ArrayList<>();


    public Restaurant(){

    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
