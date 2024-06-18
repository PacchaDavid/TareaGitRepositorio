package listas;
import nodos.NodoLista;

public class ListaCircular extends Lista 
{

    public ListaCircular() {
        cabeza = null;
        longitud = 0;
    }

    public void insertarValor(int valor, int posicion) {
        if(posicion <=0 || posicion > longitud+1) {
            System.out.println("Error en insertarValor" + String.valueOf(valor));
            return;
        }
        NodoLista nodo = new NodoLista(valor);
        if(posicion == 1 && estaVacia()) {
            cabeza = nodo;
            cabeza.setSiguiente(cabeza);
            longitud++;
            return;
        }
        if(posicion == 1 && !estaVacia()) {
            cabeza.setAnterior(nodo);
            NodoLista puntero = cabeza;
            while(puntero.getSiguiente() != cabeza){
                puntero = puntero.getSiguiente();
            }
            puntero.setSiguiente(nodo);
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
        if(puntero.getSiguiente() == null) {
            puntero.setSiguiente(null);
            puntero.setSiguiente(nodo);
            nodo.setSiguiente(cabeza);
            nodo.setAnterior(puntero);
            longitud++;
            return;
        }
        
        nodo.setSiguiente(puntero.getSiguiente());
        puntero.setSiguiente(nodo);
        nodo.setAnterior(puntero);
        nodo.getSiguiente().setAnterior(nodo);
        longitud++;
        
    }

    public int buscarValor(int posicion) {
        if(posicion <= 0 || posicion > longitud || estaVacia()) {
            System.out.println("Error en BuscarValor()...");
            return Integer.MIN_VALUE;
        }
        if(posicion == 1){
            return cabeza.getValor();
        }
        
        NodoLista puntero = cabeza;
        for(int i = 1; i < posicion;i++) {
            if(puntero.getSiguiente() == null) {
                puntero = puntero.getSiguiente();
            }
        }
        return puntero.getValor();
    }

    public void eliminarValor(int posicion) {
        if(posicion<=0 || posicion>longitud || estaVacia()) {
            System.out.println("Error en eliminarValor()...");
            return;
        }
        if(posicion == 1) {
            NodoLista eliminar = cabeza;
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
            eliminar.setSiguiente(null);
            longitud--;
            NodoLista puntero = cabeza;
            while(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }
            puntero.setSiguiente(cabeza);
            return;
        }
        NodoLista puntero = cabeza;
        for(int i = 1; i<posicion-1; i++) {
            if(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }
        }
        NodoLista eliminar = puntero.getSiguiente();
        puntero.getSiguiente().getSiguiente().setAnterior(puntero);
        puntero.setSiguiente(puntero.getSiguiente().getSiguiente());
        eliminar.setSiguiente(null);
        eliminar.setAnterior(null);
        longitud--;
    }

    public void mostrarValores(){
        if(estaVacia()){
            System.out.println("Nada que mostrar..");
            return;
        }
        System.out.print("Lista Circular { ");
        System.out.print("null" + " <- " + String.valueOf(cabeza.getValor()) + " <-> ");
        NodoLista puntero = cabeza.getSiguiente();

        for(int i = 2; i <= longitud; i++) {
            int valor = puntero.getValor();
            System.out.print(String.valueOf(valor));
            if(i < longitud) {
                System.out.print(" <-> ");
            }else{
                System.out.print(" -> ");
                System.out.print("Root( ");
                System.out.print(String.valueOf(puntero.getSiguiente().getValor()) + " ) " + " }");
            }
            if(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }
            
        }

    }
}
