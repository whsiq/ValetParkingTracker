package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Vehicle;

public interface IVehicleDAO {
    Vehicle save(Vehicle vehicle) throws Exception;
}
