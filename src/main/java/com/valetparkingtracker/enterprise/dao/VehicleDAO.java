package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("vehicleDAO")
public class VehicleDAO implements IVehicleDAO {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        Vehicle createdVehicle = vehicleRepository.save(vehicle);
        return createdVehicle;
    }

    @Override
    public List<Vehicle> fetchAll() { // This copies everything from an iterable to a list. Pretty slow, could change return type to Iterable<Vehicle>
        List<Vehicle> allVehicles = new ArrayList<>();
        Iterable<Vehicle> vehicles = vehicleRepository.findAll();
        for (Vehicle vehicle : vehicles) {
            allVehicles.add(vehicle);
        }
        return allVehicles;
    }

    @Override
    public Vehicle fetch(int id) {
        return vehicleRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }
}
