package estructuraDatos;

public class ListaSimple 
{
    private class Nodo
    {
        public int valor;
        public Nodo siguiente;

        public Nodo(int _valor) {
            this.valor  = _valor;
        }
    }

    private Nodo cabeza;
    private int longitud;

    public ListaSimple(){
        longitud = 0;
        cabeza = null;    
    }

    public boolean estaVacia(){
        return cabeza == null;
    }

    public void insertarValor(int _valor, int posicion) {
        if(posicion < 0|| posicion > longitud) {
            System.out.println("Error en la función: insertarValor");
            return;
        }
        Nodo nodo = new Nodo(_valor);
        if(estaVacia()) {
            cabeza = nodo;
            longitud++;
            return;
        }
        if(posicion == 0 && !estaVacia()) {
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
        if(posicion < 0 || posicion >= longitud || estaVacia()) {
            String message = (estaVacia())? "Lista Vacia..." : "Error de posicion";
            System.out.println(message);
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
        if(posicion < 0|| posicion >= longitud|| estaVacia()) {
            String message = (estaVacia()) ? "Lista Vacia... " : "Error de posicion";
            System.out.println(message);
            return;
        }

        if(posicion == 0) {
            Nodo eliminar  = cabeza;
            cabeza = cabeza.siguiente;
            longitud--;
            eliminar.siguiente = null;
            return;
        }

        Nodo puntero = cabeza;
        for(int i = 0; i < posicion - 1; i++) {
            if(puntero.siguiente != null) { 
                puntero = puntero.siguiente;
            }   
        }
        Nodo eliminar = puntero.siguiente;
        puntero.siguiente = puntero.siguiente.siguiente;
        eliminar.siguiente = null;
        longitud--;
    }

    public void mostrarValores() {
        if(estaVacia()){
            System.out.println("Lista Vacia nada que imprimir...");
            return;
        }
        System.out.print("Lista Simple -> [");
        Nodo puntero = cabeza;
        for(int i = 0; i < longitud; i++) {
            System.out.print(String.valueOf(puntero.valor));
            if(i<longitud-1) {
                System.out.print(", ");
            }
            if(puntero.siguiente != null) {
                puntero = puntero.siguiente;
            }
        }
        System.out.println("] Lista Simple;");
    }

    public static void main(String args[]) {
        ListaSimple lista = new ListaSimple();
        for(int i = 0; i <= 15; i++) {
            lista.insertarValor(i+1, i);
        }
        lista.mostrarValores();
        System.out.println("Valor de la posicion 13 ->" + String.valueOf(lista.buscarValor(13)));
        lista.eliminarValor(3);
        lista.eliminarValor(8);
        lista.eliminarValor(0);
        lista.mostrarValores();
    }
}

