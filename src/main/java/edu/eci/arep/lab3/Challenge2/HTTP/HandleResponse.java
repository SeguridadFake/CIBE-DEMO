package edu.eci.arep.lab3.Challenge2.HTTP;

/**
 * The type Handle response.
 * @author Johan Sebastian Arias
 */
public class HandleResponse {

    private String body;
    private String type;

    /**
     * Instantiates a new Handle response.
     */
    public HandleResponse() {
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
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
}

