package com.tercero.models;

public class Rol {

    private Integer id;

    private String nombre;

    public Rol() {}

    public Rol(String nombre) {
        this();
        this. nombre = nombre;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
