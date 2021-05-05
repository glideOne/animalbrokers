package com.fsega.animalbrokers.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fsega.animalbrokers.model.entity.*;
import com.fsega.animalbrokers.model.enums.ThreadType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThreadDto {

    private UUID id;
    private String title;
    private String description;
    private ThreadType type;
    private AnimalClass animalClass;
    private AnimalBreed animalBreed;
    private List<Photo> photos;
    private Location lastKnownLocation;
    private LocalDateTime lastSeenTime;
    private List<Post> posts;

}
