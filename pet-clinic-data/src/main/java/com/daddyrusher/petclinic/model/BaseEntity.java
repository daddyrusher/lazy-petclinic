package com.daddyrusher.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static java.util.Objects.isNull;

@Getter
@Setter
public class BaseEntity implements Serializable {
    private Long id;

    public boolean isNew() { return isNull(getId()); }
}
