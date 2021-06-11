package com.example.petclinic.services.map;

import com.example.petclinic.model.PetType;
import com.example.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements  PetTypeService{
    @Override
    public PetType save(PetType t) {
        return super.save(t);
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteByID(id);

    }

    @Override
    public void delete(PetType object) {
super.delete(object);
    }
}
