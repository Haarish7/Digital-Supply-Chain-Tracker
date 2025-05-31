package com.example.supplychain.controller;

import com.example.supplychain.dto.AuthResponse;
import com.example.supplychain.dto.LoginRequest;
import com.example.supplychain.dto.RegisterRequest;
<<<<<<< HEAD
import com.example.supplychain.service.AuthService;
=======
import com.example.supplychain.dto.UserDto;
import com.example.supplychain.entity.User;
import com.example.supplychain.enums.Role;
import com.example.supplychain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
>>>>>>> 2efbfae61d12344074a8f6644ffbae56ec0fe9d7

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
<<<<<<< HEAD
	private AuthService authService;

	@PostMapping("/register")
	public AuthResponse register(@RequestBody RegisterRequest request) {
	    return authService.register(request);
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest request) {
	    return authService.login(request);
	}
=======

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {

        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            return new AuthResponse(null, "Invalid role specified", null);
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new AuthResponse(null, "Email already registered", null);
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // You can hash this later
        user.setRole(role);

        userRepository.save(user);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return new AuthResponse(null, "User registered successfully", userDto);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return new AuthResponse(null, "User not found", null);
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return new AuthResponse(null, "Invalid password", null);
        }
>>>>>>> 2efbfae61d12344074a8f6644ffbae56ec0fe9d7

        UserDto userDto = modelMapper.map(user, UserDto.class);
        return new AuthResponse(null, "Login successful", userDto);
    }
}
