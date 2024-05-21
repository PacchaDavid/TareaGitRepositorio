public class Paciente extends Persona{
    //Relaciones
    private Doctor doctor;
    private Enfermero enfermero;
    private ExpedienteMedico expedienteMedico;

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
