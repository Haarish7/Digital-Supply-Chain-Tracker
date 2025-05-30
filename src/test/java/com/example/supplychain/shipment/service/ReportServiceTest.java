package com.example.supplychain.shipment.service;

import com.example.supplychain.enums.ShipmentStatus;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Shipment createShipment(ShipmentStatus status) {
        Shipment shipment = new Shipment();
        shipment.setStatus(status);
        shipment.setLastCheckpointTime(LocalDateTime.now(ZoneId.systemDefault()));
        return shipment;
    }

    @Test
    void testGenerateDailyReport() {
        // Prepare test data
        Shipment shipment1 = createShipment(ShipmentStatus.IN_TRANSIT);
        Shipment shipment2 = createShipment(ShipmentStatus.DELIVERED);
        Shipment shipment3 = createShipment(ShipmentStatus.IN_TRANSIT);

        List<Shipment> shipments = Arrays.asList(shipment1, shipment2, shipment3);

        // Capture time arguments to verify
        ArgumentCaptor<LocalDateTime> startCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
        ArgumentCaptor<LocalDateTime> endCaptor = ArgumentCaptor.forClass(LocalDateTime.class);

        when(shipmentRepository.findAllByLastCheckpointTimeBetween(startCaptor.capture(), endCaptor.capture()))
            .thenReturn(shipments);

        Map<ShipmentStatus, Long> report = reportService.generateDailyReport();

        assertNotNull(report);
        assertEquals(3, report.values().stream().mapToLong(Long::longValue).sum());

        assertEquals(2L, report.get(ShipmentStatus.IN_TRANSIT));
        assertEquals(1L, report.get(ShipmentStatus.DELIVERED));
        assertEquals(0L, report.get(ShipmentStatus.PENDING));
        assertEquals(0L, report.get(ShipmentStatus.CANCELLED));

        // Verify that the repository was called with correct date range
        LocalDateTime start = startCaptor.getValue();
        LocalDateTime end = endCaptor.getValue();
        assertTrue(start.isBefore(end));
    }

    @Test
    void testGenerateWeeklyReport() {
        Shipment shipment1 = createShipment(ShipmentStatus.PENDING);
        Shipment shipment2 = createShipment(ShipmentStatus.CANCELLED);

        List<Shipment> shipments = Arrays.asList(shipment1, shipment2);

        ArgumentCaptor<LocalDateTime> startCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
        ArgumentCaptor<LocalDateTime> endCaptor = ArgumentCaptor.forClass(LocalDateTime.class);

        when(shipmentRepository.findAllByLastCheckpointTimeBetween(startCaptor.capture(), endCaptor.capture()))
            .thenReturn(shipments);

        Map<ShipmentStatus, Long> report = reportService.generateWeeklyReport();

        assertNotNull(report);
        assertEquals(2, report.values().stream().mapToLong(Long::longValue).sum());

        assertEquals(1L, report.get(ShipmentStatus.PENDING));
        assertEquals(1L, report.get(ShipmentStatus.CANCELLED));

        LocalDateTime start = startCaptor.getValue();
        LocalDateTime end = endCaptor.getValue();
        assertTrue(start.isBefore(end));
    }

    @Test
    void testGetDailyReport() {
        Shipment shipment = createShipment(ShipmentStatus.IN_TRANSIT);
        List<Shipment> shipments = Collections.singletonList(shipment);

        LocalDateTime startOfDay = LocalDateTime.now(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        when(shipmentRepository.findAllByLastCheckpointTimeBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
            .thenReturn(shipments);

        List<Shipment> result = reportService.getDailyReport();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ShipmentStatus.IN_TRANSIT, result.get(0).getStatus());
    }

    @Test
    void testGetWeeklyReport() {
        Shipment shipment = createShipment(ShipmentStatus.DELIVERED);
        List<Shipment> shipments = Collections.singletonList(shipment);

        when(shipmentRepository.findAllByLastCheckpointTimeBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
            .thenReturn(shipments);

        List<Shipment> result = reportService.getWeeklyReport();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ShipmentStatus.DELIVERED, result.get(0).getStatus());
    }
}
