package edu.eci.arep.lab3.Browser;

import com.sun.net.httpserver.HttpServer;
import edu.eci.arep.lab3.URL.URLReader;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class URLWriter {

    public static void main(String[] args) throws IOException {
        URL url = new URL(args[0]);
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/resultado.html");
            myWriter.write("<p> [+] Protocol " +URLReader.getProtocol(url)+ "</p>");
            myWriter.write("<p> [+] Authority " +URLReader.getAuthority(url)+ "</p>");
            myWriter.write("<p> [+] Host " +URLReader.getHost(url)+ "</p>");
            myWriter.write("<p> [+] Port " +URLReader.getPort(url)+ "</p>");
            myWriter.write("<p> [+] Path " +URLReader.getPath(url)+ "</p>");
            myWriter.write("<p> [+] Query " +URLReader.getQuery(url)+ "</p>");
            myWriter.write("<p> [+] File " +URLReader.getFile(url)+ "</p>");
            myWriter.write("<p> [+] Ref " +URLReader.getRef(url)+ "</p>");
            myWriter.close();
            System.out.println("[+] Successfully wrote to the file.! ");
        } catch (IOException e) {
            System.out.println("[x] An error occurred.!");
            e.printStackTrace();
        }
    }
}
