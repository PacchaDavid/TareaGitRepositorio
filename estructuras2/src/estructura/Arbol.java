package estructura;

public class Arbol 
{
    private Nodo raiz;

    public Arbol(int valor) {
        raiz = new Nodo(valor);
    }

    public void insertarValor(int valor) {
        raiz.insertarValor(valor);
    }

    public void inOrden() {
        raiz.inOrden();

    }
    public void preOrden() {
        raiz.preOrden();
    }
    public void postOrden() {
        raiz.postOrden();
    }

    public static void main(String args[]) {
        Arbol arbol = new Arbol(9);
        arbol.insertarValor(5);
        arbol.insertarValor(4);
        arbol.insertarValor(6);
        arbol.insertarValor(15);
        //arbol.inOrden(); 
        arbol.postOrden();

        ListaSimple lista = new ListaSimple(15);
        for(int i = 0; i < 15; i++) {
            lista.insertarValor(i,i);
        }
        lista.recorrer();
        lista.eliminarValor(8);
        System.out.printf("valor de la posicion 8 => %d\n",lista.buscarValor(8));
    }
}
