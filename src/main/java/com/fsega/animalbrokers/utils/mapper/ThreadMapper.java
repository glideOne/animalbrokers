package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.PostDto;
import com.fsega.animalbrokers.model.dto.ThreadCreateDto;
import com.fsega.animalbrokers.model.dto.ThreadDto;
import com.fsega.animalbrokers.model.entity.AnimalBreed;
import com.fsega.animalbrokers.model.entity.Thread;
import com.fsega.animalbrokers.model.entity.User;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ThreadMapper {

    public static Thread toEntity(ThreadCreateDto threadCreateDto, AnimalBreed animalBreed, User creator) {
        if (threadCreateDto == null) {
            throw new IllegalArgumentException("Thread mapper received null dto as input.");
        }
        return Thread.builder()
                .title(threadCreateDto.getTitle())
                .description(threadCreateDto.getDescription())
                .type(threadCreateDto.getType())
                .photos(threadCreateDto.getPhotos())
                .lastKnownLocation(LocationMapper.toEntity(threadCreateDto.getLastKnownLocation()))
                .lastSeenTime(threadCreateDto.getLastSeenTime())
                .animalBreed(animalBreed)
                .creator(creator)
                .build();
    }

    public static ThreadDto toDto(Thread thread) {
        if (thread == null) {
            return null;
        }
        List<PostDto> allPosts = Arrays.asList(PostDto.builder()
                .text(thread.getDescription())
                .poster(UserMapper.toDto(thread.getCreator()))
                .photos(thread.getPhotos())
                .build());
        thread.getPosts().stream().map(PostMapper::toDto).forEach(allPosts::add);

        return ThreadDto.builder()
                .id(thread.getId())
                .title(thread.getTitle())
                .description(thread.getDescription())
                .type(thread.getType())
                .animalClassName(thread.getAnimalBreed().getAnimalClass().getName())
                .animalBreedName(thread.getAnimalBreed().getName())
                .photos(thread.getPhotos())
                .lastKnownLocation(LocationMapper.toDto(thread.getLastKnownLocation()))
                .lastSeenTime(thread.getLastSeenTime())
                .creator(UserMapper.toDto(thread.getCreator()))
                .posts(allPosts)
                .build();
    }

}
