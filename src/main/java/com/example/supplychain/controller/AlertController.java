package com.example.supplychain.controller;

import com.example.supplychain.entity.Alert;
import com.example.supplychain.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public Alert createAlert(@RequestParam String message, @RequestParam String type) {
        return alertService.createAlert(message, type);
    }

    @PostMapping("/damage")
    public Alert createDamageAlert(@RequestParam String message) {
        return alertService.createDamageAlert(message);
    }

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @GetMapping("/unresolved")
    public List<Alert> getUnresolvedAlerts() {
        return alertService.getUnresolvedAlerts();
    }

    @PutMapping("/{id}/resolve")
    public Alert resolveAlert(@PathVariable Long id) {
        return alertService.resolveAlert(id);
    }
}
