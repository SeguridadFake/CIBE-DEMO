package edu.eci.arep.lab3.Challenge2.HTTP;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * The type Request method handler.
 * @author Johan Sebastian Arias
 */
public class RequestMethodHandler {

    private static Map<String, BiFunction<HandleRequest,HandleResponse,String>> ep = new HashMap<>();

    /**
     * Get.
     *
     * @param path the path
     * @param f    the f
     */
    public static void get(String path, BiFunction<HandleRequest, HandleResponse,String> f){
        ep.put("GET"+path,f);
        System.out.println("GET"+path);
    }

    /**
     * Post.
     *
     * @param path the path
     * @param f    the f
     */
    public static void post(String path, BiFunction<HandleRequest,HandleResponse,String> f){
        ep.put("POST"+path,f);}

    /**
     * Exec handle response.
     *
     * @param request the request
     * @return the handle response
     */
    public static HandleResponse exec(HandleRequest request){
        String k = request.getMethod()+request.getPath();
        System.out.println("KKKKKKKKKKK"+k);
        System.out.println("Hello worll------------");
        if (ep.containsKey(k)){
            HandleResponse response = new HandleResponse();
            response.setBody(ep.get(k).apply(request,response));
            return response;
        }
        else{
            //System.out.println(k);
            return null;
        }
    }
}
