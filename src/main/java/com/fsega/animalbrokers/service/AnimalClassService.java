package com.fsega.animalbrokers.service;

import com.fsega.animalbrokers.model.dto.AnimalClassCreateDto;
import com.fsega.animalbrokers.model.dto.AnimalClassDto;
import com.fsega.animalbrokers.model.entity.AnimalClass;
import com.fsega.animalbrokers.repository.AnimalClassRepository;
import com.fsega.animalbrokers.utils.exception.BadRequestException;
import com.fsega.animalbrokers.utils.exception.ExceptionType;
import com.fsega.animalbrokers.utils.mapper.AnimalClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnimalClassService {

    private final AnimalClassRepository animalClassRepo;

    @Transactional
    public AnimalClassDto createAnimalClass(AnimalClassCreateDto animalClassCreateDto) {
        validateAnimalClassName(animalClassCreateDto);
        AnimalClass animalClass = AnimalClassMapper.toEntity(animalClassCreateDto);
        return AnimalClassMapper.toDto(animalClassRepo.save(animalClass));
    }

    private void validateAnimalClassName(AnimalClassCreateDto animalClassCreateDto) {
        animalClassRepo.findByName(animalClassCreateDto.getName())
                .ifPresent(cl -> {
                    throw new BadRequestException("Animal class " + animalClassCreateDto.getName() + " already exists",
                            ExceptionType.ALREADY_EXISTS);
                });
    }

    @Transactional(readOnly = true)
    public List<AnimalClassDto> getAllAnimalClasses() {
        return animalClassRepo.findAllByOrderByName().stream()
                .map(AnimalClassMapper::toDto)
                .collect(Collectors.toList());
    }
}
