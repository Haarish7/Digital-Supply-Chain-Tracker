package com.example.supplychain.shipment.service;

import com.example.supplychain.dto.DeliveryPerformanceDTO;
import com.example.supplychain.enums.ShipmentStatus;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShipmentServiceTest {

    @InjectMocks
    private ShipmentService shipmentService;

    @Mock
    private ShipmentRepository shipmentRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDeliveryPerformanceForSupplier() {
        String supplierName = "ABC Suppliers";

        LocalDateTime now = LocalDateTime.now();

        Shipment onTimeShipment = Shipment.builder()
                .status(ShipmentStatus.DELIVERED)
                .expectedArrivalTime(now.plusDays(1))
                .departureTime(now.plusDays(1).minusHours(1))
                .build();

        Shipment lateShipment = Shipment.builder()
                .status(ShipmentStatus.DELIVERED)
                .expectedArrivalTime(now.plusDays(1))
                .departureTime(now.plusDays(1).plusHours(2))
                .build();

        List<Shipment> shipments = Arrays.asList(onTimeShipment, lateShipment);

        when(shipmentRepository.findBySupplierName(supplierName)).thenReturn(shipments);

        DeliveryPerformanceDTO report = shipmentService.getDeliveryPerformanceForSupplier(supplierName);

        assertEquals(supplierName, report.getSupplierName());
        assertEquals(2, report.getTotalShipments());
        assertEquals(1, report.getOnTimeDeliveries());
        assertEquals(1, report.getLateDeliveries());
        assertEquals(2.0, report.getAverageDelayInHours(), 0.01);
    }
}
