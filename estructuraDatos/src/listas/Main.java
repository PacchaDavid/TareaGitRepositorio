package listas;
public class Main 
{
    public static void main(String args[]) {
        ListaDoblementeEnlazada lista = new ListaDoblementeEnlazada();

        for(int i = 1; i<= 15; i++){
            lista.insertarValor(i, i);
        }

        lista.recorrer();

        lista.insertarValor(38, 1);

        lista.recorrer();

        lista.eliminarValor(16);

        lista.recorrer();

        lista.eliminarValor(3);
        lista.recorrer();
    }

}
