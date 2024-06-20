package loDelIng;

public class Arbol implements Estructura
{
    private Nodo1 raiz;

    public Arbol(int valor) {
        this.raiz = new Nodo1(valor);
    }

    @Override
    public boolean agregar(int valor) {
        return raiz.agregar(valor);
    }

    public void inOrden(){
        this.raiz.inOrden();
    }

    public void postOrden(){
        this.raiz.postOrden();
    }

    /* public boolean estaVacia() {
        return raiz == null;
    } */

}
