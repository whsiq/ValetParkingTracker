package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dao.IVehicleDAO;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceStub implements IVehicleService {

    @Autowired
    private IVehicleDAO vehicleDAO;

    public VehicleServiceStub() {

    }

    public VehicleServiceStub(IVehicleDAO vehicleDAO) {

        this.vehicleDAO = vehicleDAO;
    }

    @Override
    public Vehicle fetchById(int id) {
        Vehicle foundVehicle = vehicleDAO.fetch(id);
        return foundVehicle;
    }

    @Override
    public void delete(int id) throws Exception {
        vehicleDAO.delete(id);
    }

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        return vehicleDAO.save(vehicle);
    }

    @Override
    public List<Vehicle> fetchAll() {
        return vehicleDAO.fetchAll();
    }
}
