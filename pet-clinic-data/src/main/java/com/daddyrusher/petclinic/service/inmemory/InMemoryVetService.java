package com.daddyrusher.petclinic.service.inmemory;

import com.daddyrusher.petclinic.model.Specialty;
import com.daddyrusher.petclinic.model.Vet;
import com.daddyrusher.petclinic.service.SpecialtyService;
import com.daddyrusher.petclinic.service.VetService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

import static java.util.Objects.isNull;

@Service
public class InMemoryVetService extends AbstractInMemoryService<Vet, Long> implements VetService {
    private final SpecialtyService specialtyService;

    public InMemoryVetService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if (!CollectionUtils.isEmpty(object.getSpecialties())) {
            object.getSpecialties().forEach(specialty -> {
                if (isNull(specialty.getId())) {
                    Specialty savedSpecialty = specialtyService.save(specialty);
                    specialty.setId(savedSpecialty.getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
