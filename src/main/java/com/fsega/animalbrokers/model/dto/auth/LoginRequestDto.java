package com.fsega.animalbrokers.model.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class LoginRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
