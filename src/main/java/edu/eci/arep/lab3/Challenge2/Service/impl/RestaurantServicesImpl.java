package edu.eci.arep.lab3.Challenge2.Service.impl;

import edu.eci.arep.lab3.Challenge2.Model.Restaurant;
import edu.eci.arep.lab3.Challenge2.Persistence.DBConnection;
import edu.eci.arep.lab3.Challenge2.Service.RestaurantServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Restaurant services.
 * @author Johan Sebastian Arias
 */
public class RestaurantServicesImpl implements RestaurantServices {

    private DBConnection client;

    /**
     * Instantiates a new Restaurant services.
     */
    public RestaurantServicesImpl() {
        this.client = new DBConnection();
    }

    @Override
    public ArrayList<Restaurant> getRestaurants() {
        return client.retrieveData();
    }

    @Override
    public void AddRestaurant(String restaurant) {

        System.out.println(restaurant);
        restaurant = restaurant.replace("\"","");
        System.out.println("RESTAAAA "+ restaurant);
        restaurant = restaurant.substring(1,restaurant.length()-1);
        String[] keyValuePairs = restaurant.split(","); //split the string to creat key-value pairs
        Map<String,String> map = new HashMap<>();
        for(String pair : keyValuePairs)                        //iterate over the pairs
        {
            System.out.println("pairrrr "+ pair);
            String[] entry = pair.split(":");                   //split the pairs to get key and value
            map.put(entry[0].trim(), entry[1].trim());          //add them to the hashmap and trim whitespaces
        }
        client.AddRestaurant(new Restaurant(Integer.parseInt(map.get("restaurant_id")),map.get("name"),map.get("borough"),map.get("cuisine")));
    }
}
