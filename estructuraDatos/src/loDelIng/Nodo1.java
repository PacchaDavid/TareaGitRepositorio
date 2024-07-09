package loDelIng;

public class Nodo1 implements INodo 
{
    private int valor;
    private Nodo1 izquierda;
    private Nodo1 derecha;

    public Nodo1(int valor) {
        this.valor = valor;
    }

    //Getters & setters
    public int getValor() {
        return this.valor;
    }
    
    public boolean agregar(int valor) {
        if(valor < this.valor) {
            if(this.izquierda == null) {
                this.izquierda = new Nodo1(valor);
                return true;
            } else {
                this.izquierda.agregar(valor);
            }
        } else {
            if(this.derecha == null) {
                this.derecha = new Nodo1(valor);
                return true;
            } else {
                this.derecha.agregar(valor);
            }
        }
        return false;
    }
    public void inOrden(){
        if(this.derecha != null) {
            this.izquierda.inOrden();
        }
    }

    public void postOrden() {
        if(this.izquierda != null){
            this.izquierda.postOrden();
        }

        if(this.derecha != null) {
            this.derecha.postOrden();
        }
        System.out.println(this.valor);
    }
}
