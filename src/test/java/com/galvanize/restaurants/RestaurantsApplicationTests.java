package com.galvanize.restaurants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.restaurants.controller.RestaurantController;
import com.galvanize.restaurants.model.Restaurant;
import com.galvanize.restaurants.model.Review;
import com.galvanize.restaurants.services.RestaurantServices;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantsApplicationTests {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantServices rs;

    @After
    public void tearDown() {
        rs.resetList();
    }

    @Test
    public void testGetAll() throws Exception {
        Restaurant test1 = new Restaurant();
        test1.setName("test1");

        List<Restaurant> expectedList = new ArrayList<>();
        expectedList.add(test1);

        String jsonRes = mapper.writeValueAsString(expectedList);
        rs.addRestaurantService(test1);

        this.mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonRes));
    }

    @Test
    public void testAddRestaurant() throws Exception {
        Restaurant test1 = new Restaurant();
        test1.setName("test1");

        String jsonReq = mapper.writeValueAsString(test1);

        this.mockMvc.perform(post("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonReq))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(test1.getName())));
    }

    @Test
    public void testDeleteRestaurant() throws Exception {
        Restaurant test1 = new Restaurant();
        test1.setName("test1");

        Restaurant test2 = new Restaurant();
        test2.setName("test2");

        List<Restaurant> expectedList = new ArrayList<>();
        expectedList.add(test1);


        String jsonRes = mapper.writeValueAsString(expectedList);

        rs.addRestaurantService(test1);
        rs.addRestaurantService(test2);

        this.mockMvc.perform(delete("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonRes));
    }

    @Test
    public void testEditRestaurant() throws Exception {
        Restaurant test1 = new Restaurant();
        test1.setName("test1");
        rs.addRestaurantService(test1);
        test1.setName("changedName");

        List<Restaurant> expectedList = new ArrayList<>();
        expectedList.add(test1);

        String jsonReq = mapper.writeValueAsString(test1);
        String jsonRes = mapper.writeValueAsString(expectedList);

        this.mockMvc.perform(put("/api/restaurants/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonReq))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonRes));
    }

    @Test
    public void testGetAllReviews() throws Exception {
        Restaurant test1 = new Restaurant();
        test1.setName("test1");

        Review review1 = new Review();
        review1.setText("review 1");

        List<Review> expectedReview = new ArrayList<>();
        expectedReview.add(review1);

        String jsonRes = mapper.writeValueAsString(expectedReview);

        rs.addRestaurantService(test1);
        rs.addReviewService(0, review1);

        this.mockMvc.perform(get("/api/restaurants/0/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonRes));

    }

    @Test
    public void testAddReview() throws Exception {
        Restaurant test1 = new Restaurant();
        test1.setName("test1");

        Review review1 = new Review();
        review1.setText("review 1");

        rs.addRestaurantService(test1);
        rs.addReviewService(0, review1);

        String jsonReq = mapper.writeValueAsString(review1);

        this.mockMvc.perform(post("/api/restaurants/0/reviews")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonReq))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(review1.getText())));
    }
}
