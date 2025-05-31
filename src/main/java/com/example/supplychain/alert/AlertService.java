package com.example.supplychain.alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    // Send (create) a new alert
    public void sendAlert(String shipmentTrackingNumber, String message, String alertType) {
        Alert alert = Alert.builder()
                .shipmentTrackingNumber(shipmentTrackingNumber)
                .message(message)
                .alertType(alertType)
                .createdAt(LocalDateTime.now())
                .resolved(false) // explicitly mark unresolved
                .build();

        alertRepository.save(alert);
    }

    // Get all alerts
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    // Get unresolved alerts
    public List<Alert> getUnresolvedAlerts() {
        return alertRepository.findByResolvedFalse();
    }

    // Get alerts by shipment tracking number
    public List<Alert> getAlertsByTrackingNumber(String trackingNumber) {
        return alertRepository.findByShipmentTrackingNumber(trackingNumber);
    }

    // Mark an alert as resolved
    public Alert markAlertAsResolved(Long id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with id: " + id));
        alert.setResolved(true);
        return alertRepository.save(alert);
    }
}
