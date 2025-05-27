package com.example.supplychain.shipment.repository;

import com.example.supplychain.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Shipment findByTrackingNumber(String trackingNumber);
}
