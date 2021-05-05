package com.fsega.animalbrokers.model.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location extends AbstractEntity {

    Double latitude;
    Double longitude;

}
