package com.daddyrusher.petclinic.repositories;

import com.daddyrusher.petclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
