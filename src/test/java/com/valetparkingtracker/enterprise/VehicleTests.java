package com.valetparkingtracker.enterprise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.valetparkingtracker.enterprise.dao.IVehicleDAO;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.IVehicleService;
import com.valetparkingtracker.enterprise.service.VehicleServiceStub;

@SpringBootTest
public class VehicleTests {

    @Autowired
    private IVehicleService vehicleService;
    private Vehicle vehicle = new Vehicle();

    @MockBean
    private IVehicleDAO vehicleDAO;

    @Test
    void fetchVehicleByID_returnsVolkswagenForID25() throws Exception {
        givenVehicleDataIsAvailable();
        whenVehicle25AddedIsVolkswagen();
        whenSearchVehicleWithID25();
        thenReturnOneVolkswagenVehicleForID25();

    }

    @Test
    private void givenVehicleDataIsAvailable() throws Exception {
        Mockito.when(vehicleDAO.save(vehicle)).thenReturn(vehicle);
        vehicleService = new VehicleServiceStub(vehicleDAO);
    }
    @Test
    private void whenVehicle25AddedIsVolkswagen() {
        Vehicle volkswagen = new Vehicle();
        volkswagen.setVehicleId(25);
        volkswagen.setMake("Volkswagen");
        Mockito.when(vehicleDAO.fetch(25)).thenReturn(volkswagen);
    }

    @Test
    private void whenSearchVehicleWithID25() {
        vehicle = vehicleService.fetchById(25);
    }

    @Test
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

    @Test
    private void whenUserCreatesANewVehicleAndSaves() {
        vehicle.setMake("Volkswagen");
        vehicle.setModel("Golf");
    }
    
    @Test
    private void thenCreateNewVehicleRecordAndReturnIt() throws Exception {
        Vehicle createdVehicle = vehicleService.save(vehicle);
        assertEquals(vehicle, createdVehicle);
        verify(vehicleDAO, atLeastOnce()).save(vehicle);
    }

}
