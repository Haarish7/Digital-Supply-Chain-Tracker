package com.example.supplychain.shipment.service;

import com.example.supplychain.entity.Alert;
import com.example.supplychain.repository.AlertRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.*;

//import static org.junit.jupiter.api.Assertions.;
//import static org.mockito.Mockito.;

class AlertServiceTest {
	@Mock
	private AlertRepository alertRepository;

	@InjectMocks
	private AlertService alertService;

	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateAlert() {
	    String message = "Temperature high";
	    String type = "Warning";

	    Alert mockAlert = new Alert();
	    mockAlert.setMessage(message);
	    mockAlert.setType(type);
	    mockAlert.setCreatedAt(LocalDateTime.now());

	    when(alertRepository.save(any(Alert.class))).thenReturn(mockAlert);

	    Alert result = alertService.createAlert(message, type);

	    assertEquals(message, result.getMessage());
	    assertEquals(type, result.getType());
	    assertNotNull(result.getCreatedAt());
	    verify(alertRepository).save(any(Alert.class));
	}

	@Test
	void testGetActiveAlerts() {
	    List<Alert> mockAlerts = List.of(new Alert(), new Alert());

	    when(alertRepository.findByResolvedFalse()).thenReturn(mockAlerts);

	    List<Alert> result = alertService.getActiveAlerts();

	    assertEquals(2, result.size());
	    verify(alertRepository).findByResolvedFalse();
	}

	@Test
	void testResolveAlert_Success() {
	    Long id = 1L;
	    Alert alert = new Alert();
	    alert.setResolved(false);

	    when(alertRepository.findById(id)).thenReturn(Optional.of(alert));
	    when(alertRepository.save(alert)).thenReturn(alert);

	    Alert result = alertService.resolveAlert(id);

	    assertTrue(result.isResolved());
	    verify(alertRepository).findById(id);
	    verify(alertRepository).save(alert);
	}

	@Test
	void testResolveAlert_NotFound() {
	    Long id = 2L;
	    when(alertRepository.findById(id)).thenReturn(Optional.empty());

	    ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
	        alertService.resolveAlert(id);
	    });

	    assertEquals("404 NOT_FOUND \"Alert not found with id: 2\"", exception.getMessage());
	}

}