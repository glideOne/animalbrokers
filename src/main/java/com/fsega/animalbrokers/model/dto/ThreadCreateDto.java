package com.fsega.animalbrokers.model.dto;

import com.fsega.animalbrokers.model.enums.ThreadType;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private UUID creatorId;

    @NotBlank
    @Size(max = 160)
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private ThreadType type;

    @NotNull
    private UUID breedId;

    private List<PhotoDto> photos;

    @Valid
    private LocationDto lastKnownLocation;

    private LocalDateTime lastSeenTime;

}
