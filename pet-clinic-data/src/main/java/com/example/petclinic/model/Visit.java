package com.example.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {
    @Column(name = "dateTime")
    private LocalDateTime time;
    @Column(name = "description")
    private String description;
@ManyToOne
@JoinColumn(name = "pet_id")
    private Pet pet;

    public Visit() {
        time = LocalDateTime.now();
    }

}
