package edu.eci.arep.lab3.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            int port = 1234;
            clientSocket = new Socket("127.0.0.1",port);
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            System.out.println("----Socket Client Initialized-----");
        } catch (UnknownHostException e) {
            System.err.println("Unknown host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for"
                    + "the connection to: localhost.");
            System.exit(1);
        }
        for (int i = 0; i <=20 ; i++) {
            out.println(i);
            System.out.println("Server answer: " + in.readLine());
            Thread.sleep(1000);
        }
        /*while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);*/
        out.close();
        in.close();
        clientSocket.close();
    }
}
