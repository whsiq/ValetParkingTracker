package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dto.Vehicle;

import java.util.List;

public interface IVehicleService {
    /**
     * Fetch a vehicle with a given ID
     * @param id a unique identifier for a particular vehicle
     * @return the matching vehicle, or null if no matches found.
     */
    Vehicle fetchById(int id);

    void delete(int id) throws Exception;

    Vehicle save(Vehicle vehicle) throws Exception;

    List<Vehicle> fetchAll();
}
