package com.example.supplychain.shipment.controller;

import com.example.supplychain.dto.DeliveryPerformanceDTO;
import com.example.supplychain.enums.ShipmentStatus;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.service.ShipmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ShipmentController.class)
class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService shipmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateShipment() throws Exception {
        Shipment shipment = new Shipment(1L, "TRK123", "Delhi", "Mumbai",
                LocalDateTime.now(), LocalDateTime.now().plusDays(2), null, "ABC Logistics", ShipmentStatus.DISPATCHED);

        Mockito.when(shipmentService.createShipment(any(Shipment.class))).thenReturn(shipment);

        mockMvc.perform(post("/api/shipments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shipment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trackingNumber").value("TRK123"));
    }

    @Test
    void testGetAllShipments() throws Exception {
        Shipment shipment = new Shipment(1L, "TRK123", "Delhi", "Mumbai",
                LocalDateTime.now(), LocalDateTime.now().plusDays(2), null, "ABC Logistics", ShipmentStatus.DISPATCHED);

        Mockito.when(shipmentService.getAllShipments()).thenReturn(List.of(shipment));

        mockMvc.perform(get("/api/shipments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].trackingNumber").value("TRK123"));
    }

    @Test
    void testGetShipmentById() throws Exception {
        Shipment shipment = new Shipment(1L, "TRK123", "Delhi", "Mumbai",
                LocalDateTime.now(), LocalDateTime.now().plusDays(2), null, "ABC Logistics", ShipmentStatus.DISPATCHED);

        Mockito.when(shipmentService.getShipmentById(1L)).thenReturn(shipment);

        mockMvc.perform(get("/api/shipments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trackingNumber").value("TRK123"));
    }

    @Test
    void testUpdateShipmentStatus() throws Exception {
        Shipment updated = new Shipment(1L, "TRK123", "Delhi", "Mumbai",
                LocalDateTime.now(), LocalDateTime.now().plusDays(2), null, "ABC Logistics", ShipmentStatus.ARRIVED);

        Mockito.when(shipmentService.updateShipmentStatus(1L, ShipmentStatus.ARRIVED)).thenReturn(updated);

        mockMvc.perform(put("/api/shipments/1/status")
                        .param("status", "ARRIVED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ARRIVED"));
    }

    @Test
    void testGetPerformanceBySupplier() throws Exception {
        DeliveryPerformanceDTO performanceDTO = new DeliveryPerformanceDTO("ABC Logistics", 10, 7, 3, 5.2);

        Mockito.when(shipmentService.getDeliveryPerformanceForSupplier("ABC Logistics")).thenReturn(performanceDTO);

        mockMvc.perform(get("/api/shipments/performance/ABC Logistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.supplierName").value("ABC Logistics"))
                .andExpect(jsonPath("$.totalShipments").value(10))
                .andExpect(jsonPath("$.onTimeDeliveries").value(7));
    }
}
