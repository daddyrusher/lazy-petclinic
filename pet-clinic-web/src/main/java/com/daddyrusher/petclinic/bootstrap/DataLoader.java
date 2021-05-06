package com.daddyrusher.petclinic.bootstrap;

import com.daddyrusher.petclinic.model.Owner;
import com.daddyrusher.petclinic.model.Pet;
import com.daddyrusher.petclinic.model.PetType;
import com.daddyrusher.petclinic.model.Vet;
import com.daddyrusher.petclinic.service.OwnerService;
import com.daddyrusher.petclinic.service.PetTypeService;
import com.daddyrusher.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCat = petTypeService.save(cat);

        Owner bob = new Owner();
        bob.setFirstName("Bob");
        bob.setLastName("Martin");
        bob.setAddress("123 Baker street");
        bob.setCity("London");
        bob.setPhone("12345678");

        Pet bobsPet = new Pet();
        bobsPet.setPetType(savedDog);
        bobsPet.setOwner(bob);
        bobsPet.setBirthDate(LocalDate.now());
        bobsPet.setName("Puppy");
        bob.getPets().add(bobsPet);

        ownerService.save(bob);

        Owner michael = new Owner();
        michael.setFirstName("Michael");
        michael.setLastName("Jackson");
        michael.setAddress("43 Hillow street");
        michael.setCity("New York");
        michael.setPhone("984343");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedCat);
        mikesPet.setOwner(michael);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Catrin");
        michael.getPets().add(mikesPet);

        ownerService.save(michael);

        Vet john = new Vet();
        john.setFirstName("John");
        john.setLastName("Travolta");

        vetService.save(john);
    }
}
