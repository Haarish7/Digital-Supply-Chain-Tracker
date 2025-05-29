package com.example.supplychain.shipment.controller;

import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ShipmentReportController {
	@Autowired
	private ReportService reportService;

	@GetMapping("/daily")
	public List<Shipment> getDailyReport() {
	    return reportService.getDailyReport();
	}

	@GetMapping("/weekly")
	public List<Shipment> getWeeklyReport() {
	    return reportService.getWeeklyReport();
	}

}