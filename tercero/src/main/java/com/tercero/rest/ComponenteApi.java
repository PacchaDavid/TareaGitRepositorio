package com.tercero.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.tercero.controller.dao.services.ComponenteServices;
import com.tercero.controller.excepcion.ListEmptyException;
import com.tercero.models.Componente;

@Path("/componente")
public class ComponenteApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllComponentes() throws ListEmptyException {
        String responseJson = "";
        ComponenteServices ps = new ComponenteServices();
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
    public Response getComponenteById(@PathParam("id") Integer id) {
        String jsonResponse = "";
        ComponenteServices ps = new ComponenteServices();
        
        try {
            jsonResponse = "{\"data\":\"succes!\",\"info\":" + 
            ps.getComponenteJsonById(id) + "}";            
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
    @Path("/save")
    public Response createComponente(String json) {
        String jsonResponse = "";
        ComponenteServices ps = new ComponenteServices();
        Gson gson = new Gson();
        try {
            Componente p = gson.fromJson(json,Componente.class);
            ps.setComponente(p);
            ps.save();
            jsonResponse = "{\"data\":\"Componente created!\",\"with info\":" 
            + ps.toJson() + "}";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }
        return Response.ok(jsonResponse).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteComponente/{id}")
    public Response deleteComponente(@PathParam("id") Integer id) {
        String jsonResponse = "";
        ComponenteServices cs = new ComponenteServices();
        try {
            cs.listAll().delete(id);
            jsonResponse = "{\"data\":\"Componente deleted!\"}";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }
        

        return Response.ok(jsonResponse).build();
    }
}
