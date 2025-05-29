package com.example.supplychain.shipment.entity;

import com.example.supplychain.enums.ShipmentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String trackingNumber;
	private String origin;
	private String destination;
	private String supplierName;

	private LocalDateTime departureTime;
	private LocalDateTime expectedArrivalTime;

	private LocalDateTime lastCheckpointTime; // ✅ Required for reporting

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ShipmentStatus status;

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

	public String getSupplierName() {
	    return supplierName;
	}
	public void setSupplierName(String supplierName) {
	    this.supplierName = supplierName;
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

	public LocalDateTime getLastCheckpointTime() {
	    return lastCheckpointTime;
	}
	public void setLastCheckpointTime(LocalDateTime lastCheckpointTime) {
	    this.lastCheckpointTime = lastCheckpointTime;
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

}