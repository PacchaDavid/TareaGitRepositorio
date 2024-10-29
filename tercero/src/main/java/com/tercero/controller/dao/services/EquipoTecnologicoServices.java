package com.tercero.controller.dao.services;

import com.tercero.controller.dao.EquipoTecnologicoDao;
import com.tercero.controller.tda.list.LinkedList;
import com.tercero.models.EquipoTecnologico;

public class EquipoTecnologicoServices {
    private EquipoTecnologicoDao obj;
    public EquipoTecnologicoServices() {
        obj = new EquipoTecnologicoDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<EquipoTecnologico> listAll() {
        return obj.getListAll();
    }

    public EquipoTecnologico getEquipoTecnologico() {
        return obj.getEquipoTecnologico();
    }

    public void setEquipoTecnologico(EquipoTecnologico EquipoTecnologico) {
        obj.setEquipoTecnologico(EquipoTecnologico);
    }

    public String toJson() throws Exception{
        return obj.toJson();
    }

    public EquipoTecnologico getEquipoTecnologicoById(Integer id) throws Exception  {
        return obj.getEquipoTecnologicoById(id);
    }

    public String getEquipoTecnologicoJsonById(Integer id) throws Exception {
        return obj.getEquipoTecnologicoJsonById(id);
    }
    
}
