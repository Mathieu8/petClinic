package com.example.petclinic.services;

import java.util.Set;

public interface BaseInterface<T> {

    T findById(long id);
    T save(T t);
    Set<T> findAll();
}
