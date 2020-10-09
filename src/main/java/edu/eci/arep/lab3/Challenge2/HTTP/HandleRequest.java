package edu.eci.arep.lab3.Challenge2.HTTP;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Handle request.
 * @author Johan Sebastian Arias
 */
public class HandleRequest {

    private String method;
    private String body;
    private String path;
    private HashMap<String ,String> headers;


    /**
     * Instantiates a new Handle request.
     */
    public HandleRequest() {
        this.headers = new HashMap<>();
        this.method="";
        this.body="";
        this.path="";
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets method.
     *
     * @param method the method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets header.
     *
     * @param header the header
     * @return the header
     */
    public String getHeader(String header) {
        return headers.get(header);
    }

    /**
     * Add header.
     *
     * @param headerName the header name
     * @param header     the header
     */
    public void addHeader(String headerName, String header) {
        this.headers.put(headerName,header);
    }

}
