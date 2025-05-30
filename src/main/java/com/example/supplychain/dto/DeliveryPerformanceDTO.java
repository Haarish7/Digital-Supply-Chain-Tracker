package com.example.supplychain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPerformanceDTO {
    private String supplierName;
    private long totalShipments;
    private long onTimeDeliveries;
    private long lateDeliveries;
    private double averageDelayInHours;
	public DeliveryPerformanceDTO(String supplierName2, long total, long onTime, long late, double avgDelay) {
		// TODO Auto-generated constructor stub
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
	public double getAverageDelayInHours() {
		return averageDelayInHours;
	}
	public void setAverageDelayInHours(double averageDelayInHours) {
		this.averageDelayInHours = averageDelayInHours;
	}
}
