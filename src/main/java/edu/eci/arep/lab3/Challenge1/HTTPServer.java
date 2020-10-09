package edu.eci.arep.lab3.Challenge1;

import com.sun.net.httpserver.HttpServer;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Http server.
 * @author Johan Sebastian Arias
 */
public class HTTPServer {

    private boolean running = false;

    /**
     * Instantiates a new Http server.
     */
    public HTTPServer() {
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    /**
     * Start.
     */
    public void start() {
        try {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(getPort());
            } catch (IOException e) {
                System.err.println("Could not listen on port: " + getPort());
                System.exit(1);
            }
            running = true;
            while (running) {
                try {
                    Socket clientSocket = null;
                    try {
                        System.out.println("[+] Listening on port "+getPort());
                        clientSocket = serverSocket.accept();
                    } catch (IOException e) {
                        System.err.println("Accept failed.");
                        System.exit(1);
                    }
                    processRequest(clientSocket);
                    clientSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        Map<String, String> request = new HashMap<>();
        boolean requestLineReady = false;
        while ((inputLine = in.readLine()) != null) {
            if (!requestLineReady) {
                request.put("requestLine", inputLine);
                requestLineReady = true;
            } else {
                String[] entry = createEntry(inputLine);
                if (entry.length > 1) {
                    request.put(entry[0], entry[1]);
                }
            }
            if (!in.ready() || inputLine.length()==0) {
                break;
            }
        }
        System.out.println("HASMAP");
        System.out.println(request);
        if (request.get("requestLine") !=null){
            Request req = new Request(request.get("requestLine"));
            System.out.println("RequestLine: " + req);
            createResponse(req,
                    clientSocket.getOutputStream());
            in.close();
        }

    }

    private String[] createEntry(String rawEntry) {
        return rawEntry.split(":");
    }

    private void createResponse(Request req, OutputStream out) throws IOException {
        URI theuri = req.getTheuri();
        System.out.println("METHOOOD");
        System.out.println(req.getMethod());
        getStaticResource(theuri.getPath(),out);
        out.close();
    }

    private void getStaticResource(String path, OutputStream out) throws IOException {
        //System.out.println("PATTH----");
        //System.out.println(path);
        if (path.equals("/")) {
            path = "/index.html";
            //System.out.println("ENTRE");
        }
        Path file = Paths.get("src/main/resources" + path);
    File arch = new File(System.getProperty("user.dir")+"/"+file);
        if (arch.exists()) {

            //System.out.println("---TIPO---");
            String tipo = Files.probeContentType(file);
            if (tipo.startsWith("text/")) {
                getFile(out, file, tipo.substring(5));
            } else if (tipo.startsWith("image/")) {
                getImage(out, path, tipo.substring(6));
            }
        }else{
            file = Paths.get("src/main/resources/notFound.html");
            notFound(out,file);
        }
    }

    private void notFound(OutputStream out,Path file){
        PrintWriter response = new PrintWriter(out,true);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader
                     = new BufferedReader(new InputStreamReader(in))) {
            String header = "HTTP/1.1 404 Not Found \r\n"
                    + "Content-Type: text/html \r\n"
                    + "\r\n";
            response.println(header);
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.println(line);
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getFile(OutputStream out,Path file,String ext){
        PrintWriter response = new PrintWriter(out,true);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader
                     = new BufferedReader(new InputStreamReader(in))) {
            String header = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/"+ext+"\r\n"
                    + "\r\n";
            response.println(header);
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.println(line);
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void getImage(OutputStream out, String resource, String ext) throws IOException,IIOException {

        PrintWriter response = new PrintWriter(out, true);
        ByteArrayOutputStream by = new ByteArrayOutputStream();
        File file = new File(System.getProperty("user.dir") + "/src/main/resources" + resource);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.out.println("NOT FOUND");
        }
        System.out.println("file " + file.getName());
        System.out.println("LENGHT " + file.length());
        response.println("HTTP/1.1 200 OK\r\n" +
                "Content-Type: image/" + ext + "\r\n");
        try {
            ImageIO.write(image, ext, out);
            out.close();
        } catch (IOException e) {
            System.out.println("not found");
        }

    }
}
