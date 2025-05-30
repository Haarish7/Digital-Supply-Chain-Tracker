package com.example.supplychain.shipment.service;

import com.example.supplychain.enums.ShipmentStatus;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.repository.ShipmentRepository;
import com.example.supplychain.dto.DeliveryPerformanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipmentService {@Autowired
	private ShipmentRepository shipmentRepository;

	public Shipment createShipment(Shipment shipment) {
	    return shipmentRepository.save(shipment);
	}

	public List<Shipment> getAllShipments() {
	    return shipmentRepository.findAll();
	}

	public Shipment getShipmentById(Long id) {
	    return shipmentRepository.findById(id).orElse(null);
	}

	public Shipment updateShipmentStatus(Long id, ShipmentStatus status) {
	    Shipment shipment = shipmentRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Shipment not found with id: " + id));
	    shipment.setStatus(status);
	    return shipmentRepository.save(shipment);
	}

	// ✅ New method to calculate delivery performance for a supplier
	public DeliveryPerformanceDTO getDeliveryPerformanceForSupplier(String supplierName) {
	    List<Shipment> shipments = shipmentRepository.findBySupplierName(supplierName);

	    long total = shipments.size();
	    long onTime = 0;
	    long late = 0;
	    long totalDelayHours = 0;

	    for (Shipment shipment : shipments) {
	        if (shipment.getStatus() == ShipmentStatus.DELIVERED && shipment.getExpectedArrivalTime() != null) {
	            LocalDateTime expected = shipment.getExpectedArrivalTime();
	            LocalDateTime actual = shipment.getDepartureTime(); // replace this with actual arrival if available
	            if (actual != null) {
	                if (!actual.isAfter(expected)) {
	                    onTime++;
	                } else {
	                    late++;
	                    totalDelayHours += Duration.between(expected, actual).toHours();
	                }
	            }
	        }
	    }

	    double avgDelay = (late > 0) ? (double) totalDelayHours / late : 0;

	    return new DeliveryPerformanceDTO(supplierName ,total, onTime, late, avgDelay);
	}

	public void setShipmentRepository(ShipmentRepository shipmentRepository2) {
		
		
	}
}