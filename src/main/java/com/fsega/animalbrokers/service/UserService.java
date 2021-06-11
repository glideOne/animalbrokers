package com.fsega.animalbrokers.service;

import com.fsega.animalbrokers.model.dto.UserCreateDto;
import com.fsega.animalbrokers.model.dto.UserDto;
import com.fsega.animalbrokers.model.entity.User;
import com.fsega.animalbrokers.repository.UserRepository;
import com.fsega.animalbrokers.utils.exception.BadRequestException;
import com.fsega.animalbrokers.utils.exception.ExceptionType;
import com.fsega.animalbrokers.utils.exception.NotFoundException;
import com.fsega.animalbrokers.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserDto createUser(UserCreateDto userCreateDto) {
        checkUsernameIsUnique(userCreateDto.getUsername());
        checkEmailIsUnique(userCreateDto.getEmail());

        User user = UserMapper.toEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        return UserMapper.toDto(userRepo.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepo.findAllByOrderByFirstNameAscLastNameAsc().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(UUID userId) {
        return UserMapper.toDto(userRepo.findById(userId)
                .orElse(null));
    }

    @Transactional(readOnly = true)
    public UserDto findByEmail(String email) {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new NotFoundException(String.format("Username with email: %s does not exist.", email),
                    ExceptionType.NOT_FOUND);
        }

        return UserMapper.toDto(user);
    }

    @Transactional
    public Boolean activateUser(UUID userId) {
        User userToActivate = userRepo.findById(userId)
                .orElse(null);
        if (userToActivate == null) {
            throw new NotFoundException(String.format("User with id: %s not found.", userId),
                    ExceptionType.NOT_FOUND);
        }

        userToActivate.setActive(true);
        userRepo.save(userToActivate);
        return true;
    }

    @Transactional
    public Boolean deactivateUser(UUID userId) {
        User userToDeactivate = userRepo.findById(userId)
                .orElse(null);
        if (userToDeactivate == null) {
            throw new NotFoundException(String.format("User with id: %s not found.", userId),
                    ExceptionType.NOT_FOUND);
        }

        userToDeactivate.setActive(false);
        userRepo.save(userToDeactivate);
        return true;
    }

    private void checkUsernameIsUnique(String username) {
        if (userRepo.existsByUsername(username)) {
            throw new BadRequestException(String.format("User with username: %s already exists.", username),
                    ExceptionType.ALREADY_EXISTS);
        }
    }

    private void checkEmailIsUnique(String email) {
        if (userRepo.existsByEmail(email)) {
            throw new BadRequestException(String.format("User with email: %s already exists.", email),
                    ExceptionType.ALREADY_EXISTS);
        }
    }

}
