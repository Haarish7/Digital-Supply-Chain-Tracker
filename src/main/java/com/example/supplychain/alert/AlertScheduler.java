package com.example.supplychain.alert;

import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.repository.ShipmentRepository;
import com.example.supplychain.enums.ShipmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AlertScheduler {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private AlertService alertService;

    // Runs every 30 minutes
    @Scheduled(fixedRate = 1800000)
    public void checkForDelayedShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();

        for (Shipment shipment : shipments) {
            if (shipment.getExpectedArrivalTime() != null
                    && shipment.getStatus() != ShipmentStatus.DELIVERED
                    && shipment.getExpectedArrivalTime().isBefore(LocalDateTime.now())) {

                alertService.sendAlert(
                        shipment.getTrackingNumber(),
                        "Shipment delayed: " + shipment.getTrackingNumber(),
                        "DELAYED"
                );
            }
        }
    }
}
