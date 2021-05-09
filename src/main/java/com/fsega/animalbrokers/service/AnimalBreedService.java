package com.fsega.animalbrokers.service;

import com.fsega.animalbrokers.model.dto.AnimalBreedCreateDto;
import com.fsega.animalbrokers.model.dto.AnimalBreedDto;
import com.fsega.animalbrokers.model.entity.AnimalBreed;
import com.fsega.animalbrokers.repository.AnimalBreedRepository;
import com.fsega.animalbrokers.utils.mapper.AnimalBreedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnimalBreedService {

    private final AnimalBreedRepository animalBreedRepo;

    @Transactional
    public AnimalBreedDto createAnimalBreed(AnimalBreedCreateDto animalBreedCreateDto) {
        AnimalBreed animalBreed = AnimalBreedMapper.toEntity(animalBreedCreateDto);
        return AnimalBreedMapper.toDto(animalBreedRepo.save(animalBreed));
    }

    @Transactional(readOnly = true)
    public List<AnimalBreedDto> getAnimalBreedByClassId(UUID animalClassId) {
        return animalBreedRepo.findByAnimalClassId(animalClassId).stream()
                .map(AnimalBreedMapper::toDto)
                .collect(Collectors.toList());
    }
}
