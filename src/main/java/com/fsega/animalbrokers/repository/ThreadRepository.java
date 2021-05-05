package com.fsega.animalbrokers.repository;

import com.fsega.animalbrokers.model.entity.Thread;
import com.fsega.animalbrokers.model.enums.ThreadType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ThreadRepository extends JpaRepository<Thread, UUID> {

    List<Thread> getAllByType(ThreadType type);
    Thread findThreadById(UUID id);

    @Query("SELECT t from Thread t WHERE t.creator.id = :userId")
    List<Thread> getAllByCreator(@Param("userId") UUID userId);

}
