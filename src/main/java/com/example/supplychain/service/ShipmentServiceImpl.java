package com.example.supplychain.service;

import com.example.supplychain.dto.ShipmentRequest;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Override
    public Object createShipment(ShipmentRequest shipmentRequest) {
        return shipmentRequest;
    }
}
