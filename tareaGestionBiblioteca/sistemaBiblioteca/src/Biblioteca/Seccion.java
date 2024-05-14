package Biblioteca;

public class Seccion {
    //Atributos
    private String nombre;
    private int numeroPublicaciones;

    //Relaciones
    private Estanteria[] estanteriaList;

    //Constructor
    public Seccion(String nombre, int numeroEstanterias){
        this.nombre = nombre;
        this.estanteriaList = new Estanteria[numeroEstanterias];
    }

    //Metodos
    public void agregarEstanteria(Estanteria estanteria){
        for(int i = 0; i < estanteriaList.length; i++){
            if(estanteriaList[i] == null){
                estanteriaList[i] = estanteria;
                break;
            }
        }
    }

    public Estanteria[] getEstanteriaList() {
        return estanteriaList;
    }
}
