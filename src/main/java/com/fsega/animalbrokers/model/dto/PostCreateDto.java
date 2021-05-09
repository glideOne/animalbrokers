package com.fsega.animalbrokers.model.dto;

import com.fsega.animalbrokers.model.entity.Photo;
import lombok.*;

import javax.persistence.OneToMany;
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

    @OneToMany
    private List<Photo> photos;

}
