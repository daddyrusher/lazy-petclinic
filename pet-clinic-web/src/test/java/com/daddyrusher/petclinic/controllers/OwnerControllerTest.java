package com.daddyrusher.petclinic.controllers;

import com.daddyrusher.petclinic.model.Owner;
import com.daddyrusher.petclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController ownerController;

    private MockMvc mockMvc;
    private Set<Owner> owners;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();

        var first = new Owner();
        first.setId(1L);
        owners.add(first);

        var second = new Owner();
        second.setId(2L);
        owners.add(second);

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void findOwners() throws Exception {
        //given
        var url = "/owners/find";
        var viewName = "owners/findOwners";

        //when

        //then
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));

        verifyNoInteractions(ownerService);
    }

    @ParameterizedTest
    @MethodSource("processFormDataProvider")
    void processFindForm(List<Owner> data,
                         String expectedViewName,
                         ResultMatcher expectedStatus) throws Exception {
        //given
        var url = "/owners/process";

        //when
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(data);

        //then
        mockMvc.perform(get(url))
                .andExpect(expectedStatus)
                .andExpect(view().name(expectedViewName));
    }

    @Test
    void displayOwner() throws Exception {
        //given
        var ownerId = 1L;
        var owner = new Owner();
        owner.setId(ownerId);

        //when
        when(ownerService.findById(any())).thenReturn(owner);

        //then
        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(ownerId))));
    }

    private static Stream<Arguments> processFormDataProvider() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(new Owner() {{
                            setId(1L);
                        }}, new Owner() {{
                            setId(2L);
                        }}),
                        "owners/ownersList",
                        status().isOk()),
                Arguments.of(Collections.singletonList(new Owner() {{
                            setId(1L);
                        }}),
                        "redirect:/owners/1",
                        status().is3xxRedirection())
        );
    }
}