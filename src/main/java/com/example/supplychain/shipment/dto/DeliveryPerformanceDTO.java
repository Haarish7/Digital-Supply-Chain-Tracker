package com.example.supplychain.shipment.dto;

public class DeliveryPerformanceDTO {
    private String userName;
    private long totalShipments;
    private long onTimeDeliveries;
    private long lateDeliveries;

    public DeliveryPerformanceDTO(String userName, long totalShipments, long onTimeDeliveries, long lateDeliveries) {
        this.userName = userName;
        this.totalShipments = totalShipments;
        this.onTimeDeliveries = onTimeDeliveries;
        this.lateDeliveries = lateDeliveries;
    }

    // Getters & setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTotalShipments() {
        return totalShipments;
    }

    public void setTotalShipments(long totalShipments) {
        this.totalShipments = totalShipments;
    }

    public long getOnTimeDeliveries() {
        return onTimeDeliveries;
    }

    public void setOnTimeDeliveries(long onTimeDeliveries) {
        this.onTimeDeliveries = onTimeDeliveries;
    }

    public long getLateDeliveries() {
        return lateDeliveries;
    }

    public void setLateDeliveries(long lateDeliveries) {
        this.lateDeliveries = lateDeliveries;
    }
}
