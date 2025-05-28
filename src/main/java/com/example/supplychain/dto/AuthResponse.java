package com.example.supplychain.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token; 
    private String message;
    private UserDto user;

    public AuthResponse(String token, String message, UserDto user) {
        this.setToken(token);
        this.setMessage(message);
        this.setUser(user);
    }

    public AuthResponse() {
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
}