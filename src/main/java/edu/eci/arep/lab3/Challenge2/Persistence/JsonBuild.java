package edu.eci.arep.lab3.Challenge2.Persistence;

import com.google.gson.Gson;
import edu.eci.arep.lab3.Challenge2.Model.Restaurant;

import java.util.ArrayList;

/**
 * The type Json build.
 * @author Johan Sebastian Arias
 */
public class JsonBuild {

    /**
     * Get json string.
     *
     * @param restaurants the restaurants
     * @return the string
     */
    public static String getJson(ArrayList<Restaurant> restaurants){
        Gson gson = new Gson();
        return gson.toJson(restaurants);
    }


}
