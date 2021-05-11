package com.daddyrusher.petclinic.service.inmemory;

import com.daddyrusher.petclinic.model.Owner;
import com.daddyrusher.petclinic.service.OwnerService;
import com.daddyrusher.petclinic.service.PetService;
import com.daddyrusher.petclinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InMemoryOwnerServiceTest {

    private final Long ownerId = 1L;
    private final String lastName = "Zanin";

    @Spy
    private PetTypeService petTypeService;
    @Spy
    private PetService petService;
    @Spy
    private OwnerService ownerService;

    @BeforeEach
    void setUp() {
        ownerService = new InMemoryOwnerService(petTypeService, petService);
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(lastName);
        ownerService.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerService.findAll();

        assertNotNull(owners);
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(ownerId);

        assertNull(ownerService.findById(ownerId));
        assertTrue(ownerService.findAll().isEmpty());
    }

    @Test
    void delete() {
        ownerService.delete(ownerService.findById(ownerId));

        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void save() {
        Long id = 2L;
        Owner owner = new Owner();
        owner.setId(id);
        Owner savedOwner = ownerService.save(owner);

        assertEquals(savedOwner.getId(), id);
    }

    @Test
    void saveNoId() {
        Owner owner = new Owner();
        Owner saved = ownerService.save(owner);

        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerService.findByLastName(lastName);

        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }
}