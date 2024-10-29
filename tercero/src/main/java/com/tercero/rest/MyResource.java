package com.tercero.rest;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
//import com.tercero.controller.dao.implement.AdapterDao;
import com.tercero.controller.dao.services.RolServices;
import com.tercero.controller.tda.queue.Queue;
import com.tercero.models.Rol;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    private Queue<Rol> queue = new Queue<>();
    private Gson gson = new Gson();
    private String jsonResponse;

    /* @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/queue")
    public Response queue() {
        queue = new Queue<>();
        RolServices rs = new RolServices();
        rs.getRol().setNombre("NormalUser");
        queue.queued(rs.getRol());
        jsonResponse = "";
        try {
            jsonResponse += "{\"msg\":\"ok\",\"data\":";
            jsonResponse += gson.toJson(queue.dequeued()) + "}"; 
            queue.dequeued();
        } catch (Exception e) {
            jsonResponse = "{\"msg\":";
            jsonResponse += "\"error\",\"data\":\""+ e.getLocalizedMessage()+"\"}";
        }
        
        return Response.ok(jsonResponse).build();
    } */

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/queueRols")
    public Response queueRols(String json) {
        jsonResponse = "";
        Type arrayType = Array.newInstance(Rol.class,0).getClass();
        try {
            Rol[] roles = gson.fromJson(json, arrayType);
            this.queue.fromArraytoQueue(roles);
            jsonResponse = jsonResponseBuilder("ok",gson.toJson(roles));
        } catch (Exception e) {
            jsonResponse = jsonResponseBuilder("error",'\"'+e.getMessage()+'\"');
        }        

        return Response.ok(jsonResponse).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dequeueRol")
    public Response dequeueRol() {
        jsonResponse = "";
        RolServices rs = new RolServices();              
        try {
            queue.fromArraytoQueue(rs.listAll().toArray());  
            jsonResponse = jsonResponseBuilder("ok", gson.toJson(queue.dequeued()));
        } catch (Exception e) {
            jsonResponse = jsonResponseBuilder("error",'\"'+e.getMessage()+'\"');
        }
        return Response.ok(jsonResponse).build();
    }

    public String jsonResponseBuilder(String status,String data) {
        jsonResponse = "{\"msg\":\""+ status +"\",\"data\":"+ data +"}";
        return jsonResponse;
    }
}   
