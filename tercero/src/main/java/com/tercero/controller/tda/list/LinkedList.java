package com.tercero.controller.tda.list;

import java.lang.reflect.Array;
import com.tercero.controller.excepcion.ListEmptyException;

public class LinkedList <E>{
    public Node<E> header;
    private Node<E> last;
    private Integer size;

    public LinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.header == null || this.size == 0;
    }

    public void addHeader(E dato) {
        if (this.isEmpty()) {
            this.header = new Node<>(dato);
            this.size++;
        } else {
            if(this.last == null) {
                this.header.setNext(new Node<>(dato));
                this.last = this.header.getNext();
                this.size++;
            } else {
                Node<E> aux = this.header;
                this.header = new Node<>(dato,aux);
                this.size++;
            }
        }  
    } 

    private void addLast(E info) {
        if(this.isEmpty()) {
            addHeader(info);
        } else {
            if(this.last != null) {
                Node<E> aux = this.last;
                this.last = new Node<>(info);
                aux.setNext(this.last);
                this.size++;
            } else {
                this.header.setNext(new Node<>(info));
                this.last = this.header.getNext();
                this.size++;
            }
        }
    }

    //Como el método addLast está privado entonces solo se llama en un método publico
    public void add(E info) {
        addLast(info);
    }

    //Obtener un nodo a través de su índice
    private Node<E> getNode(Integer index) throws ListEmptyException,IndexOutOfBoundsException {
        //Si la lista está vacía, lanza la excepción de lista vacía
        if (isEmpty()) { 
            throw new ListEmptyException("Error, List is empty");

        //Si el índice está fuera de rango, lanza la excepción correspondiente
        } else if (index < 0 || index.intValue() >= this.size) {
            throw new IndexOutOfBoundsException("Error, index out of range");

        //Si el indice es cero entonces se está buscando la cabeza
        } else if (index == 0) {
            return header;

        //Si el índice es size-1 entonces busca el final o nodo Last
        } else if (index == (this.size - 1)) {
            return last;

        //Sino el nodo está entre la cabeza y el final
        } else {

            //Utilizar un nodo auxiliar situado en la cabeza para recorrer la lista
            Node<E> search = header;
            int cont = 0;
            while(cont < index) {
                search = search.getNext();
                cont++;
            }
            return search;
        }
    }

    //Método para obtener la información de un nodo específico
    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException{
        return getNode(index).getInfo();
    }

    public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        /* Si está vacía o el índice es cero, entonces addHeader() para evitar la exception
        ListEmptyException  */
        if(isEmpty() || index == 0) {
            addHeader(info);
        } else if (index == this.size){
            //si el indice es el final de la lista llamar al método add last
            add(info);
        } else {
            //Busca el nodo anterior a la posicion que se desea modificar
            Node<E> anterior = getNode(index-1);
            //Crear un nuevo nodo que apunte al siguiente del auxiliar para que se ponga entre medias
            Node<E> node = new Node<>(info,anterior.getNext());
            //finalmente apunto al nuevo nodo con el anterior para que se complete la inserción
            anterior.setNext(node);
            this.size++;
        }
        
    }

    //Método para vaciar la lista (No sé en que caso lo utilizaría pero ahí está por si acaso)
    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    //Método para eliminar el primer 
    public void deleteHeader() throws ListEmptyException {
        if(isEmpty()) {
            throw new ListEmptyException("Error, List is empty");
        } else {
            Node<E> eliminar = this.header;
            this.header = this.header.getNext();
            eliminar.setNext(null);
            eliminar = null;
            size--;
        }
    }

    //Método para eliminar el último nodo
    public void deleteLast() throws ListEmptyException {
        if(isEmpty()) {
            throw new ListEmptyException("Error, List is empty");
        } else {
            if(this.size == 1) {
                deleteHeader();
            } else {
                Node<E> eliminar = this.last;
                this.last = getNode(this.size-2);
                this.last.setNext(null);
                eliminar.setNext(null);
                eliminar = null;
                size--;
            }
        }
    }

    //Método para eliminar un nodo en una posición específica
    public void delete(Integer index) throws ListEmptyException, IndexOutOfBoundsException{
        if(isEmpty()) {
            throw new ListEmptyException("Error, Lista vacía");
        } else if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error, index out of range");
        } else if(index == 0) {
            deleteHeader();
        } else if(index == this.size) {
            deleteLast();
        } else {
            Node<E> anterior = getNode(index-1);
            Node<E> eliminar = anterior.getNext();
            anterior.setNext(eliminar.getNext());
            eliminar.setNext(null);
            eliminar = null;
            this.size--;
        }
        System.out.println("Exito!");
    }

    // Método para actualizar la un objeto de una lista
    public void update(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        this.getNode(index).setInfo(info);
    }

    // Método para convertir los elemenos de una lista a string
    @Override
    public String toString() {
        if(isEmpty()) {
            return "Lista Vacía!";
        }
        
        String cadena = "";
        Node<E> nodo = this.header;
        while(nodo != null) { 
            cadena += nodo.getInfo().toString();
            nodo = nodo.getNext(); 
        }

        return cadena;
    }

    public Integer getSize() {
        return this.size;
    }

    public String toString(boolean numerado) {
        String listaString = "";
        Node<E> aux = this.header;
        Integer cont = 1;
        while(aux!=null) {
            listaString += cont.toString() + ". ";
            listaString += aux.getInfo().toString();
            listaString += "\n";
            aux = aux.getNext();
            cont++;
        }
        return listaString;
    }

    public E[] toArray() throws ListEmptyException, NegativeArraySizeException {
        if(!isEmpty()) {
            Class<?> clazz = header.getInfo().getClass();

            //Con esto le digo al compilador de java que soy consciente de lo ilegal de este cast
            @SuppressWarnings("unchecked")
            E[] matrix = (E[])Array.newInstance(clazz,this.size);
            Node<E> aux = header;
            for(int i =0; i < this.size;i++) {
                matrix[i] = aux.getInfo();
                aux = aux.getNext();
            }
            return matrix;
        } else {
            throw new ListEmptyException("Lista vacía");
        }
    }

    public LinkedList<E> toList(E[] matrix) {
        reset();
        for(int i=0;i<matrix.length;i++) {
            this.add(matrix[i]);
        }
        return this;
    }
}

/* ..Save in archivo
 * 1. Crear Lista
 * 2. aniadir objeto
 * 3. lista a json
 * 4. enviar archivo
 * 
 * ..archivo ya escrito
 * 1.leer archivo
 * 2. pasar datos a lista
 * 3. ..Save in archivo
 */
