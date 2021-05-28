package com.example.petclinic.services.map;


import com.example.petclinic.model.Pet;
import com.example.petclinic.services.PetSetvice;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetSetvice {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);

    }

    public void deleteById(Long id) {
        super.deleteByID(id);

    }
}