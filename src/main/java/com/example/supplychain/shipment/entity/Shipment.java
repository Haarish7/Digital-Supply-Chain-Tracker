package com.example.supplychain.shipment.entity;

import com.example.supplychain.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime expectedArrivalTime;
    private LocalDateTime actualArrivalTime;
    private String supplierName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;
}
