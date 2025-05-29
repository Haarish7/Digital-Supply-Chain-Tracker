package com.example.supplychain.shipment.service;

import com.example.supplychain.enums.ShipmentStatus;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    // Generate report for today
    public Map<ShipmentStatus, Long> generateDailyReport() {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        List<Shipment> shipments = shipmentRepository.findAllByLastCheckpointTimeBetween(startOfDay, endOfDay);
        return groupByStatus(shipments);
    }

    // Generate report for the past 7 days
    public Map<ShipmentStatus, Long> generateWeeklyReport() {
        LocalDateTime end = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime start = end.minusDays(7);

        List<Shipment> shipments = shipmentRepository.findAllByLastCheckpointTimeBetween(start, end);
        return groupByStatus(shipments);
    }

    // Group shipment list by status and count each
    private Map<ShipmentStatus, Long> groupByStatus(List<Shipment> shipments) {
        Map<ShipmentStatus, Long> report = new EnumMap<>(ShipmentStatus.class);
        for (ShipmentStatus status : ShipmentStatus.values()) {
            report.put(status, 0L);
        }

        for (Shipment shipment : shipments) {
            ShipmentStatus status = shipment.getStatus();
            report.put(status, report.get(status) + 1);
        }

        return report;
    }

    public List<Shipment> getDailyReport() {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();
        return shipmentRepository.findAllByLastCheckpointTimeBetween(startOfDay, endOfDay);
    }

    public List<Shipment> getWeeklyReport() {
        LocalDateTime end = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime start = end.minusDays(7);
        return shipmentRepository.findAllByLastCheckpointTimeBetween(start, end);
    }
}