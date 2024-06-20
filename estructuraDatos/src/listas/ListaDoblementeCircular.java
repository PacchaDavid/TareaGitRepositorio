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
            System.out.println("Error en el método insertarValor()");
            return;
        }
        NodoLista nodo = new NodoLista(valor);
        if(estaVacia()) {
            cabeza = nodo;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
            longitud++;
            return;
        }
        if(posicion == 1 && !estaVacia()) {
            nodo.setSiguiente(cabeza);
            cabeza.setAnterior(nodo);
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

    public void recorrerEnAmbosSentidos(boolean adelante) {
        return;
    }

}
