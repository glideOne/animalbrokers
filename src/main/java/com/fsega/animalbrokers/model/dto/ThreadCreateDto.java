package com.fsega.animalbrokers.model.dto;

import com.fsega.animalbrokers.model.entity.AnimalBreed;
import com.fsega.animalbrokers.model.entity.AnimalClass;
import com.fsega.animalbrokers.model.entity.Location;
import com.fsega.animalbrokers.model.entity.Photo;
import com.fsega.animalbrokers.model.enums.ThreadType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThreadCreateDto {

    UUID creatorId;

    @NotBlank
    @Size(max = 160)
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private ThreadType type;

    @NotBlank
    private AnimalClass animalClass;

    @NotBlank
    private AnimalBreed animalBreed;

    private List<Photo> photos;

    private Location lastKnownLocation;

    private LocalDateTime lastSeenTime;

}
