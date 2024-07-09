package listas;
import nodos.NodoLista;

public class ListaDoblementeEnlazada extends Lista 
{

    public ListaDoblementeEnlazada() {
        cabeza = null;
        longitud = 0;
    }

    public void insertarValor(int valor, int posicion) {
        if(posicion <= 0 || posicion > longitud + 1){
            System.out.println("Error en la función insertarValor()");
            return;
        }
        NodoLista nodo = new NodoLista(valor);
        if(estaVacia()) {
            cabeza = nodo;
            longitud++;
            return;
        }
        if(posicion == 1 && !estaVacia()) {
            cabeza.setAnterior(nodo);
            nodo.setSiguiente(cabeza);
            cabeza = nodo;
            longitud++;
            return;
        }
        NodoLista puntero = cabeza;
        for(int i = 2;i<posicion; i++) {
            if(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }
        }
        nodo.setSiguiente(puntero.getSiguiente());
        puntero.setSiguiente(nodo);
        nodo.setAnterior(puntero);
        if(nodo.getSiguiente() != null){
            nodo.getSiguiente().setAnterior(nodo);
        }
        longitud++;
    }

    public int buscarValor(int posicion) {
        if(estaVacia()||posicion<=0||posicion>longitud) {
            return Integer.MIN_VALUE;
        }
        NodoLista puntero = cabeza;
        for(int i = 1; i < posicion; i++) {
            if(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            } 
        }
        return puntero.getValor();
    }

    
    public void eliminarValor(int posicion) {
        if(estaVacia()||posicion<=0||posicion>longitud) {
            return;
        }
        if(posicion == 1 && !estaVacia()){
            NodoLista eliminar = cabeza;
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
            eliminar.setSiguiente(null);
            longitud--;
            return;
        }
        NodoLista puntero = cabeza;
        for(int i = 1; i < posicion-1;i++) {
            if(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }
        }

        if(puntero.getSiguiente() != null) {
            NodoLista eliminar = puntero.getSiguiente();
            if(puntero.getSiguiente().getSiguiente() != null) {
                puntero.getSiguiente().getSiguiente().setAnterior(puntero);
                puntero.setSiguiente(eliminar.getSiguiente());
            }else{
                puntero.setSiguiente(null);
            }               
            eliminar.setSiguiente(null);
            eliminar.setAnterior(null);
            longitud--; 
        }
         
    }

    public void recorrer() {
        if(estaVacia()){
            return;
        }
        System.out.println("Lista Doble {");
        NodoLista puntero = cabeza;
        for(int i = 1; i <= longitud; i++) {
            System.out.printf("Nodo[%d] =  ",i);

            String anterior = (puntero.getAnterior() != null)?String.valueOf(puntero.getAnterior().getValor()) : "null";
            System.out.print(anterior);
            System.out.print(" <- ");
            System.out.print(puntero.getValor());
            System.out.print(" -> ");
            String siguiente = (puntero.getSiguiente() != null)?String.valueOf(puntero.getSiguiente().getValor()) : "null";
            System.out.print(siguiente);
            if(i<longitud){
                System.out.println(",");
            }
            if(puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }
        }
        System.out.print("\n");
        System.out.println("} Lista Doble;");
    }
}
