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
import com.tercero.controller.dao.services.EquipoTecnologicoServices;
import com.tercero.controller.excepcion.ListEmptyException;
import com.tercero.models.EquipoTecnologico;

@Path("/equipoTecnologico")
public class EquipoTecnologicoApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllEquipoTecnologicos() throws ListEmptyException {
        String responseJson = "";
        EquipoTecnologicoServices ps = new EquipoTecnologicoServices();
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
    public Response getEquipoTecnologicoById(@PathParam("id") Integer id) {
        String jsonResponse = "";
        EquipoTecnologicoServices ps = new EquipoTecnologicoServices();
        
        try {
            jsonResponse = "{\"data\":\"succes!\",\"info\":" + 
            ps.getEquipoTecnologicoJsonById(id) + "}";            
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
    @Path("/createEquipoTecnologico")
    public Response createEquipoTecnologico(String json) {
        String jsonResponse = "";
        EquipoTecnologicoServices ps = new EquipoTecnologicoServices();
        Gson gson = new Gson();
        try {
            EquipoTecnologico p = gson.fromJson(json,EquipoTecnologico.class);
            ps.setEquipoTecnologico(p);
            ps.save();
            jsonResponse = "{\"data\":\"EquipoTecnologico created!\",\"with info\":" 
            + ps.toJson() + "}";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }
        

        return Response.ok(jsonResponse).build();
    }
}
