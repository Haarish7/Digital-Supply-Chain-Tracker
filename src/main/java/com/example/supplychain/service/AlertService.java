package com.example.supplychain.service;

import com.example.supplychain.entity.Alert;
import com.example.supplychain.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public Alert createAlert(String message, String type) {
        Alert alert = new Alert();
        alert.setMessage(message);
        alert.setType(type);
        alert.setCreatedAt(LocalDateTime.now());
        alert.setResolved(false);
        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public List<Alert> getUnresolvedAlerts() {
        return alertRepository.findByResolvedFalse();
    }

    public Alert resolveAlert(Long id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setResolved(true);
        return alertRepository.save(alert);
    }
}
