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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        var url = "/owners";

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

    @Test
    void initCreationForm() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        //given
        var owner = new Owner();
        owner.setId(1L);

        //when
        when(ownerService.save(any())).thenReturn(owner);

        //then
        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        //given
        var owner = new Owner();
        var ownerId = 1L;
        owner.setId(ownerId);

        //when
        when(ownerService.findById(anyLong())).thenReturn(owner);

        //then
        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).findById(anyLong());
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        //given
        var owner = new Owner();
        var ownerId = 1L;
        owner.setId(1L);

        //when
        when(ownerService.save(any())).thenReturn(owner);

        //then
        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any());
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