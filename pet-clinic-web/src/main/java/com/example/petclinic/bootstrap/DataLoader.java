package com.example.petclinic.bootstrap;


import com.example.petclinic.model.*;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetTypeService;
import com.example.petclinic.services.SpecialitiesService;
import com.example.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = addPetType("Dog");
        PetType cat = addPetType("cat");
        PetType bird = addPetType("bird");
        PetType fish = addPetType("fish");


        Pet annaKat = addPet(cat, "annanas", Date.valueOf(LocalDate.now().minusYears(1)));
        Owner owner1 = addOwner("a", "anna", "123 appelstraat", "appeldoorn", "111 111 11 11", annaKat);
        Pet basDog1 = addPet(dog, "berend", Date.valueOf(LocalDate.now().minusWeeks(200)));
        Pet basDog2 = addPet(dog, "botje", Date.valueOf(LocalDate.now().minusWeeks(200)));
        Owner owner2 = addOwner("b", "bas", "2 buekenlaan", "baarn", "222 222 22 22", basDog1, basDog2);

        System.out.println("loaded Owners........");
        Speciality dogSpeciality = addSpeciality("dog");
        Speciality catSpeciality = addSpeciality("cat");

        Vet vet1 = addVet("z", "zzz", dogSpeciality);
        Vet vet2 = addVet("y", "youcel", catSpeciality);

        System.out.println("loaded vets %%%%%%%%");
    }

    private Speciality addSpeciality(String specialityName) {
        Speciality speciality = new Speciality();
        speciality.setDescription(specialityName);
        return specialitiesService.save(speciality);

    }

    private Vet addVet(String firstName, String lastName, Speciality speciality) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.getSpecialities().add(speciality);
        return vetService.save(vet);
    }

    private Owner addOwner(String firstName, String lastName, String address, String city, String telephone, Pet... pets) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddres(address);
        owner.setCity(city);
        owner.setTelephone(telephone);
        for (Pet pet : pets) {
            pet.setOwner(owner);
            owner.getPets().add(pet);
        }
        return ownerService.save(owner);
    }

    private Pet addPet(PetType petType, String name, Date bDay) {
        Pet pet = new Pet();
        pet.setPetType(petType);
        pet.setName(name);
        pet.setBirthday(bDay);
        return pet;

    }

    private PetType addPetType(String petType) {
        PetType temp = new PetType();
        temp.setName(petType);
        return petTypeService.save(temp);
    }
}
