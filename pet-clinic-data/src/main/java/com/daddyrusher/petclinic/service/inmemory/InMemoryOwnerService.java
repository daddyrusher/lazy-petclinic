package com.daddyrusher.petclinic.service.inmemory;

import com.daddyrusher.petclinic.exception.EntityException;
import com.daddyrusher.petclinic.model.Owner;
import com.daddyrusher.petclinic.model.Pet;
import com.daddyrusher.petclinic.service.OwnerService;
import com.daddyrusher.petclinic.service.PetService;
import com.daddyrusher.petclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class InMemoryOwnerService extends AbstractInMemoryService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public InMemoryOwnerService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        if (nonNull(object)) {
            if (nonNull(object.getPets())) {
                object.getPets().forEach(pet -> {
                    if (nonNull(pet.getPetType())) {
                        if (isNull(pet.getPetType().getId())) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new EntityException("Pet type is required");
                    }
                    if (isNull(pet.getId())) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }

            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
