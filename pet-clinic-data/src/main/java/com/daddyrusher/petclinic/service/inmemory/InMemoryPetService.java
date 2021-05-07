package com.daddyrusher.petclinic.service.inmemory;

import com.daddyrusher.petclinic.model.Pet;
import com.daddyrusher.petclinic.service.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InMemoryPetService extends AbstractInMemoryService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() { return super.findAll(); }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}