package com.example.supplychain.shipment.service;

import com.example.supplychain.dto.CheckpointDto;
import com.example.supplychain.shipment.entity.Checkpoint;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.repository.CheckpointRepository;
import com.example.supplychain.shipment.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckpointServiceTest {

    @Mock
    private CheckpointRepository checkpointRepository;

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CheckpointService checkpointService;

    private Shipment shipment;
    private Checkpoint checkpoint;
    private CheckpointDto checkpointDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        shipment = new Shipment();
        shipment.setId(1L);

        checkpoint = new Checkpoint();
        checkpoint.setId(1L);
        checkpoint.setShipment(shipment);
        checkpoint.setLocation("Warehouse");
        checkpoint.setCity("CityA");
        checkpoint.setState("StateA");
        checkpoint.setCountry("CountryA");
        checkpoint.setPostalCode("12345");
        checkpoint.setTimestamp(LocalDateTime.now());
        checkpoint.setMessage("Package received");
        checkpoint.setStatus("Received");
        checkpoint.setLatitude(10.0);
        checkpoint.setLongitude(20.0);

        checkpointDto = new CheckpointDto();
        checkpointDto.setLocation("Warehouse");
        checkpointDto.setCity("CityA");
        checkpointDto.setState("StateA");
        checkpointDto.setCountry("CountryA");
        checkpointDto.setPostalCode("12345");
        checkpointDto.setTimestamp(LocalDateTime.now());
        checkpointDto.setMessage("Package received");
        checkpointDto.setStatus("Received");
        checkpointDto.setLatitude(10.0);
        checkpointDto.setLongitude(20.0);
    }

    @Test
    void testAddCheckpoint_success() {
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(checkpointRepository.save(any(Checkpoint.class))).thenReturn(checkpoint);
        when(modelMapper.map(checkpoint, CheckpointDto.class)).thenReturn(checkpointDto);

        CheckpointDto result = checkpointService.addCheckpoint(1L, checkpointDto);

        assertNotNull(result);
        assertEquals("Warehouse", result.getLocation());
        verify(shipmentRepository).findById(1L);
        verify(checkpointRepository).save(any(Checkpoint.class));
        verify(modelMapper).map(checkpoint, CheckpointDto.class);
    }

    @Test
    void testAddCheckpoint_shipmentNotFound() {
        when(shipmentRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> checkpointService.addCheckpoint(1L, checkpointDto));

        assertEquals("Shipment not found with id: 1", exception.getMessage());
    }

    @Test
    void testGetCheckpointsByShipment() {
        when(checkpointRepository.findByShipmentIdOrderByTimestampDesc(1L))
            .thenReturn(List.of(checkpoint));
        when(modelMapper.map(checkpoint, CheckpointDto.class)).thenReturn(checkpointDto);

        List<CheckpointDto> result = checkpointService.getCheckpointsByShipment(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Warehouse", result.get(0).getLocation());
        assertEquals(1L, result.get(0).getShipmentId());
    }

    @Test
    void testUpdateCheckpoint_success() {
        when(checkpointRepository.findById(1L)).thenReturn(Optional.of(checkpoint));
        when(checkpointRepository.save(checkpoint)).thenReturn(checkpoint);
        when(modelMapper.map(checkpoint, CheckpointDto.class)).thenReturn(checkpointDto);

        checkpointDto.setLocation("New Location");
        checkpointDto.setCity("New City");

        CheckpointDto updatedDto = checkpointService.updateCheckpoint(1L, checkpointDto);

        assertNotNull(updatedDto);
        assertEquals("New Location", updatedDto.getLocation());
        assertEquals("New City", updatedDto.getCity());
        verify(checkpointRepository).save(checkpoint);
    }

    @Test
    void testUpdateCheckpoint_notFound() {
        when(checkpointRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> checkpointService.updateCheckpoint(1L, checkpointDto));

        assertEquals("Checkpoint not found with id: 1", exception.getMessage());
    }

    @Test
    void testDeleteCheckpoint() {
        doNothing().when(checkpointRepository).deleteById(1L);

        checkpointService.deleteCheckpoint(1L);

        verify(checkpointRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllCheckpoints() {
        when(checkpointRepository.findAll()).thenReturn(Arrays.asList(checkpoint));
        when(modelMapper.map(checkpoint, CheckpointDto.class)).thenReturn(checkpointDto);

        List<CheckpointDto> result = checkpointService.getAllCheckpoints();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Warehouse", result.get(0).getLocation());
    }
}
