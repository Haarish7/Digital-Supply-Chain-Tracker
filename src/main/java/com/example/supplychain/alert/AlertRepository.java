package com.example.supplychain.alert;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByResolvedFalse();
    List<Alert> findByShipmentTrackingNumber(String trackingNumber);
}
