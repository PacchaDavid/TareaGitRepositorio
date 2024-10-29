package com.tercero.controller.dao;

import com.tercero.controller.dao.implement.AdapterDao;
import com.tercero.controller.tda.list.LinkedList;
import com.tercero.models.Rol;

public class RolDao extends AdapterDao<Rol> {
    private Rol rol;

    public RolDao() {
        super(Rol.class);
    }

    public Rol getRol() {
        if(this.rol == null) {
            this.rol = new Rol();
        }
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public LinkedList<Rol> getListAll() {
        return this.listAll();
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        getRol().setId(id);
        persist(getRol());
        return true;
    }

    public void deleteRolById(Integer id) throws Exception {
        LinkedList<Rol> list = listAll();
        for(int i = id; i<list.getSize();i++) {
            list.get(i).setId(i);
        }
        String newData = g.toJson(list.toArray());
        delete(id,newData);
    }

    public void update(Rol rol, Integer index) throws Exception {
        /* Rol currentRol = listAll().get(index-1);
        if(rol.getNombre() == null) rol.setNombre(currentRol.getNombre());
        if(rol.getId() == null) rol.setId(currentRol.getId()); */
        this.merge(rol, index-1);
    }

    public String toJson() {
        return g.toJson(this.getRol());
    }

    public Rol getRolById(Integer id) throws Exception {
        return this.get(id);
    }

    public String getRolJsonById(Integer id) throws Exception {
        return g.toJson(getRolById(id));
    }
}
