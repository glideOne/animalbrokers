package com.fsega.animalbrokers.repository;

import com.fsega.animalbrokers.model.entity.AnimalClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalClassRepository extends JpaRepository<AnimalClass, UUID> {

    Optional<AnimalClass> findByName(String name);

    List<AnimalClass> findAllByOrderByName();
}
