package com.example.supplychain.shipment.service;

import com.example.supplychain.dto.CheckpointDto;
import com.example.supplychain.shipment.entity.Checkpoint;
import com.example.supplychain.shipment.entity.Shipment;
import com.example.supplychain.shipment.repository.CheckpointRepository;
import com.example.supplychain.shipment.repository.ShipmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckpointService {

    @Autowired
    private CheckpointRepository checkpointRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CheckpointDto addCheckpoint(Long shipmentId, CheckpointDto checkpointDto) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
            .orElseThrow(() -> new RuntimeException("Shipment not found with id: " + shipmentId));

        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setShipment(shipment);
        checkpoint.setLocation(checkpointDto.getLocation());
        checkpoint.setCity(checkpointDto.getCity());
        checkpoint.setState(checkpointDto.getState());
        checkpoint.setCountry(checkpointDto.getCountry());
        checkpoint.setPostalCode(checkpointDto.getPostalCode());
        checkpoint.setTimestamp(checkpointDto.getTimestamp() != null ? 
            checkpointDto.getTimestamp() : LocalDateTime.now());
        checkpoint.setMessage(checkpointDto.getMessage());
        checkpoint.setStatus(checkpointDto.getStatus());
        checkpoint.setLatitude(checkpointDto.getLatitude());
        checkpoint.setLongitude(checkpointDto.getLongitude());

        Checkpoint savedCheckpoint = checkpointRepository.save(checkpoint);
        return modelMapper.map(savedCheckpoint, CheckpointDto.class);
    }

    public List<CheckpointDto> getCheckpointsByShipment(Long shipmentId) {
        List<Checkpoint> checkpoints = checkpointRepository.findByShipmentIdOrderByTimestampDesc(shipmentId);
        return checkpoints.stream()
            .map(checkpoint -> {
                CheckpointDto dto = modelMapper.map(checkpoint, CheckpointDto.class);
                dto.setShipmentId(checkpoint.getShipment().getId());
                return dto;
            })
            .collect(Collectors.toList());
    }

    public CheckpointDto updateCheckpoint(Long checkpointId, CheckpointDto checkpointDto) {
        Checkpoint checkpoint = checkpointRepository.findById(checkpointId)
            .orElseThrow(() -> new RuntimeException("Checkpoint not found with id: " + checkpointId));

        checkpoint.setLocation(checkpointDto.getLocation());
        checkpoint.setCity(checkpointDto.getCity());
        checkpoint.setState(checkpointDto.getState());
        checkpoint.setCountry(checkpointDto.getCountry());
        checkpoint.setPostalCode(checkpointDto.getPostalCode());
        checkpoint.setMessage(checkpointDto.getMessage());
        checkpoint.setStatus(checkpointDto.getStatus());
        checkpoint.setLatitude(checkpointDto.getLatitude());
        checkpoint.setLongitude(checkpointDto.getLongitude());

        Checkpoint updatedCheckpoint = checkpointRepository.save(checkpoint);
        CheckpointDto dto = modelMapper.map(updatedCheckpoint, CheckpointDto.class);
        dto.setShipmentId(updatedCheckpoint.getShipment().getId());
        return dto;
    }

    public void deleteCheckpoint(Long checkpointId) {
        checkpointRepository.deleteById(checkpointId);
    }

    public List<CheckpointDto> getAllCheckpoints() {
        List<Checkpoint> checkpoints = checkpointRepository.findAll();
        return checkpoints.stream()
            .map(checkpoint -> {
                CheckpointDto dto = modelMapper.map(checkpoint, CheckpointDto.class);
                dto.setShipmentId(checkpoint.getShipment().getId());
                return dto;
            })
            .collect(Collectors.toList());
    }
}
