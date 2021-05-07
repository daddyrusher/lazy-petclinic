package com.daddyrusher.petclinic.bootstrap;

import com.daddyrusher.petclinic.model.*;
import com.daddyrusher.petclinic.service.OwnerService;
import com.daddyrusher.petclinic.service.PetTypeService;
import com.daddyrusher.petclinic.service.SpecialtyService;
import com.daddyrusher.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCat = petTypeService.save(cat);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty neuroChemistry = new Specialty();
        neuroChemistry.setDescription("Neurochemistry");
        Specialty savedNeuroChemistry = specialtyService.save(neuroChemistry);

        Specialty biochemistry = new Specialty();
        biochemistry.setDescription("Biochemistry");
        Specialty savedBiochemistry = specialtyService.save(biochemistry);

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
        john.getSpecialties().add(savedBiochemistry);

        vetService.save(john);

        Vet dwayne = new Vet();
        john.setFirstName("Dwayne");
        john.setLastName("Johnson");
        john.getSpecialties().add(savedSurgery);

        vetService.save(dwayne);

        Vet matthew = new Vet();
        john.setFirstName("Matthew");
        john.setLastName("McConaughey");
        john.getSpecialties().add(savedNeuroChemistry);

        vetService.save(matthew);
    }
}
