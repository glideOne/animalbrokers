package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.ThreadCreateDto;
import com.fsega.animalbrokers.model.dto.ThreadDto;
import com.fsega.animalbrokers.model.entity.Thread;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ThreadMapper {

    public static Thread toEntity(ThreadCreateDto threadCreateDto) {
        if (threadCreateDto == null) {
            throw new IllegalArgumentException("Thread mapper received null dto as input.");
        }
        return Thread.builder()
                .title(threadCreateDto.getTitle())
                .description(threadCreateDto.getDescription())
                .type(threadCreateDto.getType())
                .animalClass(threadCreateDto.getAnimalClass())
                .animalBreed(threadCreateDto.getAnimalBreed())
                .photos(threadCreateDto.getPhotos())
                .lastKnownLocation(threadCreateDto.getLastKnownLocation())
                .lastSeenTime(threadCreateDto.getLastSeenTime())
                .build();
    }

    public static ThreadDto toDto(Thread thread) {
        if (thread == null) {
            return null;
        }
        return ThreadDto.builder()
                .title(thread.getTitle())
                .description(thread.getDescription())
                .type(thread.getType())
                .animalClass(thread.getAnimalClass())
                .animalBreed(thread.getAnimalBreed())
                .photos(thread.getPhotos())
                .lastKnownLocation(thread.getLastKnownLocation())
                .lastSeenTime(thread.getLastSeenTime())
                .build();
    }

}
