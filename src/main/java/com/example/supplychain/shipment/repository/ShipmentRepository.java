package com.example.supplychain.shipment.repository;

import com.example.supplychain.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
Shipment findByTrackingNumber(String trackingNumber);// ✅ New method to support delivery performance report
List<Shipment> findBySupplierName(String supplierName);
}