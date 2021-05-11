package com.daddyrusher.petclinic.repositories;

import com.daddyrusher.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
