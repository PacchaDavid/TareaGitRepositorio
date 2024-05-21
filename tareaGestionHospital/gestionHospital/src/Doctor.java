public class Doctor extends Persona {
    private int numeroIdentificacion;
    private EspecialidadMedica especialidad;

    //Relaciones
    private Paciente paciente;
    private Enfermero enfermero;


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
