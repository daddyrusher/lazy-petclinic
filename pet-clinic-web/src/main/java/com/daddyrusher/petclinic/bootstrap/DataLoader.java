package com.daddyrusher.petclinic.bootstrap;

import com.daddyrusher.petclinic.model.Owner;
import com.daddyrusher.petclinic.model.Pet;
import com.daddyrusher.petclinic.model.Vet;
import com.daddyrusher.petclinic.service.OwnerService;
import com.daddyrusher.petclinic.service.VetService;
import com.daddyrusher.petclinic.service.map.OwnerServiceMap;
import com.daddyrusher.petclinic.service.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner bob = new Owner();
        bob.setId(1L);
        bob.setFirstName("Bob");
        bob.setLastName("Martin");

        ownerService.save(bob);

        Owner michael = new Owner();
        michael.setId(2L);
        michael.setFirstName("Michael");
        michael.setLastName("Jackson");

        ownerService.save(michael);

        Vet john = new Vet();
        john.setId(1L);
        john.setFirstName("John");
        john.setLastName("Travolta");

        vetService.save(john);
    }
}
