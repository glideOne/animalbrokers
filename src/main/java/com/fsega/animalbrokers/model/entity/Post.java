package com.fsega.animalbrokers.model.entity;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends AbstractEntity {

    private String text;

}
