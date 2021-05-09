package com.fsega.animalbrokers.controller;

import com.fsega.animalbrokers.model.dto.AnimalClassCreateDto;
import com.fsega.animalbrokers.model.dto.AnimalClassDto;
import com.fsega.animalbrokers.service.AnimalClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.fsega.animalbrokers.utils.Constants.API_VERSION;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_VERSION + "/classes")
public class AnimalClassController {

    private final AnimalClassService animalClassService;

    @PostMapping
    public AnimalClassDto createAnimalClass(@RequestBody @Valid AnimalClassCreateDto animalClassCreateDto) {
        return animalClassService.createAnimalClass(animalClassCreateDto);
    }

    @GetMapping
    public List<AnimalClassDto> getAllAnimalClasses() {
        return animalClassService.getAllAnimalClasses();
    }

}
