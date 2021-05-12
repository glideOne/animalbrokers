package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.AnimalClassCreateDto;
import com.fsega.animalbrokers.model.dto.AnimalClassDto;
import com.fsega.animalbrokers.model.entity.AnimalBreed;
import com.fsega.animalbrokers.model.entity.AnimalClass;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AnimalClassMapper {

    public static AnimalClass toEntity(AnimalClassCreateDto animalClassCreateDto) {
        if (animalClassCreateDto == null) {
            throw new IllegalArgumentException("AnimalClass mapper received null dto as input.");
        }
        return AnimalClass.builder()
                .name(animalClassCreateDto.getName())
                .build();
    }

    public static AnimalClassDto toDto(AnimalClass animalClass) {
        if (animalClass == null) {
            return null;
        }
        return AnimalClassDto.builder()
                .id(animalClass.getId())
                .name(animalClass.getName())
                .build();
    }
}
