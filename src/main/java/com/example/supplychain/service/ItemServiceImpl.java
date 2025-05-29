package com.example.supplychain.service;

import com.example.supplychain.dto.ItemRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public Object createItem(ItemRequest request) {
        // Simulate saving to DB or processing logic
        return "Item created successfully: " + request.getName();
    }
}
