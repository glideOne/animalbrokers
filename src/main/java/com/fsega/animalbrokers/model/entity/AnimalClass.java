package com.fsega.animalbrokers.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalClass extends AbstractEntity {

    @OneToMany(mappedBy = "animalClass", fetch = FetchType.EAGER)
    private List<AnimalBreed> animalBreeds;

    //dog, cat, bird, etc.
    @Column(nullable = false)
    private String name;

}
