package listas;
import nodos.NodoLista;

public class ListaEnlazadaSimple extends Lista
{

    public ListaEnlazadaSimple() {
        cabeza = null;
        longitud = 0;
    }


    public void insertarValor(int valor, int posicion) {
        NodoLista nodo = new NodoLista(valor);
        if(posicion <= 0 || posicion > longitud + 1) {
            System.out.println("Error: posición mal especificada!");
            return;
        }
        if(cabeza == null) {
            cabeza = nodo;
            longitud++;
            return;
        }

        if(posicion == 1 && cabeza != null) {
            nodo.setSiguiente(cabeza);
            cabeza = nodo;
            longitud++;
            return;
        }
        NodoLista puntero = cabeza;
        for(int i = 2; i < posicion; i++) {
            if(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }
        }
        nodo.setSiguiente(puntero.getSiguiente());
        puntero.setSiguiente(nodo);
        longitud++;
    }



    public int buscarValor(int posicion) {
        if(cabeza == null || posicion <= 0 || posicion > longitud) {
            if(cabeza == null){

            }
            return Integer.MIN_VALUE;
        }

        NodoLista puntero = cabeza;
        for(int i = 1; i < posicion;i++) {
            if(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }
        }

        return puntero.getValor();
    }


    public void eliminarValor(int posicion) {
        if(cabeza == null || posicion <= 0 || posicion > longitud) {
            System.out.println("Error");
            return;
        }

        if(posicion == 1) {
            NodoLista eliminar = cabeza;
            cabeza = cabeza.getSiguiente();
            eliminar.setSiguiente(null);
            longitud--;
            return;
        }
        
        NodoLista puntero = cabeza;
        for(int i = 1; i<posicion-1; i++) {
            if(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }
        }
        NodoLista eliminar = puntero.getSiguiente();
        puntero.setSiguiente(puntero.getSiguiente().getSiguiente());
        eliminar.setSiguiente(null);
        longitud--;
    }

    public void recorrerHaciaAdelante() {
        if(cabeza != null) {
            NodoLista puntero = cabeza;
            System.out.print("Lista Simple -> [");
            for(int i = 1; i <= longitud; i++) {
                System.out.print(puntero.getValor());
                if(i<longitud){
                    System.out.print(",");
                }
                if(puntero.getSiguiente() != null) {
                    puntero = puntero.getSiguiente();
                }
            }
            System.out.println("]");
        }else{
            System.out.println("Lista vacía, nada que mostrar... ");
        }
    }

    public int getLongitud() {
        return longitud;
    }

}
