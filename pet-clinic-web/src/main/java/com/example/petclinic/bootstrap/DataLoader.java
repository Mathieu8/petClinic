package com.example.petclinic.bootstrap;


import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import com.example.petclinic.model.Vet;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetTypeService;
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

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = addPetType(petTypeService, "Dog");
        PetType cat = addPetType(petTypeService, "cat");
        PetType bird = addPetType(petTypeService, "bird");
        PetType fish = addPetType(petTypeService, "fish");

        Owner owner1 = new Owner();
        owner1.setFirstName("a");
        owner1.setLastName("anna");
        owner1.setAddres("123 appelstraat");
        owner1.setCity("appeldoorn");
        owner1.setTelephone("111 111 11 11");

        Pet annaKat  = new Pet();
        annaKat.setPetType(cat);
        annaKat.setOwner(owner1);
        annaKat.setBirthday(Date.valueOf(LocalDate.now().minusYears(1)));
        annaKat.setName("annanas");
        owner1.getPets().add(annaKat);

        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("b");
        owner2.setLastName("Bas");
        owner2.setAddres("2 bloedsinasappel");
        owner2.setCity("Baarn");
        owner2.setTelephone("222 222 22 22");


        Pet basDog  = new Pet();
        basDog.setPetType(dog);
        basDog.setOwner(owner2);
        basDog.setName("berend");
        basDog.setBirthday(Date.valueOf(LocalDate.now().minusWeeks(200)));
        owner2.getPets().add(basDog);

        ownerService.save(owner2);

        System.out.println("loaded Owners........");

        Vet vet1 = new Vet();
        vet1.setFirstName("z");
        vet1.setLastName("Zzz");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("y");
        vet2.setLastName("you");
        vetService.save(vet2);

        System.out.println("loaded vets %%%%%%%%");
    }

    private PetType addPetType(PetTypeService petTypeService, String petType) {
        PetType temp = new PetType();
        temp.setName(petType);
       return petTypeService.save(temp);
    }
}
