package com.example.petclinic.bootstrap;


import com.example.petclinic.model.Owner;
import com.example.petclinic.model.PetType;
import com.example.petclinic.model.Vet;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetTypeService;
import com.example.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


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

        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("b");
        owner2.setLastName("Bas");
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
