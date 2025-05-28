package com.example.supplychain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Hello API", description = "Simple Hello API to test Swagger")
public class HelloControl {

    @Operation(summary = "Say hello to a user")
    @GetMapping("/hello")
    public String sayHello(@RequestParam String name) {
        return "Hello, " + name + "! Swagger is working.";
    }
}
