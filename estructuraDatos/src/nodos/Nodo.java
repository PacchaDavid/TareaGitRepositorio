package nodos;

public abstract class Nodo {
    protected int valor;


    @Override
    public String toString() {
        return "valor: "+ String.valueOf(valor);
    }
}
