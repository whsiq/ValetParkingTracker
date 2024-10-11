package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dao.IVehicleDAO;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleServiceStub implements IVehicleService {

    private IVehicleDAO vehicleDAO;

    public VehicleServiceStub() {

    }

    public VehicleServiceStub(IVehicleDAO vehicleDAO) {

        this.vehicleDAO = vehicleDAO;
    }

    @Override
    public Vehicle fetchById(int id) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Volkswagen");
        vehicle.setId(id);
        return vehicle;
    }

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        return vehicleDAO.save(vehicle);
    }
}
