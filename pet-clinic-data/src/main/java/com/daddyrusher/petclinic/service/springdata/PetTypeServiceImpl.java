package com.daddyrusher.petclinic.service.springdata;

import com.daddyrusher.petclinic.model.PetType;
import com.daddyrusher.petclinic.repositories.PetTypeRepository;
import com.daddyrusher.petclinic.service.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdata")
@AllArgsConstructor
public class PetTypeServiceImpl implements PetTypeService {
    private final PetTypeRepository repository;

    @Override
    public Set<PetType> findAll() {
        Set<PetType> types = new HashSet<>();
        repository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public PetType findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return repository.save(object);
    }

    @Override
    public void delete(PetType object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
