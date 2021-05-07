package com.daddyrusher.petclinic.repositories;

import com.daddyrusher.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
