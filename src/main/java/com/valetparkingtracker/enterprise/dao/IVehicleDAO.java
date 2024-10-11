package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Vehicle;

import java.util.List;

public interface IVehicleDAO {
    Vehicle save(Vehicle vehicle) throws Exception;

    List<Vehicle> fetchAll();
}
