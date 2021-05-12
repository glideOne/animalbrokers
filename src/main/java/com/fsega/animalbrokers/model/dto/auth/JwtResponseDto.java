package com.fsega.animalbrokers.model.dto.auth;

import com.fsega.animalbrokers.model.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDto {

    private String token;

    private String type = "Bearer";

    private UserDto userDto;

    public JwtResponseDto(String accessToken, UserDto userDto) {
        this.token = accessToken;
        this.userDto = userDto;
    }

}
