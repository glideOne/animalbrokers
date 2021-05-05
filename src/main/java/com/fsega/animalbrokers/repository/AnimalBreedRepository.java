package com.fsega.animalbrokers.repository;

import com.fsega.animalbrokers.model.entity.AnimalBreed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalBreedRepository extends JpaRepository<AnimalBreed, UUID> {
}
