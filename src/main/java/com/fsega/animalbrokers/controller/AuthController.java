package com.fsega.animalbrokers.controller;

import com.fsega.animalbrokers.model.dto.UserDto;
import com.fsega.animalbrokers.model.dto.auth.JwtResponseDto;
import com.fsega.animalbrokers.model.dto.auth.LoginRequestDto;
import com.fsega.animalbrokers.security.jwt.JwtUtils;
import com.fsega.animalbrokers.security.services.UserDetailsImpl;
import com.fsega.animalbrokers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.fsega.animalbrokers.utils.Constants.API_VERSION;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_VERSION + "/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public JwtResponseDto authenticateUser(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UserDto userDto = userService.findByEmail(userDetails.getEmail());
        return new JwtResponseDto(jwt, userDto);
    }
}
