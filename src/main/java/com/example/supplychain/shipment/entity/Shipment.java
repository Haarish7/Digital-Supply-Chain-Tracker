package com.example.supplychain.shipment.entity;

import com.example.supplychain.enums.ShipmentStatus;
import jakarta.persistence.*;
<<<<<<< HEAD
=======
import lombok.*;

>>>>>>> 2efbfae61d12344074a8f6644ffbae56ec0fe9d7
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
<<<<<<< HEAD
=======
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
>>>>>>> 2efbfae61d12344074a8f6644ffbae56ec0fe9d7
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime expectedArrivalTime;
<<<<<<< HEAD
=======
    private LocalDateTime actualArrivalTime;
    private String supplierName;
>>>>>>> 2efbfae61d12344074a8f6644ffbae56ec0fe9d7

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;
<<<<<<< HEAD

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Checkpoint> checkpoints = new ArrayList<>();

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getExpectedArrivalTime() {
        return expectedArrivalTime;
    }
    public void setExpectedArrivalTime(LocalDateTime expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
    }

    public ShipmentStatus getStatus() {
        return status;
    }
    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
=======
>>>>>>> 2efbfae61d12344074a8f6644ffbae56ec0fe9d7
}
