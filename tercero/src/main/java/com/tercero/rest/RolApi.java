package com.tercero.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.tercero.controller.dao.services.RolServices;
import com.tercero.controller.excepcion.ListEmptyException;
import com.tercero.models.Rol;

@Path("/rol")
public class RolApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllRols() throws ListEmptyException {
        String responseJson = "";
        RolServices ps = new RolServices();
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
    public Response getRolById(@PathParam("id") Integer id) {
        String jsonResponse = "";
        RolServices ps = new RolServices();
        
        try {
            jsonResponse = "{\"data\":\"succes!\",\"info\":" + 
            ps.getRolJsonById(id) + "}";            
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
    @Path("/createRol")
    public Response createRol(String json) {
        String jsonResponse = "";
        RolServices ps = new RolServices();
        Gson gson = new Gson();
        try {
            Rol p = gson.fromJson(json,Rol.class);
            ps.setRol(p);
            ps.save();
            jsonResponse = "{\"data\":\"Rol created!\",\"with info\":" 
            + ps.toJson() + "}";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }
        

        return Response.ok(jsonResponse).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateRol")
    public Response updateRol(String json) {
        String jsonResponse = "";
        RolServices rs = new RolServices();
        Gson gson = new Gson();
        try {
            Rol r = gson.fromJson(json,Rol.class);
            rs.update(r, r.getId());
            rs.setRol(r);
            jsonResponse = "{\"data\":\"Rol upgraded!\",\"with info\":" 
            + rs.toJson() + "}";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }
        

        return Response.ok(jsonResponse).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/del/")
    public Response delRol(HashMap<String,String> json) {
        Integer id = Integer.valueOf(json.get("id"));
        RolServices rs = new RolServices();
        String jsonResponse = "";
        try {
            jsonResponse = "{\"msg\":\"ok\",\"data\"" +rs.getRolJsonById(id) + "}";
            rs.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok(jsonResponse).build();
    }
}
