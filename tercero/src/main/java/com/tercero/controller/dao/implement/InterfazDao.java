package com.tercero.controller.dao.implement;

import com.tercero.controller.tda.list.LinkedList;

public interface InterfazDao<T> {
    public void persist(T object) throws Exception;
    public void merge(T object, Integer index) throws Exception;

    //ojo aquí el profe le dejó en LinkedList sin parametrizar con <T>
    public LinkedList<T> listAll();
    public T get(Integer id) throws Exception;
} 