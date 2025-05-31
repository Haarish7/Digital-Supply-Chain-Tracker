package com.example.supplychain.controller;

import com.example.supplychain.alert.Alert;
import com.example.supplychain.alert.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class Alertcontrollers {

    private final AlertService alertService;

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @GetMapping("/unresolved")
    public List<Alert> getUnresolvedAlerts() {
        return alertService.getUnresolvedAlerts();
    }

    @GetMapping("/shipment/{trackingNumber}")
    public List<Alert> getAlertsByTrackingNumber(@PathVariable String trackingNumber) {
        return alertService.getAlertsByTrackingNumber(trackingNumber);
    }

    @PutMapping("/{id}/resolve")
    public Alert resolveAlert(@PathVariable Long id) {
        return alertService.markAlertAsResolved(id);
    }
}
