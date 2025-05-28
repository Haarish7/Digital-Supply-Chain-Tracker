package com.example.supplychain.shipment.service;

import com.example.supplychain.enums.ShipmentStatus;
import com.example.supplychain.shipment.dto.DeliveryPerformanceDTO;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.repository.ShipmentRepository;
import com.example.supplychain.entity.User; // ✅ Correct import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ShipmentService {

    @Autowired
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

    // ✅ Method to update status
    public Shipment updateShipmentStatus(Long id, ShipmentStatus status) {
        Shipment shipment = shipmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Shipment not found with id: " + id));

        shipment.setStatus(status);
        return shipmentRepository.save(shipment);
    }

    // ✅ Delivery Performance Report
    public List<DeliveryPerformanceDTO> getDeliveryPerformanceReport() {
        List<Shipment> shipments = shipmentRepository.findAll();

        Map<User, List<Shipment>> shipmentsByUser = shipments.stream()
                .collect(Collectors.groupingBy(Shipment::getUser));

        return shipmentsByUser.entrySet().stream()
                .map(entry -> {
                    User user = entry.getKey();
                    List<Shipment> userShipments = entry.getValue();

                    long total = userShipments.size();
                    long onTime = userShipments.stream()
                            .filter(s -> s.getExpectedArrivalTime() != null &&
                                         s.getDepartureTime() != null &&
                                         s.getExpectedArrivalTime().isAfter(s.getDepartureTime()) &&
                                         s.getStatus() == ShipmentStatus.DELIVERED)
                            .count();

                    long late = total - onTime;

                    return new DeliveryPerformanceDTO(
                            user.getUsername(), // Make sure User has getUsername()
                            total,
                            onTime,
                            late
                    );
                })
                .collect(Collectors.toList());
    }
}
