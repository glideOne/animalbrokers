package com.fsega.animalbrokers.repository;

import com.fsega.animalbrokers.model.entity.AnimalBreed;
import com.fsega.animalbrokers.model.entity.AnimalClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalBreedRepository extends JpaRepository<AnimalBreed, UUID> {

    List<AnimalBreed> findByAnimalClassId(UUID animalClassId);

    Optional<AnimalBreed> findByNameAndAnimalClassId(String name, UUID animalClassId);

}
