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
import com.tercero.controller.dao.services.TecnicoServices;
import com.tercero.controller.excepcion.ListEmptyException;
import com.tercero.models.Tecnico;

@Path("/tecnico")
public class TecnicoApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllTecnicos() throws ListEmptyException {
        String responseJson = "";
        TecnicoServices ps = new TecnicoServices();
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
    public Response getTecnicoById(@PathParam("id") Integer id) {
        String jsonResponse = "";
        TecnicoServices ps = new TecnicoServices();
        
        try {
            jsonResponse = "{\"data\":\"succes!\",\"info\":" + 
            ps.getTecnicoJsonById(id) + "}";            
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
    @Path("/createTecnico")
    public Response createTecnico(String json) {
        String jsonResponse = "";
        TecnicoServices ps = new TecnicoServices();
        Gson gson = new Gson();
        try {
            Tecnico p = gson.fromJson(json,Tecnico.class);
            ps.setTecnico(p);
            ps.save();
            jsonResponse = "{\"data\":\"Tecnico created!\",\"with info\":" 
            + ps.toJson() + "}";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }
        

        return Response.ok(jsonResponse).build();
    }
}

