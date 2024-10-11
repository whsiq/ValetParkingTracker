package com.valetparkingtracker.enterprise;

import com.valetparkingtracker.enterprise.dao.IVehicleDAO;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.IVehicleService;
import com.valetparkingtracker.enterprise.service.VehicleServiceStub;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ValetParkingTrackerApplicationTests {

    @Autowired
    private IVehicleService vehicleService;
    private Vehicle vehicle = new Vehicle();

    @MockBean
    private IVehicleDAO vehicleDAO;

    @Test
    void contextLoads() {
    }

    @Test
    void fetchVehicleByID_returnsVolkswagenForID25() throws Exception {
        givenVehicleDataIsAvailable();
        whenSearchVehicleWithID25();
        thenReturnOneVolkswagenVehicleForID25();

    }

    private void givenVehicleDataIsAvailable() throws Exception {
        Mockito.when(vehicleDAO.save(vehicle)).thenReturn(vehicle);
        vehicleService = new VehicleServiceStub(vehicleDAO);
    }

    private void whenSearchVehicleWithID25() {
        vehicle = vehicleService.fetchById(25);
    }

    private void thenReturnOneVolkswagenVehicleForID25() {
        String description = vehicle.getMake();
        assertEquals("Volkswagen", description);
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
}
