package com.example.supplychain.shipment.service;

import com.example.supplychain.dto.DeliveryPerformanceDTO;
import com.example.supplychain.enums.ShipmentStatus;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShipmentServiceTest {

    private ShipmentRepository shipmentRepository;
    private ShipmentService shipmentService;

    @BeforeEach
    void setUp() {
        shipmentRepository = mock(ShipmentRepository.class);
        shipmentService = new ShipmentService();
        shipmentService.setShipmentRepository(shipmentRepository); // Field injection
    }

    @Test
    void testCreateShipment() {
        Shipment shipment = new Shipment();
        when(shipmentRepository.save(shipment)).thenReturn(shipment);

        Shipment result = shipmentService.createShipment(shipment);
        assertNotNull(result);
        verify(shipmentRepository, times(1)).save(shipment);
    }

    @Test
    void testGetAllShipments() {
        List<Shipment> mockList = Arrays.asList(new Shipment(), new Shipment());
        when(shipmentRepository.findAll()).thenReturn(mockList);

        List<Shipment> result = shipmentService.getAllShipments();
        assertEquals(2, result.size());
    }

    @Test
    void testGetShipmentById() {
        Shipment shipment = new Shipment();
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));

        Shipment result = shipmentService.getShipmentById(1L);
        assertNotNull(result);
    }

    @Test
    void testUpdateShipmentStatus() {
        Shipment shipment = new Shipment();
        shipment.setStatus(ShipmentStatus.IN_TRANSIT);
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(shipmentRepository.save(ArgumentMatchers.any(Shipment.class))).thenReturn(shipment);

        Shipment result = shipmentService.updateShipmentStatus(1L, ShipmentStatus.DELIVERED);
        assertEquals(ShipmentStatus.DELIVERED, result.getStatus());
    }

    @Test
    void testGetDeliveryPerformanceForSupplier() {
        String supplierName = "Acme Inc";

        Shipment onTimeShipment = new Shipment();
        onTimeShipment.setStatus(ShipmentStatus.DELIVERED);
        onTimeShipment.setExpectedArrivalTime(LocalDateTime.now().plusHours(1));
        onTimeShipment.setDepartureTime(LocalDateTime.now());
        onTimeShipment.setSupplierName(supplierName); // Ensure correct supplier

        Shipment lateShipment = new Shipment();
        lateShipment.setStatus(ShipmentStatus.DELIVERED);
        lateShipment.setExpectedArrivalTime(LocalDateTime.now().minusHours(2));
        lateShipment.setDepartureTime(LocalDateTime.now());
        lateShipment.setSupplierName(supplierName); // Ensure correct supplier

        when(shipmentRepository.findBySupplierName(supplierName))
            .thenReturn(Arrays.asList(onTimeShipment, lateShipment));

        DeliveryPerformanceDTO result = shipmentService.getDeliveryPerformanceForSupplier(supplierName);

        assertEquals(2, result.getTotalShipments());
        assertEquals(1, result.getOnTimeDeliveries());
        assertEquals(1, result.getLateDeliveries());
        assertTrue(result.getAverageDelayInHours() > 0);
    }
}
