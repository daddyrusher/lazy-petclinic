package com.daddyrusher.petclinic.service.springdata;

import com.daddyrusher.petclinic.model.Specialty;
import com.daddyrusher.petclinic.repositories.SpecialtyRepository;
import com.daddyrusher.petclinic.service.SpecialtyService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdata")
@AllArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepository repository;

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        repository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return repository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
