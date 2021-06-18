package com.example.petClinic.services.springDataJPA;

import com.example.petclinic.model.Owner;
import com.example.petclinic.repositories.OwnerRepository;
import com.example.petclinic.repositories.PetRepository;
import com.example.petclinic.repositories.PetTypeRepository;
import com.example.petclinic.services.springDataJPA.OwnerSDJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    final Long ownerID =1l;
    final String lastName = "lastName";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner owner;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
////        ownerSDJpaService = new OwnerSDJpaService(ownerRepository,petRepository,petTypeRepository);
        owner =Owner.builder().lastName(lastName).id(ownerID).build();
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());

    }

    @Test
    void findById() {
         var tempOwner = Optional.of(owner);
        when(ownerRepository.findById(anyLong())).thenReturn(tempOwner);

        Owner ownerResult = service.findById(1L);

        assertNotNull(ownerResult);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner ownerResult = service.findById(1L);

        assertNull(ownerResult);
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1l).build());
        returnOwnersSet.add(Owner.builder().id(2l).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void delete() {
        service.delete(owner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ownerID);

        verify(ownerRepository, times(1)).deleteById(any());

    }

    @Test
    void findByLastName() {
        Owner returnOwner = Owner.builder().id(ownerID).lastName(lastName).build();

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner owner = service.findByLastName(lastName);

        assertEquals(lastName, owner.getLastName());

        verify(ownerRepository).findByLastName(any());
    }
}