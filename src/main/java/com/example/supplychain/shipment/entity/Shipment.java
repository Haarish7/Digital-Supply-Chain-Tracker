package com.example.supplychain.shipment.entity;

import com.example.supplychain.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String trackingNumber;
	private String origin;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime expectedArrivalTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ShipmentStatus status;

	// ✅ This field was missing and is required for report queries
	private LocalDateTime lastCheckpointTime;

	// Getters and Setters (optional due to Lombok @Data)
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

	public LocalDateTime getLastCheckpointTime() {
	    return lastCheckpointTime;
	}

	public void setLastCheckpointTime(LocalDateTime lastCheckpointTime) {
	    this.lastCheckpointTime = lastCheckpointTime;
	}

}




