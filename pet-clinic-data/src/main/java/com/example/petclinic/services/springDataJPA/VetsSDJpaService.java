package com.example.petclinic.services.springDataJPA;

import com.example.petclinic.model.Vet;
import com.example.petclinic.repositories.VetRepository;
import com.example.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJpa")
public class VetsSDJpaService implements VetService {
    private final VetRepository vetRepository;

    public VetsSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet save(Vet vet) {

        return vetRepository.save(vet);
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
vetRepository.deleteById(aLong);
    }
}
