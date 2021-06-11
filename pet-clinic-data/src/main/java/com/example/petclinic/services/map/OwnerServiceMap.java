package com.example.petclinic.services.map;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetSetvice;
import com.example.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetSetvice petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetSetvice petSetvice) {
        this.petTypeService = petTypeService;
        this.petService = petSetvice;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        if(owner!=null){
            if(owner.getPets()!=null){
                owner.getPets().forEach(pet ->{
                    if(pet.getPetType()!=null){
                        if(pet.getPetType().getId() ==0){
                            pet.setPetType(
                                    petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("PetType required");
                    }
                    if(pet.getId()==null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });

            }
            return super.save( owner);
        } else return null;

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        super.deleteByID(id);

    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
