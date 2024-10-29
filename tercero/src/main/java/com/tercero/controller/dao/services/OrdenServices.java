package com.tercero.controller.dao.services;

import com.tercero.controller.dao.OrdenDao;
import com.tercero.controller.tda.list.LinkedList;
import com.tercero.models.Orden;

public class OrdenServices {
    private OrdenDao obj;
    public OrdenServices() {
        obj = new OrdenDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Orden> listAll() {
        return obj.getListAll();
    }

    public Orden getOrden() {
        return obj.getOrden();
    }

    public void setOrden(Orden Orden) {
        obj.setOrden(Orden);
    }

    public String toJson() throws Exception{
        return obj.toJson();
    }

    public Orden getOrdenById(Integer id) throws Exception  {
        return obj.getOrdenById(id);
    }

    public String getOrdenJsonById(Integer id) throws Exception {
        return obj.getOrdenJsonById(id);
    }
    
}