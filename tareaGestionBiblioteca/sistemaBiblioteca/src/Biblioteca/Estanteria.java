package Biblioteca;

public class Estanteria {
    private int numero;
    //Relaciones
    private Publicacion[] publicacionList;
    private Seccion seccion;

    //Constructor
    public Estanteria(int numero, int numeroPublicaciones){
        this.numero = numero;
        this.publicacionList = new Publicacion[numeroPublicaciones];
    }

    //Metodos
    public void agregarPublicacion(Publicacion publicacion){
        for(int i = 0; i < publicacionList.length; i++){
            if(publicacionList[i] == null){
                publicacionList[i] = publicacion;
                break;
            }
        }
    }

    public Publicacion[] getPublicacionList() {
        return publicacionList;
    }
}
