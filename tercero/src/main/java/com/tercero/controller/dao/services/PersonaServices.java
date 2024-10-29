package com.tercero.controller.dao.services;

import com.tercero.controller.dao.PersonaDao;
import com.tercero.controller.tda.list.LinkedList;
import com.tercero.models.Persona;

public class PersonaServices {
    private PersonaDao obj;
    public PersonaServices() {
        obj = new PersonaDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Persona> listAll() {
        return obj.getListAll();
    }

    public Persona getPersona() {
        return obj.getPersona();
    }

    public void setPersona(Persona persona) {
        obj.setPersona(persona);
    }

    public String toJson() {
        return obj.toJson();
    }

    public Persona getPersonaById(Integer id) throws Exception  {
        return obj.getPersonaById(id);
    }

    public String getPersonaJsonById(Integer id) throws Exception {
        return obj.getPersonaJsonById(id);
    }
    
}
