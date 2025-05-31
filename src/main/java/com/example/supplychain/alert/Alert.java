package com.example.supplychain.alert;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(nullable = false)
    private boolean resolved = false;

    private String message;

    private LocalDateTime createdAt;

    private String shipmentTrackingNumber;

    private String alertType;  // e.g., DELAYED, INFO, etc.
}
