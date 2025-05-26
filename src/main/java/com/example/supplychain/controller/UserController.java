package com.example.supplychain.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {

	@GetMapping("/welcome")
	public String hello() {
		return "Welcome to DSCT";
	}
}
