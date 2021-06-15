package com.example.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person{
    @Column(name = "address")
    private String addres;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String addres, String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.addres = addres;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }
}
