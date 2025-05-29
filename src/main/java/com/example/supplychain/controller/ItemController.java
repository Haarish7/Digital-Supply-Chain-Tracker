package com.example.supplychain.controller;

import com.example.supplychain.dto.ItemRequest;
import com.example.supplychain.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemRequest request) {
        Object response = itemService.createItem(request);
        return ResponseEntity.ok(response);
    }
}

