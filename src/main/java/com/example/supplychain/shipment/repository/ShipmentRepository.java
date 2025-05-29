package com.example.supplychain.shipment.repository;

import com.example.supplychain.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findBySupplierName(String supplierName);

    // You can add other query methods here as needed, using properties that exist in Shipment.java
}
