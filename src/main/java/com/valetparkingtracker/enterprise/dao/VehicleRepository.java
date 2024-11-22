package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

}
