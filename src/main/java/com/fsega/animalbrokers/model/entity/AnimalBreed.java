package com.fsega.animalbrokers.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalBreed extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_class_id")
    private AnimalClass animalClass;

    //retriever, akita, other
    @Column(nullable = false)
    private String name;

}
