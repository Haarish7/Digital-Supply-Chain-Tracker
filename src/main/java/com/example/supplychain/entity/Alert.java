package com.example.supplychain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String type; // "INFO", "WARNING", "ERROR", "DAMAGE", etc.
    private LocalDateTime createdAt;
    private boolean resolved = false;

    // Optionally link to a shipment (uncomment if needed)
    // @ManyToOne
    // private Shipment shipment;

    public Alert() {}

    public Alert(String message, String type, LocalDateTime createdAt, boolean resolved) {
        this.message = message;
        this.type = type;
        this.createdAt = createdAt;
        this.resolved = resolved;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }

    // If linking to Shipment, add getter/setter for shipment here
}
