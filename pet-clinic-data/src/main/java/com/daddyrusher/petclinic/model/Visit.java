package com.daddyrusher.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static java.util.Objects.isNull;

@Getter
@Setter
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "visit_date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public boolean isNotValid() {
        return isNull(this.getPet()) ||
                isNull(this.getPet().getOwner()) ||
                isNull(this.getPet().getId()) ||
                isNull(this.getPet().getOwner().getId());
    }
}
