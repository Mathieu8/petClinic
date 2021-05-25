package com.example.petclinic.services;

import com.example.petclinic.model.Owner;

public interface ownerInterface extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
//    Owner findById(long id);
//    Owner save(Owner owner);
//    Set<Owner> findAll();

}
