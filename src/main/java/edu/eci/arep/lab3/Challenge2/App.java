package edu.eci.arep.lab3.Challenge2;

import com.google.gson.Gson;
import edu.eci.arep.lab3.Challenge2.HTTP.HandleRequest;
import edu.eci.arep.lab3.Challenge2.HTTP.HttpServer;
import edu.eci.arep.lab3.Challenge2.HTTP.RequestMethodHandler;
import edu.eci.arep.lab3.Challenge2.Model.Restaurant;
import edu.eci.arep.lab3.Challenge2.Persistence.JsonBuild;
import edu.eci.arep.lab3.Challenge2.Service.RestaurantServices;
import edu.eci.arep.lab3.Challenge2.Service.impl.RestaurantServicesImpl;

/**
 * The type App.
 * @author Johan Sebastian Arias
 */
public class App {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        RestaurantServices restaurantServices = new RestaurantServicesImpl();

        //System.out.println("DATAAAA" + JsonBuild.getJson(restaurantServices.getRestaurants()));
        HttpServer server = new HttpServer();
        server.start();
        RequestMethodHandler.get("/restaurants",((request,response) ->
                       JsonBuild.getJson(restaurantServices.getRestaurants())));
        RequestMethodHandler.post("/restaurants",(request,response)-> {
            restaurantServices.AddRestaurant(request.getBody());
        return "";

        });
        //RequestMethodHandler.get("/restaurants",((request,response) ->
          //                "HELLO BITCH"));
    }
}
