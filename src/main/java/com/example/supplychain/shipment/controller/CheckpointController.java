package com.example.supplychain.shipment.controller;

import com.example.supplychain.dto.CheckpointDto;
import com.example.supplychain.shipment.service.CheckpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkpoints")
@CrossOrigin(origins = "*")
public class CheckpointController {

    @Autowired
    private CheckpointService checkpointService;

    // Add a new checkpoint to a shipment
    @PostMapping("/shipment/{shipmentId}")
    public ResponseEntity<CheckpointDto> addCheckpoint(
            @PathVariable Long shipmentId,
            @RequestBody CheckpointDto checkpointDto) {
        CheckpointDto createdCheckpoint = checkpointService.addCheckpoint(shipmentId, checkpointDto);
        return ResponseEntity.ok(createdCheckpoint);
    }

    // Get all checkpoints for a specific shipment
    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<CheckpointDto>> getCheckpointsByShipment(@PathVariable Long shipmentId) {
        List<CheckpointDto> checkpoints = checkpointService.getCheckpointsByShipment(shipmentId);
        return ResponseEntity.ok(checkpoints);
    }

    // Update an existing checkpoint
    @PutMapping("/{checkpointId}")
    public ResponseEntity<CheckpointDto> updateCheckpoint(
            @PathVariable Long checkpointId,
            @RequestBody CheckpointDto checkpointDto) {
        CheckpointDto updatedCheckpoint = checkpointService.updateCheckpoint(checkpointId, checkpointDto);
        return ResponseEntity.ok(updatedCheckpoint);
    }

    // Delete a checkpoint
    @DeleteMapping("/{checkpointId}")
    public ResponseEntity<String> deleteCheckpoint(@PathVariable Long checkpointId) {
        checkpointService.deleteCheckpoint(checkpointId);
        return ResponseEntity.ok("Checkpoint deleted successfully");
    }

    // Get all checkpoints (for admin purposes)
    @GetMapping
    public ResponseEntity<List<CheckpointDto>> getAllCheckpoints() {
        List<CheckpointDto> checkpoints = checkpointService.getAllCheckpoints();
        return ResponseEntity.ok(checkpoints);
    }
}
