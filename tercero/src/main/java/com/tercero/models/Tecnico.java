package com.tercero.models;



public class Tecnico extends Persona {
    private Double salario;

    public Tecnico() {
        super();
    }

    public Tecnico(String nombre, String apellido, String dni, 
                   String fechaNacimiento, String direccion, Double salario) 
    {
        super(nombre,apellido,dni,fechaNacimiento,direccion);
        this.salario = salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
    
    public Double getSalario() {
        return salario;
    }


}
