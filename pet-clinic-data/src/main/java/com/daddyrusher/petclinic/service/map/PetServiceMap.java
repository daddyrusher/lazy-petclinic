package com.daddyrusher.petclinic.service.map;

import com.daddyrusher.petclinic.model.Pet;
import com.daddyrusher.petclinic.service.CrudService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {
    @Override
    public Set<Pet> findAll() {
        return this.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        this.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return this.save(object);
    }

    @Override
    public Pet findById(Long id) {
        return this.findById(id);
    }
}
