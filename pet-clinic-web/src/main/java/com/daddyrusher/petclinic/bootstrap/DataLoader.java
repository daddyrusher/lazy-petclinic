package com.daddyrusher.petclinic.bootstrap;

import com.daddyrusher.petclinic.model.Owner;
import com.daddyrusher.petclinic.model.PetType;
import com.daddyrusher.petclinic.model.Vet;
import com.daddyrusher.petclinic.service.OwnerService;
import com.daddyrusher.petclinic.service.PetTypeService;
import com.daddyrusher.petclinic.service.VetService;
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
        PetType dog = new PetType();
        dog.setName("Dog");

        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");

        petTypeService.save(cat);

        Owner bob = new Owner();
        bob.setFirstName("Bob");
        bob.setLastName("Martin");

        ownerService.save(bob);

        Owner michael = new Owner();
        michael.setFirstName("Michael");
        michael.setLastName("Jackson");

        ownerService.save(michael);

        Vet john = new Vet();
        john.setFirstName("John");
        john.setLastName("Travolta");

        vetService.save(john);
    }
}
