package com.example.supplychain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.supplychain.entity.Alert;
import com.example.supplychain.shipment.service.AlertService;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {
	@Autowired
	private AlertService alertService;

	// GET /api/alerts - retrieve all unresolved alerts
	@GetMapping
	public ResponseEntity<List<Alert>> getActiveAlerts() {
	    List<Alert> alerts = alertService.getActiveAlerts();
	    return ResponseEntity.ok(alerts);
	}

	// POST /api/alerts/{id}/resolve - mark an alert as resolved
	@PostMapping("/{id}/resolve")
	public ResponseEntity<Alert> resolveAlert(@PathVariable Long id) {
	    Alert resolved = alertService.resolveAlert(id);
	    return ResponseEntity.ok(resolved);
	}

	// ✅ NEW: POST /api/alerts/create - create an alert with message and type
	@PostMapping("/create")
	public ResponseEntity<Alert> createAlert(
	        @RequestParam String message,
	        @RequestParam String type) {
	    Alert alert = alertService.createAlert(message, type);
	    return ResponseEntity.ok(alert);
	}

}