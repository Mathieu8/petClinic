package com.example.petclinic.model;

import java.time.LocalDateTime;

public class Visit extends BaseEntity {
    private LocalDateTime time;
    private String description;
    private Pet pet;

    public Visit() {
        time = LocalDateTime.now()
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
