package com.daddyrusher.petclinic.service.springdata;

import com.daddyrusher.petclinic.model.Pet;
import com.daddyrusher.petclinic.repositories.PetRepository;
import com.daddyrusher.petclinic.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdata")
@AllArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository repository;

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        repository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return repository.save(object);
    }

    @Override
    public void delete(Pet object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
