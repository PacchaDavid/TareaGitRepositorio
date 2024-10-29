package com.tercero.controller.tda.queue;

import com.tercero.controller.tda.list.Node;

public class Queue<E> {
    private Node<E> head;
    private Node<E> last;

    public Queue() {
        this.head = null;
        this.last = null;
    }

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getLast() {
        return last;
    }

    public void setLast(Node<E> last) {
        this.last = last;
    }

    public Boolean isEmpty() {
        return this.head == null;
    }

    public void queued(E data) {
        Node<E> node = new Node<>(data);
        if(isEmpty()) {
            this.head = node;
            this.last = node;
        } else {
            this.last.setNext(node);
            this.last = node;
        }
    }

    public E dequeued() throws Exception {
        Node<E> node;
        if(isEmpty()) {
            throw new Exception("Queue is empty");
        } else { 
            node = this.head;
            this.head = this.head.getNext();
            node.setNext(null);
        }
        return node.getInfo();
    }

    private void reset() {
        this.head = null;
        this.last = null;
    }

    public void fromArraytoQueue(E[] array) throws Exception {
        reset();
        for(int i = 0; i < array.length; i++) {
            queued(array[i]); 
        }
    }


}
