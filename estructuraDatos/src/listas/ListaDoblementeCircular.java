package listas;
import nodos.NodoLista;

public class ListaDoblementeCircular extends Lista
{
    
    public ListaDoblementeCircular() {
        cabeza = null;
        longitud = 0;
    }

    public void insertarValor(int valor, int posicion) {
        if(posicion<0||posicion>longitud) {
            System.out.println("Error en la clase insertarValor()");
            return;
        }
        NodoLista nodo = new NodoLista(valor);
        if(estaVacia()) {
            cabeza = nodo;
            
        }
        return;
    }
    public int buscarValor(int posicion) {
        return Integer.MIN_VALUE;
    }
    public void eliminarValor(int posicion) {
        return;
    }


}
