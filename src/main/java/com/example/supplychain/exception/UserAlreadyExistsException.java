package com.example.supplychain.exception;

public class UserAlreadyExistsException extends RuntimeException {
public UserAlreadyExistsException(String message) {
super(message);
}
}
