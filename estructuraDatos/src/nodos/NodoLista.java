package nodos;

public class NodoLista extends Nodo {
    private NodoLista siguiente = null;
    private NodoLista anterior = null;


    public NodoLista(int _valor) {
        valor = _valor;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }
    public NodoLista getAnterior() {
        return anterior;
    }
    public int getValor() {
        return valor;
    }



    public void setSiguiente(NodoLista _siguiente) { 
        siguiente = _siguiente;
    }
    public void setAnterior(NodoLista _atras) {
        anterior = _atras;
    }
    public void setValor(int _valor) {
        valor = _valor;
    }


    @Override
    public String toString() {
        return "Valor: " + String.valueOf(valor);
    }

    

}
