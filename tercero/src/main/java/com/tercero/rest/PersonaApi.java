package com.tercero.rest;


import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.tercero.controller.dao.services.PersonaServices;
import com.tercero.controller.excepcion.ListEmptyException;


@Path("/persona")
public class PersonaApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllPersons() throws ListEmptyException {
        String responseJson = "";
        PersonaServices ps = new PersonaServices();
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
    public Response getPersonaById(@PathParam("id") Integer id) {
        String jsonResponse = "";
        PersonaServices ps = new PersonaServices();
        
        try {
            jsonResponse = "{\"data\":\"succes!\",\"info\":" + 
            ps.getPersonaJsonById(id) + "}";            
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }

        return Response.ok(jsonResponse).build();
    }

    /* @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createPersona")
    public Response createPersona(String json) {
        String jsonResponse = "";
        PersonaServices ps = new PersonaServices();
        Gson gson = new Gson();
        try {
            Persona p = gson.fromJson(json,Persona.class);
            ps.setPersona(p);
            ps.save();
            jsonResponse = "{\"data\":\"persona created!\",\"with info\":" 
            + ps.toJson() + "}";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"ErrorMsg\",\"info\":\"" + 
            e.getMessage() + "\"}"; 
        }
        
        return Response.ok(jsonResponse).build();
    } */

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, String> map) {
        PersonaServices ps = new PersonaServices();
        ps.getPersona().setApellido(map.get(("apellido")).toString());
        ps.getPersona().setNombre(map.get(("nombre")).toString());
        ps.getPersona().setDni(map.get(("dni")).toString());
        ps.getPersona().setFechaNacimiento(map.get(("fechaNacimiento")).toString());
        ps.getPersona().setDireccion((map.get(("direccion")).toString()));

        HashMap<String,String> res = new HashMap<>();
        try {
            ps.save();
                res.put("msg","OK");
                res.put("data","persona registrada correctamente");
                return Response.ok(map).build();
        } catch (Exception e) {
            res.put("msg","Error");
            res.put("data",e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}


/* Existen tres tipos de validación: 
 * 1. Vista: true
 * 2. Back-End: true
 * 3. Base de datos
 */

 /*  */