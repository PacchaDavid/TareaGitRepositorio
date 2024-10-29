package com.tercero.models;

public class EquipoTecnologico {
    private Integer id;

    private String marca;
    private String tipo;
    private String analisis;

    private Integer personaId;

    public EquipoTecnologico() {}

    public EquipoTecnologico(String marca, String tipo, String analisis) {
        this.marca = marca;
        this.tipo = tipo;
        this.analisis = analisis;   
    }

    public EquipoTecnologico(String marca, String tipo, String analisis, Integer personaId) {
        this(marca,tipo,analisis);
        this.marca = marca;
        this.tipo = tipo;
        this.analisis = analisis;
        this.personaId = personaId;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAnalisis() {
        return this.analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public Integer getPersonaId() {
        return this.personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    @Override
    public String toString() {
        return "Marca: " + this.marca + "\n" +
                "Tipo: " + this.tipo + "\n" + 
                "Analisis: " + this.analisis;
    }

}
