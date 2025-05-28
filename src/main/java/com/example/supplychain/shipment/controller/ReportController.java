package com.example.supplychain.shipment.controller;

import com.example.supplychain.shipment.dto.DeliveryPerformanceDTO;
import com.example.supplychain.shipment.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/delivery-performance")
    public ResponseEntity<List<DeliveryPerformanceDTO>> getDeliveryPerformanceReport() {
        List<DeliveryPerformanceDTO> report = shipmentService.getDeliveryPerformanceReport();
        return ResponseEntity.ok(report);
    }
}
