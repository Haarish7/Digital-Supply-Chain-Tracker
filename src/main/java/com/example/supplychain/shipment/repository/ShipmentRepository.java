package com.example.supplychain.shipment.repository;

import com.example.supplychain.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {


    Shipment findByTrackingNumber(String trackingNumber);

   
}
