package edu.eci.arep.lab3.Challenge2.HTTP;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
public class HttpServer extends Thread{

    private boolean running = false;

    /**
     * Instantiates a new Http server.
     */
    public HttpServer() {
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

    public void run() {
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
                        System.out.println("[++] Listening on port "+getPort());
                        clientSocket = serverSocket.accept();
                    } catch (IOException e) {
                        System.err.println("Accept failed.");
                        System.exit(1);
                    }
                    processRequest(clientSocket);
                    clientSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(com.sun.net.httpserver.HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(com.sun.net.httpserver.HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        Map<String, String> headers = new HashMap<>();
        HandleRequest request = new HandleRequest();
        boolean requestLineReady = false;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("LINEEE "+inputLine);
            if (!requestLineReady) {
                headers.put("requestLine", inputLine);
                String [] relevant = headers.get("requestLine").split(" ");
                request.setMethod(relevant[0]);
                request.setPath(relevant[1]);
                requestLineReady = true;
            } else {
                String[] entry = createEntry(inputLine);
                if (entry.length > 1) {
                    headers.put(entry[0], entry[1]);
                    request.addHeader(entry[0],entry[1]);
                }
            }
            if (!in.ready() || inputLine.length()==0) {
                break;
            }
        }
        StringBuilder body = new StringBuilder();
        if (request.getMethod().equals("POST")) {

            int c = 0;
            int cl = Integer.parseInt(request.getHeader("Content-Length").trim());
            for (int i = 0; i < cl  ; i++) {
                c = in.read();
                body.append((char) c);
            }
            request.setBody(body.toString());
        }

        if (!request.getMethod().equals("")){
            executeRequest(request,clientSocket);
        }
        in.close();


    }

    private void executeRequest(HandleRequest request, Socket clientSocket) throws IOException {
        HandleResponse controllerEndPoint = RequestMethodHandler.exec(request);

        if (request.getPath().equals("/")){
            request.setPath("/index.html");

        }
        String faile = request.getPath();
        Path file = Paths.get("src/main/resources" + request.getPath());
        File arch = new File(System.getProperty("user.dir")+"/"+file);
        String type = Files.probeContentType(file);


        if (controllerEndPoint!=null){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.print("HTTP/1.1 200 OK \r\nContent-Type: "+ type + "\r\n\r\n");
            out.print(controllerEndPoint.getBody());
            out.close();
        }
        if (!arch.exists()) {
            file = Paths.get("src/main/resources/notFound.html");
            notFound(clientSocket,file);
        }
        else if (request.getMethod().equals("GET") && request.getPath().contains(".")){
            if (type.startsWith("text/")){
                getFile(clientSocket.getOutputStream(),file,type.substring(5));

            }else if(type.startsWith("application/")){
                getFile(clientSocket.getOutputStream(),file,type.substring(13));
            } else{
                System.out.println("TIPOO IMAGEN "+ type.substring(6));
                getImage(clientSocket.getOutputStream(),arch,type.substring(6));
            }
        }
    }

    private String[] createEntry(String rawEntry) {
        return rawEntry.split(":");
    }

    private void getFile(OutputStream out,Path file,String ext) throws IOException {
        System.out.println("GETT--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
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
            }
        } catch (IOException ex) {
            Logger.getLogger(com.sun.net.httpserver.HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   private void getImage(OutputStream out, File arch, String ext) throws IOException,IIOException {
       DataOutputStream outToClient = new DataOutputStream(out);
       int numOfBytes = (int ) arch.length();
       FileInputStream inFile = new FileInputStream("src/main/resources/"+ arch.getName());
       byte[] fileBytes = new byte[numOfBytes];
       inFile.read(fileBytes);
       String header = "HTTP/1.1 200 OK\r\n"
               + "Content-Type: image/"+ext.trim()+"\r\n"
               + "\r\n";
       outToClient.writeBytes(header);
       outToClient.write(fileBytes, 0, numOfBytes);
}

    private void notFound(Socket clientSocket,Path file) throws IOException {
        PrintWriter response = new PrintWriter(clientSocket.getOutputStream(),true);
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
            }
        } catch (IOException ex) {
            Logger.getLogger(com.sun.net.httpserver.HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
