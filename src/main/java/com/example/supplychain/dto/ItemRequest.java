package com.example.supplychain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemRequest {
    @NotBlank(message = "Item name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Supplier ID must not be null")
    private Long supplierId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
