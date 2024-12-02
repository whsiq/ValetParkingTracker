package com.valetparkingtracker.enterprise;

import com.valetparkingtracker.enterprise.dao.IVehicleDAO;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.IVehicleService;
import com.valetparkingtracker.enterprise.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class VehicleTests {

    @Autowired
    private IVehicleService vehicleService;
    private Vehicle vehicle = new Vehicle();

    @Qualifier("vehicleDAOStub")
    @MockBean
    private IVehicleDAO vehicleDAO;

    @Test
    void fetchVehicleByID_returnsVolkswagenForID25() throws Exception {
        givenVehicleDataIsAvailable();
        whenVehicle25AddedIsVolkswagen();
        whenSearchVehicleWithID25();
        thenReturnOneVolkswagenVehicleForID25();
    }

    private void givenVehicleDataIsAvailable() throws Exception {
        Mockito.when(vehicleDAO.save(vehicle)).thenReturn(vehicle);
        vehicleService = new VehicleService(vehicleDAO);
    }

    private void whenVehicle25AddedIsVolkswagen() {
        Vehicle volkswagen = new Vehicle();
        volkswagen.setVehicleId(25);
        volkswagen.setMake("Volkswagen");
        Mockito.when(vehicleDAO.fetch(25)).thenReturn(volkswagen);
    }

    private void whenSearchVehicleWithID25() {
        vehicle = vehicleService.fetchById(25);
    }

    private void thenReturnOneVolkswagenVehicleForID25() {
        String make = vehicle.getMake();
        assertEquals("Volkswagen", make);
    }

    @Test
    void saveVehicle_validateReturnVehicleWithMakeAndModel() throws Exception {
        givenVehicleDataIsAvailable();
        whenUserCreatesANewVehicleAndSaves();
        thenCreateNewVehicleRecordAndReturnIt();
    }

    private void whenUserCreatesANewVehicleAndSaves() {
        vehicle.setMake("Volkswagen");
        vehicle.setModel("Golf");
    }

    private void thenCreateNewVehicleRecordAndReturnIt() throws Exception {
        Vehicle createdVehicle = vehicleService.save(vehicle);
        assertEquals(vehicle, createdVehicle);
        verify(vehicleDAO, atLeastOnce()).save(vehicle);
    }

    @Test
    void deleteVehicle_validateMethod() throws Exception {
        givenVehicleDataIsAvailable();
        whenVehicle25AddedIsVolkswagen();
        whenSearchVehicleWithID25();
        thenDeleteVehicleWithID25();
        thenValidateVehicleWithID25VehicleNotFound();
    }

    private void thenDeleteVehicleWithID25() throws Exception {
        vehicleService.delete(25);
        verify(vehicleDAO, atLeastOnce()).delete(25);
    }

    private void thenValidateVehicleWithID25VehicleNotFound() {
        Mockito.when(vehicleDAO.fetch(25)).thenReturn(null);
        verify(vehicleDAO, atLeastOnce()).delete(25);
    }

    @Test
    void fetchAllVehicles_validateMethod() throws Exception {
        givenVehicleDataIsAvailable();
        whenMultipleVehiclesAdded();
        thenReturnAllVehicles();
    }

    private void whenMultipleVehiclesAdded() {
        List<Vehicle> mockVehicles = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setMake("Toyota");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setMake("Honda");

        mockVehicles.add(vehicle1);
        mockVehicles.add(vehicle2);

        Mockito.when(vehicleDAO.fetchAll()).thenReturn(mockVehicles);
    }

    private void thenReturnAllVehicles() {
        List<Vehicle> result = vehicleService.fetchAll();
        assertEquals(2, result.size());
        assertEquals("Toyota", result.get(0).getMake());
        assertEquals("Honda", result.get(1).getMake());
        verify(vehicleDAO, atLeastOnce()).fetchAll();
    }
}
