package listas;
import estructuraDatos.EstructuraDatos;
import nodos.NodoLista;

public abstract class Lista extends EstructuraDatos 
{
    protected NodoLista cabeza;
    protected int longitud;

    
    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getLongitud(){
        return longitud;
    }
    
}
