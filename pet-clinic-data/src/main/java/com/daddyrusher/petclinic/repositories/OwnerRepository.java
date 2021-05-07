package com.daddyrusher.petclinic.repositories;

import com.daddyrusher.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
