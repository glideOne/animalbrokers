package com.fsega.animalbrokers.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fsega.animalbrokers.model.entity.Photo;
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
    private String animalClassName;
    private String animalBreedName;
    private List<PhotoDto> photos;
    private LocationDto lastKnownLocation;
    private LocalDateTime lastSeenTime;
    private List<PostDto> posts;
    private UserDto creator;

}
