package com.fsega.animalbrokers.controller;

import com.fsega.animalbrokers.model.dto.UserCreateDto;
import com.fsega.animalbrokers.model.dto.UserDto;
import com.fsega.animalbrokers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

import static com.fsega.animalbrokers.utils.Constants.API_VERSION;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_VERSION + "/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
