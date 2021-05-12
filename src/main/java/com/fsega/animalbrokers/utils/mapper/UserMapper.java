package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.UserCreateDto;
import com.fsega.animalbrokers.model.dto.UserDto;
import com.fsega.animalbrokers.model.entity.User;
import com.fsega.animalbrokers.model.enums.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toEntity(UserCreateDto userCreateDto) {
        if (userCreateDto == null) {
            throw new IllegalArgumentException("User mapper received null dto as input.");
        }
        return User.builder()
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .email(userCreateDto.getEmail())
                .username(userCreateDto.getUsername())
                .role(Role.USER)
                .active(true)
                .avatar(userCreateDto.getAvatar())
                .build();
    }

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .active(user.isActive())
                .avatar(user.getAvatar())
                .build();
    }

}
