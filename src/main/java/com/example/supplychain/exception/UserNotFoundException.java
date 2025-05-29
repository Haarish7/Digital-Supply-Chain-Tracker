package com.example.supplychain.exception;

public class UserNotFoundException extends RuntimeException {
public UserNotFoundException(String message) {
super(message);
}
}