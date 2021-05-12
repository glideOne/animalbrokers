package com.fsega.animalbrokers.model.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostSearchDto {

    private UUID posterId;
    private UUID threadId;

}
