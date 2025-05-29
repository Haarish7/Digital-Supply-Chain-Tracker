package com.example.supplychain.shipment.entity;

import com.example.supplychain.enums.ShipmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String trackingNumber;
	private String origin;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime expectedArrivalTime;

	private LocalDateTime actualArrivalTime; // ✅ Newly added
	private String supplierName;             // ✅ Newly added

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ShipmentStatus status;

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

	public LocalDateTime getActualArrivalTime() {
	    return actualArrivalTime;
	}

	public void setActualArrivalTime(LocalDateTime actualArrivalTime) {
	    this.actualArrivalTime = actualArrivalTime;
	}

	public String getSupplierName() {
	    return supplierName;
	}

	public void setSupplierName(String supplierName) {
	    this.supplierName = supplierName;
	}

	public ShipmentStatus getStatus() {
	    return status;
	}

	public void setStatus(ShipmentStatus status) {
	    this.status = status;
	}
}