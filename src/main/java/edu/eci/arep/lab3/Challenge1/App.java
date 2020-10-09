package edu.eci.arep.lab3.Challenge1;

import edu.eci.arep.lab3.Challenge1.HTTPServer;


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
        HTTPServer server = new HTTPServer();
        server.start();
    }

}
