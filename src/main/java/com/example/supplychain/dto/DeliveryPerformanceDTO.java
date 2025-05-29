package com.example.supplychain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryPerformanceDTO {
    private String supplierName;
    private double performancePercentage;
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public double getPerformancePercentage() {
		return performancePercentage;
	}
	public void setPerformancePercentage(double performancePercentage) {
		this.performancePercentage = performancePercentage;
	}
}




