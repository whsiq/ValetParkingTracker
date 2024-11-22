package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dao.IVehicleDAO;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private IVehicleDAO vehicleDAO;

    public VehicleService() {

    }

    public VehicleService(IVehicleDAO vehicleDAO) {

        this.vehicleDAO = vehicleDAO;
    }

    @Override
    @Cacheable(value="vehicle", key="#id")
    public Vehicle fetchById(int id) {
        Vehicle foundVehicle = vehicleDAO.fetch(id);
        return foundVehicle;
    }

    @Override
    @CacheEvict(value="vehicle", key="#id")
    public void delete(int id) throws Exception {
        vehicleDAO.delete(id);
    }

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        return vehicleDAO.save(vehicle);
    }

    @Override
    @Cacheable("vehicles")
    public List<Vehicle> fetchAll() {
        return vehicleDAO.fetchAll();
    }
}
