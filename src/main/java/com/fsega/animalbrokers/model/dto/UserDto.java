package com.fsega.animalbrokers.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fsega.animalbrokers.model.enums.Role;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Role role;
    private boolean active;
    private byte[] avatar;

}
