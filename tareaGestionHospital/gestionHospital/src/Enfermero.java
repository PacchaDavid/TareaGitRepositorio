public class Enfermero extends Persona{
    private String cargo;

    //Relaciones
    private Doctor doctor;
    private Persona persona;
    //Metodos
    public void programarCita() {
        System.out.println("Cita programada");
    }
    public void cancelarCita() {
        System.out.println("Cita cancelada");
    }
    public void realizarCita() {
        System.out.println("Cita realizada");
    }
}
