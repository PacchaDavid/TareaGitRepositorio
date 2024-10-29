package com.tercero.controller.dao.services;

import com.tercero.controller.dao.RolDao;
import com.tercero.controller.tda.list.LinkedList;
import com.tercero.models.Rol;

public class RolServices {
    private RolDao obj;
    public RolServices() {
        obj = new RolDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Rol> listAll() {
        return obj.getListAll();
    }

    public void update(Rol rol,Integer index) throws Exception {
        obj.update(rol, index);
    } 

    public void delete(Integer id) throws Exception {
        obj.deleteRolById(id);
    }

    public Rol getRol() {
        return obj.getRol();
    }

    public void setRol(Rol Rol) {
        obj.setRol(Rol);
    }

    public String toJson() throws Exception{
        return obj.toJson();
    }

    public Rol getRolById(Integer id) throws Exception  {
        return obj.getRolById(id);
    }

    public String getRolJsonById(Integer id) throws Exception {
        return obj.getRolJsonById(id);
    }
    
}