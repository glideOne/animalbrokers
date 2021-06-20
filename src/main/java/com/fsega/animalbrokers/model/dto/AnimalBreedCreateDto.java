package com.fsega.animalbrokers.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalBreedCreateDto {

    @NotBlank
    @Size(max = 64)
    private String name;

    @NotNull
    private UUID animalClassId;

}
