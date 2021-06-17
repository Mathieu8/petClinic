package com.example.petClinic.services.map;

import com.example.petclinic.model.Owner;
import com.example.petclinic.services.map.OwnerServiceMap;
import com.example.petclinic.services.map.PetServiceMap;
import com.example.petclinic.services.map.PetTypeServiceMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
   final Long ownerID = 1l;
   final String lastName = "lastName";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().lastName(lastName).id(ownerID).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerID);

        assertEquals(ownerID, owner.getId());
    }

    @Test
    void saveExcistingID() {
        Long id = 2l;
        Owner owner2 = Owner.builder().id(id).build();
        ownerServiceMap.save(owner2);

        assertEquals(id, ownerServiceMap.findAll().size());
    }

    @Test
    void saveNoId(){
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerID));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerID);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);

        assertNotNull(owner);
        assertEquals(lastName, owner.getLastName());
        assertEquals(ownerID, owner.getId());
    }
}