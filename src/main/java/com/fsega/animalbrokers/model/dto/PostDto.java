package com.fsega.animalbrokers.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fsega.animalbrokers.model.entity.Photo;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {

    private String text;
    private List<Photo> photos;
    private UserDto poster;

}
