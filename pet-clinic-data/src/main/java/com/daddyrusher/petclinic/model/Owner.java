package com.daddyrusher.petclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "owners")
@NoArgsConstructor
public class Owner extends Person {
    private String address;
    private String city;
    private String phone;
    private Set<Pet> pets = new HashSet<>();
}
