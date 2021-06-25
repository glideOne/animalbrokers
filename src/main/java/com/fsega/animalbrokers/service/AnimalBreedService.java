package com.fsega.animalbrokers.service;

import com.fsega.animalbrokers.model.dto.AnimalBreedCreateDto;
import com.fsega.animalbrokers.model.dto.AnimalBreedDto;
import com.fsega.animalbrokers.model.dto.AnimalClassCreateDto;
import com.fsega.animalbrokers.model.entity.AnimalBreed;
import com.fsega.animalbrokers.model.entity.AnimalClass;
import com.fsega.animalbrokers.repository.AnimalBreedRepository;
import com.fsega.animalbrokers.repository.AnimalClassRepository;
import com.fsega.animalbrokers.utils.exception.BadRequestException;
import com.fsega.animalbrokers.utils.exception.ExceptionType;
import com.fsega.animalbrokers.utils.exception.NotFoundException;
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
    private final AnimalClassRepository animalClassRepo;

    @Transactional
    public AnimalBreedDto createAnimalBreed(AnimalBreedCreateDto animalBreedCreateDto) {
        AnimalClass animalClass = animalClassRepo.findById(animalBreedCreateDto.getAnimalClassId())
                .orElseThrow(() -> new NotFoundException("Animal class " + animalBreedCreateDto.getAnimalClassId()
                        + " was not found", ExceptionType.NOT_FOUND));

        validateAnimalBreedName(animalBreedCreateDto);
        AnimalBreed animalBreed = AnimalBreedMapper.toEntity(animalBreedCreateDto, animalClass);
        return AnimalBreedMapper.toDto(animalBreedRepo.save(animalBreed));
    }

    private void validateAnimalBreedName(AnimalBreedCreateDto animalBreedCreateDto) {
        animalBreedRepo.findByNameAndAnimalClassId(animalBreedCreateDto.getName(), animalBreedCreateDto.getAnimalClassId())
                .ifPresent(cl -> {
                    throw new BadRequestException("Animal breed " + animalBreedCreateDto.getName() + " already exists",
                            ExceptionType.ALREADY_EXISTS);
                });
    }

    @Transactional(readOnly = true)
    public List<AnimalBreedDto> getAnimalBreedByClassId(UUID animalClassId) {
        return animalBreedRepo.findByAnimalClassIdOrderByName(animalClassId).stream()
                .map(AnimalBreedMapper::toDto)
                .collect(Collectors.toList());
    }
}
