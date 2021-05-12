package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.AnimalBreedCreateDto;
import com.fsega.animalbrokers.model.dto.AnimalBreedDto;
import com.fsega.animalbrokers.model.entity.AnimalBreed;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AnimalBreedMapper {

    public static AnimalBreed toEntity(AnimalBreedCreateDto animalBreedCreateDto) {
        if (animalBreedCreateDto == null) {
            throw new IllegalArgumentException("AnimalBreed mapper received null dto as input.");
        }
        return AnimalBreed.builder()
                .name(animalBreedCreateDto.getName())
                .build();
    }

    public static AnimalBreedDto toDto(AnimalBreed animalBreed) {
        if (animalBreed == null) {
            return null;
        }
        return AnimalBreedDto.builder()
                .id(animalBreed.getId())
                .name(animalBreed.getName())
                .build();
    }
}
