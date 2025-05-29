package com.example.supplychain.shipment.repository;

import com.example.supplychain.shipment.entity.Checkpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {
    
    // Find all checkpoints for a specific shipment
    List<Checkpoint> findByShipmentIdOrderByTimestampDesc(Long shipmentId);
    
    // Find checkpoints by location
    List<Checkpoint> findByLocationContainingIgnoreCase(String location);
    
    // Find checkpoints by status
    List<Checkpoint> findByStatus(String status);
}
