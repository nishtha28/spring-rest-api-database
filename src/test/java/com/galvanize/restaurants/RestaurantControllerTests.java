package com.galvanize.restaurants;

import com.galvanize.restaurants.controller.RestaurantController;
import com.galvanize.restaurants.model.Restaurant;
import com.galvanize.restaurants.services.RestaurantServices;
import javafx.beans.binding.When;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mock;

    @MockBean
    RestaurantServices services;

    RestaurantController rc =new RestaurantController(services);

    @Test public void testGetAll() throws  Exception{
        Restaurant test1 = new Restaurant();
        test1.setName("test1");

        List<Restaurant> resList = new ArrayList<>();
        resList.add(test1);

        when(services.getRestaurantList()).thenReturn(resList);

        mock.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",is(test1.getName())));
    }
}
