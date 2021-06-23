package com.fsega.animalbrokers.repository;

import com.fsega.animalbrokers.model.entity.Thread;
import com.fsega.animalbrokers.model.enums.ThreadType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ThreadRepository extends JpaRepository<Thread, UUID> {

    Thread findThreadById(UUID id);

    @Query("SELECT t FROM Thread t WHERE (CAST (:creatorId as text) is null OR t.creator.id = :creatorId) " +
            "AND (CAST (:threadType as text) is null OR t.type = :threadType) " +
            "AND (CAST (:text as text) is null OR t.title LIKE :text OR t.description LIKE :text) " +
            "AND (CAST (:animalClassId as text) is null OR t.animalBreed.animalClass.id = :animalClassId) " +
            "AND (CAST (:animalBreedId as text) is null OR t.animalBreed.id = :animalBreedId) " +
            "ORDER BY t.created DESC")
    List<Thread> searchThreads(@Param("threadType") ThreadType threadType,
                               @Param("creatorId") UUID creatorId,
                               @Param("text") String text,
                               @Param("animalClassId") UUID animalClassId,
                               @Param("animalBreedId") UUID animalBreedId);

    Boolean existsByIdAndCreatorId(UUID threadId, UUID creatorId);

}
