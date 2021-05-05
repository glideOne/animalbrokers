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
            "AND (CAST (:threadType as text) is null OR t.type = :threadType)")
    List<Thread> searchThreads(@Param("threadType") ThreadType threadType,
                               @Param("creatorId") UUID creatorId);

}
