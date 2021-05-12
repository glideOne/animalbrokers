package com.fsega.animalbrokers.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalBreed extends AbstractEntity {

    //retriever, akita, other
    @Column(nullable = false, length = 64)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_class_id")
    private AnimalClass animalClass;

    @OneToMany(mappedBy = "animalBreed")
    private List<Thread> threads;

}
