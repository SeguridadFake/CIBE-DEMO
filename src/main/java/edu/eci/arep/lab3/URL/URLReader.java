package edu.eci.arep.lab3.URL;

import java.net.*;

public class URLReader {
    public static void main(String[] args) throws Exception {
        URL oracle = new URL("https:// www.prueba.com:1234/test/id?lol=1#arep");
        System.out.println("[+] Protocol " +getProtocol(oracle));
        System.out.println("[+] Authority " + getAuthority(oracle));
        System.out.println("[+] Host " + getHost(oracle));
        System.out.println("[+] Port " + getPort(oracle));
        System.out.println("[+] Path " + getPath(oracle));
        System.out.println("[+] Query " + getQuery(oracle));
        System.out.println("[+] File " + getFile(oracle));
        System.out.println("[+] Ref " + getRef(oracle));
    }

    public static String getProtocol(URL url){
        return url.getProtocol();
    }

    public static String getAuthority(URL url){
        return url.getAuthority();
    }
    public static String getHost(URL url){
        return url.getHost();
    }
    public static int getPort(URL url){
        return url.getPort();
    }
    public static String getPath(URL url){
        return url.getPath();
    }
    public static String getRef(URL url){
        return url.getRef();
    }
    public static String getQuery(URL url){
        return url.getQuery();
    }
    public static String getFile(URL url){
        return url.getFile();
    }

}