package loDelIng;

public class Main1
{
    public static void main(String args[]) {
        Estructura arbol = new Arbol(10);
        arbol.agregar(5);
        arbol.agregar(8);
        arbol.agregar(3);
        arbol.agregar(13);
        ((Arbol)arbol).postOrden();
    }
}
