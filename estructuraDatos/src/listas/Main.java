package listas;

public class Main 
{
    public static void main(String args[]) {
        ListaEnlazadaSimple lista = rellenarLista(6);
        lista.recorrerHaciaAdelante();   
    }

    public static ListaEnlazadaSimple rellenarLista(int n) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        for(int i = 1; i <= n; i++) {
            lista.insertarValor(i,i);
        }
        return lista;
    }
}
