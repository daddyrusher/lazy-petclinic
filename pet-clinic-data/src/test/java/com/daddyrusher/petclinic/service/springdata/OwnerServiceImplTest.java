package com.daddyrusher.petclinic.service.springdata;

import com.daddyrusher.petclinic.model.Owner;
import com.daddyrusher.petclinic.repositories.OwnerRepository;
import com.daddyrusher.petclinic.repositories.PetRepository;
import com.daddyrusher.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {
    private static final String LAST_NAME = "Zanin";

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    private Owner mockedOwner;

    @BeforeEach
    void setUp() {
        mockedOwner = new Owner();
        mockedOwner.setId(1L);
        mockedOwner.setLastName(LAST_NAME);
    }

    @Test
    void findAll() {
        //given
        Set<Owner> expected = new HashSet<>();
        Owner first = new Owner();
        first.setId(1L);

        Owner second = new Owner();
        second.setId(2L);

        expected.add(first);
        expected.add(second);

        //when
        when(ownerRepository.findAll()).thenReturn(expected);

        //then
        Set<Owner> actual = ownerService.findAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void findById() {
        //given

        //when
        when(ownerRepository.findById(any())).thenReturn(Optional.of(mockedOwner));

        //then
        Owner actual = ownerService.findById(1L);
        assertNotNull(actual);
        assertEquals(actual.getId(), mockedOwner.getId());
    }

    @Test
    void save() {
        //given
        Owner owner = new Owner();

        //when
        when(ownerRepository.save(any())).thenReturn(mockedOwner);

        //then
        Owner saved = ownerService.save(owner);

        verify(ownerRepository).save(any());
        assertNotNull(saved);
    }

    @Test
    void delete() {
        //given

        //when

        //then
        ownerService.delete(mockedOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        //given

        //when

        //then
        ownerService.deleteById(mockedOwner.getId());
        verify(ownerRepository).deleteById(any());
    }

    @Test
    void findByLastName() {
        //given

        //when
        when(ownerRepository.findByLastName(any())).thenReturn(mockedOwner);

        //then
        Owner actual = ownerService.findByLastName(LAST_NAME);

        verify(ownerRepository).findByLastName(any());
        assertNotNull(actual);
        assertEquals(mockedOwner, actual);
    }

    @Test
    void findByIdNotFound() {
        //given

        //when
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());

        //then
        Owner owner = ownerService.findById(1L);

        assertNull(owner);
    }
}