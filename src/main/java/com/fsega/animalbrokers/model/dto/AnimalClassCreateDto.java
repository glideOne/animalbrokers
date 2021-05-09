package com.fsega.animalbrokers.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalClassCreateDto {

    @NotBlank
    @Size(max = 64)
    private String name;

}
