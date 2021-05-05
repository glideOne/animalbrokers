package com.fsega.animalbrokers.model.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo extends AbstractEntity {

    private String description;

}
