//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Paciente paciente = new Paciente();
        Doctor doctor = new Doctor();
        Enfermero enfermero = new Enfermero();
        paciente.programarCita();
        doctor.programarCita();
        enfermero.programarCita();
    }
}