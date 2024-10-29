package com.tercero.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.tercero.controller.dao.services.OrdenServices;
import com.tercero.controller.excepcion.ListEmptyException;
import com.tercero.models.Orden;

@Path("/orden")
public class OrdenApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllOrdens() throws ListEmptyException {
        String responseJson = "";
        OrdenServices ps = new OrdenServices();
        Gson gson = new Gson();
        
        try {
            responseJson = "{\"data\":\"succes!\",\"info\":" + 
            gson.toJson(ps.listAll().toArray()) + "}";            
        } catch (Exception e) {
            e.printStackTrace();
            responseJson = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }

        return Response.ok(responseJson).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getOrdenById(@PathParam("id") Integer id) {
        String jsonResponse = "";
        OrdenServices ps = new OrdenServices();
        
        try {
            jsonResponse = "{\"data\":\"succes!\",\"info\":" + 
            ps.getOrdenJsonById(id) + "}";            
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }

        return Response.ok(jsonResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createOrden")
    public Response createOrden(String json) {
        String jsonResponse = "";
        OrdenServices ps = new OrdenServices();
        Gson gson = new Gson();
        try {
            Orden p = gson.fromJson(json,Orden.class);
            ps.setOrden(p);
            ps.save();
            jsonResponse = "{\"data\":\"Orden created!\",\"with info\":" 
            + ps.toJson() + "}";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }
        

        return Response.ok(jsonResponse).build();
    }
}
