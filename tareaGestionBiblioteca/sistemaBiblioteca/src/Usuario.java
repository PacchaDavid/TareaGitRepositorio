public class Usuario extends Persona{
    private String nroTelefono;

    public Usuario(String nombre, String nroTelefono){
        this.nombre = nombre;
        this.nroTelefono = nroTelefono;
    }

    //Metodos
    public void solicitarPrestamo(){
        System.out.println("Solicitando prestamo...");
    }
}
