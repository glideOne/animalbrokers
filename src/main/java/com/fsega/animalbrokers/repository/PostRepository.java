package com.fsega.animalbrokers.repository;

import com.fsega.animalbrokers.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    Post findPostById(UUID id);

    @Query("SELECT p FROM Post p WHERE (CAST (:posterId as text) is null OR p.poster.id = :posterId) " +
            "AND (CAST (:threadId as text) is null OR p.thread.id = :threadId)")
    List<Post> searchPosts(@Param("posterId") UUID posterId,
                           @Param("threadId") UUID threadId);

    Boolean existsByIdAndPosterId(UUID postId, UUID creatorId);

}
