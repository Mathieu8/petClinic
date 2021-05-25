package com.example.petclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {

    T save(T t);

    T findById(ID id);
    Set<T> findAll();

    void delete(T object);
    void deleteById(ID id);
}
