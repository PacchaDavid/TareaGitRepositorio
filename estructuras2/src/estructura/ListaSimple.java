package estructura;

public class ListaSimple
{
    private Nodo cabeza;
    private int longitud;

    public ListaSimple(int valor) {
        cabeza = new Nodo(valor);
    }
    
    public void insertarValor(int valor, int posicion) {
        if(posicion < 0 || posicion > longitud) {
            System.out.println("Error de posicion");
            return;
        }
        Nodo nodo = new Nodo(valor);
        if(posicion == 0) {
            nodo.siguiente = cabeza;
            cabeza = nodo;
            longitud++;
            return;
        }
        Nodo puntero = cabeza;
        for(int i = 0; i < posicion-1; i++) {
            if(puntero.siguiente != null) {
                puntero = puntero.siguiente;
            }
        }
        nodo.siguiente = puntero.siguiente;
        puntero.siguiente = nodo;
        longitud++;       
    }

    public int buscarValor(int posicion) {
        if(posicion < 0 || posicion > longitud-1) {
            System.out.println("Error de posicion en BuscarValor");
            return Integer.MIN_VALUE;
        }
        Nodo puntero = cabeza;
        for(int i = 0; i < posicion; i++) {
            if(puntero.siguiente != null) {
                puntero = puntero.siguiente;
            }
        }
        return puntero.valor;
    }

    public void eliminarValor(int posicion) {
        if(posicion < 0|| posicion > longitud) {
            System.out.println("Error de posicion en eliminarValor");
            return;
        }
        if(posicion == 0) {
            Nodo eliminar = cabeza;
            cabeza = cabeza.siguiente;
            eliminar.siguiente = null;
            longitud--;
        }
        Nodo puntero = cabeza;
        for(int i = 0; i < posicion-1; i++) {
            if(puntero.siguiente != null) {
                puntero = puntero.siguiente;
            }
        }
        Nodo eliminar = puntero.siguiente;
        puntero.siguiente = puntero.siguiente.siguiente;
        eliminar.siguiente = null;
        longitud--;
    }

    public void recorrer() {
        System.out.print("ListaSimple [");
        Nodo puntero = cabeza;
        for(int i = 0; i <= longitud; i++) {
            System.out.print(puntero.valor);
            if(puntero.siguiente != null) {
                puntero = puntero.siguiente;
            }
            if(i < longitud){
                System.out.print(" -> ");
            }
        }
        System.out.println(" -> null] ListaSimple");
    }

}
