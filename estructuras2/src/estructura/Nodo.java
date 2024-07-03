package estructura;

public class Nodo 
{
    public int valor;
    public Nodo derecha = null;
    public Nodo izquierda = null;
    public Nodo siguiente = null;
    public Nodo anterior = null;

    public Nodo(int _valor){
        valor = _valor;
    }

    public void insertarValor(int valor) {
        if(this.valor == valor) {
            System.out.println("Error de valor");
            return;
        }
        Nodo nodo = new Nodo(valor);
        if(valor < this.valor) {
            if(this.izquierda != null) {
                this.izquierda.insertarValor(valor);
            }else{
                this.izquierda = nodo;
            }
        }else{
            if(this.derecha != null) {
                this.derecha.insertarValor(valor);
            }else {
                this.derecha = nodo;
            }
        }
    }

    public void inOrden() {
        if(this.izquierda != null) {
            this.izquierda.inOrden();
        }
        System.out.println(this.valor); 
        if(this.derecha != null) {
            this.derecha.inOrden();
        }              
    }

    public void preOrden() {
        System.out.println(this.valor);
        if(this.izquierda != null) {
            this.izquierda.preOrden();
        } 
        if(this.derecha != null) {
            this.derecha.preOrden();
        }                
    }

    public void postOrden() {
        if(this.izquierda != null) {
            this.izquierda.postOrden();
        } 
        if(this.derecha != null) {
            this.derecha.postOrden();
        }
        System.out.println(this.valor);                       
    }
}
