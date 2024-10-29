package com.tercero.controller.tda.stack;

import com.tercero.controller.tda.list.Node;

public class Stack<E> {
    private Node<E> top;
    private Integer size;

    public Stack() {
        this.top = null;
        this.size = 0; 
    }

    public Node<E> getTop() {
        return top;
    }
    public void setTop(Node<E> top) {
        this.top = top;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean isEmpty() {
        return this.top == null;
    }

    public void push(E info) {
        if(isEmpty()) {
            this.top = new Node<>(info);
        } else {
            Node<E> node = new Node<>(info,this.top);
            this.top = node;
        }
    }

    public E pop() throws Exception {
        Node<E> node;
        if(isEmpty()) {
            throw new Exception("Cannot pop Stack is empty");
        } else {
            node = this.top;
            this.top = this.top.getNext();
            node.setNext(null);
        }
        return node.getInfo();
    }

    public E peek() throws Exception {
        if(!isEmpty()) return this.top.getInfo();
        else throw new Exception("Stack is empty ");
    }

}
