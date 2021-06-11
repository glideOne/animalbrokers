package com.fsega.animalbrokers.model.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {

    private UUID posterId;

    @NotNull
    private UUID threadId;

    @NotBlank
    private String text;

    private List<PhotoDto> photos;

    @Valid
    private LocationDto spottedAt;

}
