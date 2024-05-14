import Biblioteca.Biblioteca;

public class SistemaGestion implements Contrato {
    private static int numeroPublicaciones;
    private static int numeroEstanterias;
    private static int numeroSecciones;
    private static int numeroEmpleados = 0;

    //Relaciones
    private Biblioteca biblioteca;
    private Empleado[] empleadoList;

    //Constructor
    public SistemaGestion(Biblioteca biblioteca, int numeroEmpleados){
        this.biblioteca = biblioteca;
        this.empleadoList = new Empleado[numeroEmpleados];
    }

    //metodos
    @Override
    public void contratarEmpleado(Empleado empleado) {
        for(int i = 0; i < empleadoList.length; i++){
            if(empleadoList[i] == null){
                empleadoList[i] = empleado;
                //System.out.println("Empleado contratado");
                numeroEmpleados++;
                break;
            }
        }
    }
}
