package com.tercero.controller.dao;

import com.tercero.controller.dao.implement.AdapterDao;
import com.tercero.controller.tda.list.LinkedList;
import com.tercero.models.Orden;

public class OrdenDao extends AdapterDao<Orden> {
    private Orden orden;

    public OrdenDao() {
        super(Orden.class);
    }

    public Orden getOrden() {
        if(this.orden == null) {
            this.orden = new Orden();
        }
        return this.orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public LinkedList<Orden> getListAll() {
        return this.listAll();
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        getOrden().setId(id);
        persist(getOrden());
        return true;
    }

    public String toJson() {
        return g.toJson(this.getOrden());
    }

    public Orden getOrdenById(Integer id) throws Exception {
        return this.get(id);
    }

    public String getOrdenJsonById(Integer id) throws Exception {
        return g.toJson(getOrdenById(id));
    }
}