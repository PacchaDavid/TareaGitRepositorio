package com.tercero.controller.dao.services;

import com.tercero.controller.dao.TecnicoDao;
import com.tercero.controller.tda.list.LinkedList;
import com.tercero.models.Tecnico;

public class TecnicoServices {
    private TecnicoDao obj;
    public TecnicoServices() {
        obj = new TecnicoDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Tecnico> listAll() {
        return obj.getListAll();
    }

    public Tecnico getTecnico() {
        return obj.getTecnico();
    }

    public void setTecnico(Tecnico Tecnico) {
        obj.setTecnico(Tecnico);
    }

    public String toJson() throws Exception{
        return obj.toJson();
    }

    public Tecnico getTecnicoById(Integer id) throws Exception  {
        return obj.getTecnicoById(id);
    }

    public String getTecnicoJsonById(Integer id) throws Exception {
        return obj.getTecnicoJsonById(id);
    }
    
}