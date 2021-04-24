package com.fsega.animalbrokers.service;

import com.fsega.animalbrokers.model.dto.UserCreateDto;
import com.fsega.animalbrokers.model.dto.UserDto;
import com.fsega.animalbrokers.model.entity.User;
import com.fsega.animalbrokers.repository.UserRepository;
import com.fsega.animalbrokers.utils.exception.BadRequestException;
import com.fsega.animalbrokers.utils.exception.ExceptionType;
import com.fsega.animalbrokers.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepo;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserDto createUser(UserCreateDto userCreateDto) {
        checkUsernameIsUnique(userCreateDto.getUsername());
        checkEmailIsUnique(userCreateDto.getEmail());

        User user = UserMapper.toEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        return UserMapper.toDto(userRepo.save(user));
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
