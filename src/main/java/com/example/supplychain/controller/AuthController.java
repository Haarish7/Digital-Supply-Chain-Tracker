package com.example.supplychain.controller;

import com.example.supplychain.dto.AuthResponse;
import com.example.supplychain.dto.LoginRequest;
import com.example.supplychain.dto.RegisterRequest;
import com.example.supplychain.dto.UserDto;
import com.example.supplychain.entity.User;
import com.example.supplychain.enums.Role;
import com.example.supplychain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	 public UserRepository userRepository;
	 public ModelMapper modelMapper;
	
	
//	private final Uer uer = new Uer;
	


	@PostMapping("/register")
	public AuthResponse register(@RequestBody RegisterRequest request) {

//	    if (request.getEmail() == null || request.getPassword() == null || request.getName() == null || request.getRole() == null) {
//	        return new AuthResponse(null, "Missing required fields", null);
//	    }
//
//
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
	    user.setName(request.getName(), null);
	    user.setEmail(request.getEmail(), null);
	    user.setPassword(request.getPassword(), null); 
	    user.setRole(role, role);

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

	    UserDto userDto = modelMapper.map(user, UserDto.class);
	    return new AuthResponse(null, "Login successful", userDto);
	}

}