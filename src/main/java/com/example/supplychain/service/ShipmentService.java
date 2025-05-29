package com.example.supplychain.service;

import com.example.supplychain.dto.ShipmentRequest;

public interface ShipmentService {
    Object createShipment(ShipmentRequest shipmentRequest);
}
