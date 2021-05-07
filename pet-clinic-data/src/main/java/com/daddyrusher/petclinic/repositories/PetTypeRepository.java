package com.daddyrusher.petclinic.repositories;

import com.daddyrusher.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
