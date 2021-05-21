package com.example.petclinic.services;

import com.example.petclinic.model.Owner;

import java.util.Set;

public interface ownerInterface extends BaseInterface<Owner> {
    Owner findByLastName(String lastName);
//    Owner findById(long id);
//    Owner save(Owner owner);
//    Set<Owner> findAll();

}
