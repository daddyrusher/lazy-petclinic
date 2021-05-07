package com.daddyrusher.petclinic.service.springdata;

import com.daddyrusher.petclinic.model.Visit;
import com.daddyrusher.petclinic.repositories.VisitRepository;
import com.daddyrusher.petclinic.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdata")
@AllArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository repository;

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        repository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return repository.save(object);
    }

    @Override
    public void delete(Visit object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
