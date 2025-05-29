package com.example.supplychain.service;

import com.example.supplychain.dto.*;
import com.example.supplychain.entity.User;
import com.example.supplychain.enums.Role;
import com.example.supplychain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
	private UserRepository userRepository;
	private ModelMapper modelMapper;

	public AuthResponse register(RegisterRequest request) {
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
	    user.setPassword(request.getPassword());
	    user.setRole(role);

	    userRepository.save(user);
	    UserDto userDto = modelMapper.map(user, UserDto.class);
	    return new AuthResponse(null, "User registered successfully", userDto);
	}

	public AuthResponse login(LoginRequest request) {
	    Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

	    if (userOpt.isEmpty()) {
	        return new AuthResponse(null, "User not found", null);
	    }

	    User user = userOpt.get();

	    if (!user.getPassword().equals(request.getPassword())) {
	        return new AuthResponse(null, "Invalid password", null);
	    }

	    UserDto userDto = modelMapper.map(user, UserDto.class);
	    return new AuthResponse(null, "Login successful", userDto);
	}

}
