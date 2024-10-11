package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleDAOStub implements IVehicleDAO {

    List<Vehicle> allVehicles = new ArrayList<Vehicle>();

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        allVehicles.add(vehicle);
        return vehicle;
    }

    @Override
    public List<Vehicle> fetchAll() {
        return allVehicles;
    }
}
