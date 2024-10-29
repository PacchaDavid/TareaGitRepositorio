package com.tercero.controller.dao;

import com.tercero.controller.dao.implement.AdapterDao;
import com.tercero.controller.tda.list.LinkedList;
import com.tercero.models.EquipoTecnologico;

public class EquipoTecnologicoDao extends AdapterDao<EquipoTecnologico> {
    private EquipoTecnologico equipoTecnologico;

    public EquipoTecnologicoDao() {
        super(EquipoTecnologico.class);
    }

    public EquipoTecnologico getEquipoTecnologico() {
        if(this.equipoTecnologico == null) {
            this.equipoTecnologico = new EquipoTecnologico();
        }
        return this.equipoTecnologico;
    }

    public void setEquipoTecnologico(EquipoTecnologico equipoTecnologico) {
        this.equipoTecnologico = equipoTecnologico;
    }

    public LinkedList<EquipoTecnologico> getListAll() {
        return this.listAll();
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        getEquipoTecnologico().setId(id);
        persist(getEquipoTecnologico());
        return true;
    }

    public String toJson() {
        return g.toJson(this.getEquipoTecnologico());
    }

    public EquipoTecnologico getEquipoTecnologicoById(Integer id) throws Exception {
        return this.get(id);
    }

    public String getEquipoTecnologicoJsonById(Integer id) throws Exception {
        return g.toJson(getEquipoTecnologicoById(id));
    }
}