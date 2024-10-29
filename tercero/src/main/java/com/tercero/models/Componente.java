package com.tercero.models;

public class Componente {

    private Integer id;
    private String nombre;
    private Double precio;

    public Componente() {}

    public Componente(String nombre, Double precio) {
        this();
        this.nombre = nombre;
        this.precio = precio;
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

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}
