package edu.eci.arep.lab3.Challenge2.Service;

import edu.eci.arep.lab3.Challenge2.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface Restaurant services.
 * @author Johan Sebastian Arias
 */
public interface RestaurantServices {


    /**
     * Gets restaurants.
     *
     * @return the restaurants
     */
    public ArrayList<Restaurant> getRestaurants();

    /**
     * Add restaurant.
     *
     * @param restaurant the restaurant
     */
    public void AddRestaurant(String restaurant);

}
