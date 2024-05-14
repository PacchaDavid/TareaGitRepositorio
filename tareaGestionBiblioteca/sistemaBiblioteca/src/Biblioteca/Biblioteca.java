package Biblioteca;


public class Biblioteca {
    //Atributos
    private String nombre;
    private static int numeroPublicaciones;

    //Relaciones
    private Seccion[] seccionList;

    //Constructor
    public Biblioteca(String nombre, int numeroSecciones){
        this.nombre = nombre;
        this.seccionList = new Seccion[numeroSecciones];
    }

    //Metodos
    public void agregarSeccion(Seccion seccion){
        for(int i = 0; i < seccionList.length; i++){
            if(seccionList[i] == null){
                seccionList[i] = seccion;
                break;
            }
        }
    }


    public int numeroSecciones(){
        int contador = 0;
        for(int i = 0; i < seccionList.length; i++){
            if(seccionList[i] != null){
                contador++;
            }
        }
        return contador;
    }

    public Seccion[] getSeccionList() {
        return seccionList;
    }
}
