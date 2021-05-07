package com.daddyrusher.petclinic.repositories;

import com.daddyrusher.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
