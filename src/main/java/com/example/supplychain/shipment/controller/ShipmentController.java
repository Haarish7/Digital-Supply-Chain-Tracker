package com.example.supplychain.shipment.controller;

import com.example.supplychain.dto.DeliveryPerformanceDTO;
import com.example.supplychain.enums.ShipmentStatus;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {@Autowired
	private ShipmentService shipmentService;

	@PostMapping
	public Shipment createShipment(@RequestBody Shipment shipment) {
	    return shipmentService.createShipment(shipment);
	}

	@GetMapping
	public List<Shipment> getAllShipments() {
	    return shipmentService.getAllShipments();
	}

	@GetMapping("/{id}")
	public Shipment getShipmentById(@PathVariable Long id) {
	    return shipmentService.getShipmentById(id);
	}

	// ✅ New endpoint to update shipment status
	@PutMapping("/{id}/status")
	public Shipment updateShipmentStatus(
	        @PathVariable Long id,
	        @RequestParam ShipmentStatus status
	) {
	    return shipmentService.updateShipmentStatus(id, status);
	}

	// ✅ Endpoint to get Delivery Performance per Supplier
	@GetMapping("/performance/{supplierName}")
	public DeliveryPerformanceDTO getPerformance(@PathVariable String supplierName) {
	    return shipmentService.getDeliveryPerformanceForSupplier(supplierName);
	}
}