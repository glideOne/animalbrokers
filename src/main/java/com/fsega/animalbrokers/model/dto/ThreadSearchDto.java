package com.fsega.animalbrokers.model.dto;

import com.fsega.animalbrokers.model.enums.ThreadType;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThreadSearchDto {

    private UUID creatorId;
    private ThreadType type;

}
