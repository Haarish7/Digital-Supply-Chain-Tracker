package com.example.supplychain.controller;

import com.example.supplychain.alert.Alert;
import com.example.supplychain.alert.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlertcontrollersTest {

    @Mock
    private AlertService alertService;

    @InjectMocks
    private Alertcontrollers alertcontrollers;

    private Alert alert1;
    private Alert alert2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        alert1 = Alert.builder()
                .id(1L)
                .shipmentTrackingNumber("TRK123")
                .message("Delayed")
                .alertType("DELAY")
                .createdAt(LocalDateTime.now())
                .resolved(false)
                .build();

        alert2 = Alert.builder()
                .id(2L)
                .shipmentTrackingNumber("TRK123")
                .message("Very Late")
                .alertType("DELAY")
                .createdAt(LocalDateTime.now())
                .resolved(false)
                .build();
    }

    @Test
    public void testGetAllAlerts() {
        when(alertService.getAllAlerts()).thenReturn(Arrays.asList(alert1, alert2));
        List<Alert> alerts = alertcontrollers.getAllAlerts();

        assertEquals(2, alerts.size());
        verify(alertService, times(1)).getAllAlerts();
    }

    @Test
    public void testGetUnresolvedAlerts() {
        when(alertService.getUnresolvedAlerts()).thenReturn(Arrays.asList(alert1, alert2));
        List<Alert> alerts = alertcontrollers.getUnresolvedAlerts();

        assertFalse(alerts.isEmpty());
        verify(alertService, times(1)).getUnresolvedAlerts();
    }

    @Test
    public void testGetAlertsByTrackingNumber() {
        when(alertService.getAlertsByTrackingNumber("TRK123")).thenReturn(Arrays.asList(alert1, alert2));
        List<Alert> alerts = alertcontrollers.getAlertsByTrackingNumber("TRK123");

        assertEquals(2, alerts.size());
        verify(alertService, times(1)).getAlertsByTrackingNumber("TRK123");
    }

    @Test
    public void testResolveAlert() {
        alert1.setResolved(true);
        when(alertService.markAlertAsResolved(1L)).thenReturn(alert1);

        Alert resolvedAlert = alertcontrollers.resolveAlert(1L);
        assertTrue(resolvedAlert.isResolved());
        verify(alertService, times(1)).markAlertAsResolved(1L);
    }
}
