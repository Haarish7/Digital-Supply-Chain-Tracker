package com.example.supplychain.controller;

import com.example.supplychain.dto.AuthResponse;
import com.example.supplychain.dto.LoginRequest;
import com.example.supplychain.dto.RegisterRequest;
import com.example.supplychain.dto.UserDto;
import com.example.supplychain.entity.User;
import com.example.supplychain.enums.Role;
import com.example.supplychain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        modelMapper = new ModelMapper();
        authController = new AuthController(userRepository, modelMapper);
    }

    @Test
    void testRegister_Successful() {
        RegisterRequest request = new RegisterRequest("Alice", "alice@example.com", "pass123", "USER");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        AuthResponse response = authController.register(request);

        assertEquals("User registered successfully", response.getMessage());
        assertNotNull(response.getUser());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegister_EmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest("Alice", "alice@example.com", "pass123", "USER");
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(new User()));

        AuthResponse response = authController.register(request);

        assertEquals("Email already registered", response.getMessage());
        assertNull(response.getUser());
    }

    @Test
    void testRegister_InvalidRole() {
        RegisterRequest request = new RegisterRequest("Bob", "bob@example.com", "pass123", "INVALID");

        AuthResponse response = authController.register(request);

        assertEquals("Invalid role specified", response.getMessage());
        assertNull(response.getUser());
    }

    @Test
    void testLogin_Successful() {
        LoginRequest request = new LoginRequest("alice@example.com", "pass123");
        User user = new User(1L, "Alice", "alice@example.com", "pass123", Role.USER);

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        AuthResponse response = authController.login(request);

        assertEquals("Login successful", response.getMessage());
        assertNotNull(response.getUser());
    }

    @Test
    void testLogin_UserNotFound() {
        LoginRequest request = new LoginRequest("unknown@example.com", "pass123");

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        AuthResponse response = authController.login(request);

        assertEquals("User not found", response.getMessage());
        assertNull(response.getUser());
    }

    @Test
    void testLogin_InvalidPassword() {
        LoginRequest request = new LoginRequest("alice@example.com", "wrongpass");
        User user = new User(1L, "Alice", "alice@example.com", "correctpass", Role.USER);

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        AuthResponse response = authController.login(request);

        assertEquals("Invalid password", response.getMessage());
        assertNull(response.getUser());
    }
}
