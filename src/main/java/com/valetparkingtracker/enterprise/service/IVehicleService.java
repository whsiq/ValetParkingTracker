package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dto.Vehicle;

public interface IVehicleService {
    /**
     * Fetch a vehicle with a given ID
     * @param id a unique identifier for a particular vehicle
     * @return the matching vehicle, or null if no matches found.
     */
    Vehicle fetchById(int id);
}
