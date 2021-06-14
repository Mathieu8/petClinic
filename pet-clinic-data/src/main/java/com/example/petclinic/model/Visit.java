package com.example.petclinic.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
