package com.example.supplychain.shipment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.supplychain.entity.Alert;
import com.example.supplychain.repository.AlertRepository;

@Service
public class AlertService {
	@Autowired
	private AlertRepository alertRepository;

	public Alert createAlert(String message, String type) {
	    Alert alert = new Alert();
	    alert.setMessage(message);
	    alert.setType(type);
	    alert.setCreatedAt(LocalDateTime.now());
	    return alertRepository.save(alert);
	}

	public List<Alert> getActiveAlerts() {
	    return alertRepository.findByResolvedFalse();
	}

	public Alert resolveAlert(Long id) {
	    Alert alert = alertRepository.findById(id)
	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alert not found with id: " + id));

	    alert.setResolved(true);
	    return alertRepository.save(alert);
	}

}