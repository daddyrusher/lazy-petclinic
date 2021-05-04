package com.daddyrusher.petclinic.service;

import com.daddyrusher.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
