package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dto.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleServiceStub implements IVehicleService {
    @Override
    public Vehicle fetchById(int id) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Volkswagen");
        vehicle.setId(id);
        return vehicle;
    }
}
