package com.fsega.animalbrokers.repository;

import com.fsega.animalbrokers.model.entity.AnimalClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalClassRepository extends JpaRepository<AnimalClass, UUID> {
}
