package edu.eci.arep.lab3.Challenge2.Model;

/**
 * The type Restaurant.
 */
public class Restaurant {
    private String borough;
    private String name;
    private int restaurant_id;
    private String cuisine;

    /**
     * Instantiates a new Restaurant.
     *
     * @param restaurant_id the restaurant id
     * @param name          the name
     * @param borough       the borough
     * @param cuisine       the cuisine
     */
    public Restaurant(int restaurant_id, String name,String borough, String cuisine) {
        this.borough = borough;
        this.name = name;
        this.restaurant_id = restaurant_id;
        this.cuisine = cuisine;
    }

    /**
     * Gets borough.
     *
     * @return the borough
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Sets borough.
     *
     * @param borough the borough
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets restaurant id.
     *
     * @return the restaurant id
     */
    public int getRestaurant_id() {
        return restaurant_id;
    }

    /**
     * Sets restaurant id.
     *
     * @param restaurant_id the restaurant id
     */
    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    /**
     * Gets cuisine.
     *
     * @return the cuisine
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * Sets cuisine.
     *
     * @param cuisine the cuisine
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return "{\"name\":\""+getName()+"\",\"borough\":"+getBorough()+",\"cuisine\":\""+getCuisine()+ ",\"restaurant_id\":\""+getRestaurant_id()+"}";
    }
}
