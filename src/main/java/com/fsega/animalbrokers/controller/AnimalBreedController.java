package com.fsega.animalbrokers.controller;

import com.fsega.animalbrokers.model.dto.AnimalBreedCreateDto;
import com.fsega.animalbrokers.model.dto.AnimalBreedDto;
import com.fsega.animalbrokers.service.AnimalBreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.fsega.animalbrokers.utils.Constants.API_VERSION;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_VERSION + "/breeds")
public class AnimalBreedController {

    private final AnimalBreedService animalBreedService;

    @PostMapping
    public AnimalBreedDto createAnimalBreed(@RequestBody @Valid AnimalBreedCreateDto animalBreedCreateDto,
                                            @RequestHeader(name = "Authorization") String token) {
        return animalBreedService.createAnimalBreed(animalBreedCreateDto);
    }

    @GetMapping
    public List<AnimalBreedDto> getAnimalBreedsByClassId(@RequestParam UUID animalClassId) {
        return animalBreedService.getAnimalBreedByClassId(animalClassId);
    }

}
