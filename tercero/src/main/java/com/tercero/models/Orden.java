package com.tercero.models;

public class Orden {

    private Integer id;
    private String nroOrden;

    public Orden() {}

    public Orden(String nroOrden) {
        this();
        this.nroOrden = nroOrden;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNroOrden() {
        return this.nroOrden;
    }

    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }
    

}