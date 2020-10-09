package edu.eci.arep.lab3.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        int port = 1234;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("[+] Listening on port " + port);
        } catch (IOException e) {
            System.err.println("Could not listen on port 1234");
            System.exit(1);
        }

        Socket socket = null; //listen on port 1234
        try {
            socket = serverSocket.accept();
            System.out.println("----Socket Server Initialized-----");
        } catch (IOException e) {
            System.err.println("Socket creaton failed");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        String inputLine , outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Number from client: " + inputLine);
            outputLine = "This number raised to 2 is: " + Math.pow(Integer.parseInt(inputLine),2) ;
            out.println(outputLine);
             if (outputLine.equals("Answer: Bye."))
                break;
             }
        out.close();
        in.close();
        socket.close();
        serverSocket.close();

    }
}
