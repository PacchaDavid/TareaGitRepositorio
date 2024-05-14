public class Empleado extends Persona{
    private String id;
    private String cargo;

    //Relaciones
    private SistemaGestion sistemaGestion;

    //Constructor
    public Empleado(String nombre, String id, String cargo,SistemaGestion sistema){
        this.nombre = nombre;
        this.id = id;
        this.cargo = cargo;
    }

    public String getNombre(){
        return nombre;
    }
}
