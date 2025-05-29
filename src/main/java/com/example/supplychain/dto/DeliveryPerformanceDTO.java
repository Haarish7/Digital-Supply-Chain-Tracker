package com.example.supplychain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryPerformanceDTO {
    private String supplierName;
    private long totalShipments;
    private long onTimeDeliveries;
    private long lateDeliveries;
    private double averageDelayInHours;
}
